package io.github.hakkelt.generator;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.thoughtworks.qdox.model.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.text.WordUtils;

import lombok.SneakyThrows;
import lombok.extern.java.Log;

@Log
class Template {

    static final int SLUG_LENGTH = 20;
    static final Pattern SPECIAL_REGEX_CHARS = Pattern.compile("[{}()\\[\\].+*?^$\\\\|]");
    static final String BR = System.lineSeparator();
    static final String IMPORTS_PLACEHOLDER =
        "--------------------- ## IMPORTS ## ---------------------";
    static final String GENERATOR_PACKAGE =
        "io.github.hakkelt.generator";
    static final String SIZE_MISMATCH =
        "In the following annotation, the number of replacement string does not match the number of patterns in the @Patterns annotation: ";
    static final String MISSING_REPLACEMENT_ANNOTATION =
        "You must provide at least one @Replacements annotation when @Patterns annotation is used!";
    static final String CONFLICTING_REPLACE_OR_REPLACEMENTS_ANNOTATIONS =
        "You cannot use @Replace annotation together with either @Patterns or @Replacements annotations!";
    static final String MULTIPLE_CLASSTEMPLATES_IN_A_FILE =
        "@ClassTemplate annotation can be used only once per file.";
    static final String CLASSTEMPLATE_ON_INNER_CLASS =
        "@ClassTemplate annotation cannot be used on inner classes.";
    static final String MISSING_REFERENCE_IN_NEW_NAME_PATTERN =
        "The 'newName' parameter of @ClassTemplate annotation must contain a reference to any of the patterns (e.g. $1)";
    static final String TOO_LARGE_REFERENCE_INDEX =
        "Reference index in newName property is too large in the following expression: ";
    static final String GENERATED_HEADER_COMMENT =
        "This file was generated, so instead of changing it, consider updating the template: %s";
    static final String EXTRAIMPORTS_ON_METHOD_LEVEL =
        "extraImports property only allowed when @Replacements annotation is used on a class.";

    JavaSource source;
    JavaClass mainClass;
    String sourceFolder;
    Path outputDirectory;
    String packageName;
    String newNamePattern;
    String newNameForOriginal;
    List<Pattern> templatePatterns;
    List<List<String>> templateReplacementsList;
    Set<String> imports = new TreeSet<>();
    Set<String> staticImports = new TreeSet<>();
    List<List<String>> extraImports = new ArrayList<>();
    boolean shouldReplacePatterns = true;
    String oldClassName;

    String templateString;
    String templateStringForOriginal;
    List<Pair<Pattern, String>> slugs = new ArrayList<>();
    List<Pair<String, List<String>>> buildRules = new ArrayList<>();
    Set<String> alreadyUsedNames = new HashSet<>();
    Random random = new Random();

    public Template(JavaSource source, JavaClass mainClass, String sourceFolder) {
        this.source = source;
        this.mainClass = mainClass;
        this.sourceFolder = sourceFolder;

        var classTemplateAnnotations = filterAnnotations(mainClass.getAnnotations(), ClassTemplate.class, false);
        if (classTemplateAnnotations.size() > 1)
            throw new IllegalArgumentException(MULTIPLE_CLASSTEMPLATES_IN_A_FILE);
        JavaAnnotation classTemplateAnnotation = classTemplateAnnotations.get(0);
        String relativePath = "src/" + removeQuotationMarks(
            (String) classTemplateAnnotation.getNamedParameter("outputDirectory"));
        this.outputDirectory = Path.of(sourceFolder, relativePath).toAbsolutePath();
        List<String> parts = List.of(relativePath.replace("\\\\", "/").split("/"));
        this.packageName = String.join(".", parts.subList(3, parts.size()));
        this.oldClassName = mainClass.getSimpleName();

        var pair = processAnnotations(mainClass.getAnnotations(), true);
        templatePatterns = pair.getLeft();
        templateReplacementsList = pair.getRight();
        compileSlugs();

        this.newNamePattern = getNewNamePattern(classTemplateAnnotation, true);
        this.templateString = buildTemplate(source, mainClass, newNamePattern);

        this.newNameForOriginal = getNewNamePattern(classTemplateAnnotation, false);
        this.shouldReplacePatterns = false;
        this.templateStringForOriginal = buildTemplate(source, mainClass, newNameForOriginal);
    }

    @SneakyThrows
    public void build() {
        if (!outputDirectory.toFile().exists())
            Files.createDirectory(outputDirectory);
        writeToFile(templateStringForOriginal.replace(oldClassName, newNameForOriginal), newNameForOriginal, outputDirectory);
        IntStream.range(0, templateReplacementsList.size()).parallel().forEach(i -> buildSingle(i, outputDirectory));
        log.info(String.format("Processing of %s is done", source.getURL().toURI().getPath()));
    }

    private void buildSingle(int index, Path path) {
        String compiledSource = templateString;
        String className = newNamePattern;
        for (var rule : buildRules) {
            compiledSource = compiledSource.replaceAll(rule.getKey(), rule.getValue().get(index));
            className = className.replaceAll(rule.getKey(), rule.getValue().get(index));
        }
        compiledSource = compiledSource.replace(IMPORTS_PLACEHOLDER, getImportsString(extraImports.get(index)));
        writeToFile(compiledSource, className, path);
    }

    private String buildTemplate(JavaSource source, JavaClass mainClass, String mainClassNewName) {
        StringBuilder sb = new StringBuilder();
        printGeneratedNote(sb);
        for (var className : source.getImports()) {
            if (className.startsWith(GENERATOR_PACKAGE) || getPackageName(className).equals(packageName))
                continue;
            (className.startsWith("static ") ? staticImports : imports).add(className);
        }
        sb.append(this.shouldReplacePatterns ? IMPORTS_PLACEHOLDER : getImportsString(List.of()));

        for (var cls : source.getClasses())
            new PreparedClass(cls, cls.equals(mainClass) ? mainClassNewName : cls.getSimpleName(), "", List.of()).print(sb);

        return sb.toString().strip() + BR;
    }

    private String getPackageName(String importString) {
        return importString.substring(0, importString.lastIndexOf("."));
    }

    private String getNewNamePattern(JavaAnnotation classTemplateAnnotation, boolean useSlugs) {
        String namePattern = removeQuotationMarks((String) classTemplateAnnotation.getNamedParameter("newName"));
        Matcher m = Pattern.compile(".*\\$(\\d+).*").matcher(namePattern);
        boolean matched = m.find();
        if (!matched && !templatePatterns.isEmpty()) {
            throw new IllegalArgumentException(MISSING_REFERENCE_IN_NEW_NAME_PATTERN);
        } else if (matched) {
            int matchedNumber = Integer.parseInt(m.group(1));
            if (matchedNumber > templatePatterns.size())
                throw new IllegalArgumentException(TOO_LARGE_REFERENCE_INDEX + namePattern);
            if (useSlugs) {
                return namePattern.replace("$" + m.group(1), slugs.get(matchedNumber - 1).getValue());
            } else {
                String replacement = templatePatterns.get(matchedNumber - 1).toString();
                if (replacement.startsWith("\\b"))
                    replacement = replacement.substring(2, replacement.length() - 2);
                return namePattern.replace("$" + m.group(1), replacement);
            }
        }
        return namePattern;
    }

    @SneakyThrows
    private void writeToFile(String compiledSource, String className, Path path) {
        path = Paths.get(path.toString(), className + ".java");
        try (FileWriter writer = new FileWriter(path.toFile())) {
            writer.write(compiledSource);
        }
    }

    private void compileSlugs() {
        for (int[] i = {0}; i[0] < templatePatterns.size(); i[0]++) {
            String slug = randomUniqueString();
            slugs.add(Pair.of(templatePatterns.get(i[0]), slug));
            var collectedReplacements = 
                templateReplacementsList.stream().map(replacements -> replacements.get(i[0])).collect(Collectors.toList());
            buildRules.add(Pair.of(slug, collectedReplacements));
        }
    }

    private String getImportsString(List<String> currentExtraImports) {
        StringBuilder sb = new StringBuilder();

        sb.append("package ").append(packageName).append(";").append(BR).append(BR);

        var localStaticImports = new TreeSet<>(staticImports);
        var localImports = new TreeSet<>(imports);
        for (var className : currentExtraImports) {
            (className.startsWith("static ") ? localStaticImports : localImports).add(className);
        }
        for (var className : localStaticImports)
            sb.append("import ").append(className).append(";").append(BR);
        if (!localStaticImports.isEmpty())
            sb.append(BR);
        String previousDomain = localImports.stream().findFirst().orElse("").split("\\.")[0];
        for (var className : localImports) {
            String newDomain = className.split("\\.")[0];
            if (!previousDomain.equals(newDomain))
                sb.append(BR);
            previousDomain = newDomain;
            sb.append("import ").append(className).append(";").append(BR);
        }
        sb.append(BR);
        return sb.toString();
    }

    @SneakyThrows
    private void printGeneratedNote(StringBuilder sb) {
        String path = Path.of(sourceFolder).relativize(Path.of(source.getURL().toURI())).toString();
        String warning = String.format(GENERATED_HEADER_COMMENT, path);
        int lineLength = 120 - " * ".length();
        String wrapped = WordUtils.wrap(warning, lineLength, BR, true);
        sb.append("/**").append(BR).append(" * ").append("-".repeat(lineLength));
        for (String line : wrapped.split(BR))
            sb.append(BR).append(" * ").append(line);
        sb.append(BR).append(" * ").append("-".repeat(lineLength)).append(BR).append(" */").append(BR).append(BR);
    }

    class PreparedClass {
        JavaClass thisClass;
        String className;
        String leftPadding;
        List<JavaTypeVariable<JavaGenericDeclaration>> parentTypeParameters;

        public PreparedClass(JavaClass thisClass, String className, String leftPadding,
                List<JavaTypeVariable<JavaGenericDeclaration>> parentTypeParameters) {
            this.thisClass = thisClass;
            this.className = className;
            this.leftPadding = leftPadding;
            this.parentTypeParameters = parentTypeParameters;

            if (thisClass.isInner() && !processAnnotations(thisClass.getAnnotations(), true).getLeft().isEmpty())
                throw new IllegalArgumentException(CLASSTEMPLATE_ON_INNER_CLASS);
        }

        public void print(StringBuilder sb) {
            String insideLeftPadding = leftPadding + "    ";

            String classBody = getBody(insideLeftPadding);

            printComment(thisClass.getComment(), thisClass.getTags(), sb, leftPadding);
            printAnnotations(thisClass.getAnnotations(), sb, leftPadding);
            printSignature(sb, classBody);

            if (!thisClass.getNestedClasses().isEmpty()) {
                for (var cls : thisClass.getNestedClasses())
                    new PreparedClass(cls, cls.getSimpleName(), insideLeftPadding, thisClass.getTypeParameters())
                        .print(sb);
                sb.append(BR);
            }

            sb.append(classBody);

            sb.append(leftPadding).append("}").append(BR).append(BR);
        }

        private String getBody(String insideLeftPadding) {
            StringBuilder sb = new StringBuilder();

            if (!thisClass.getFields().isEmpty()) {
                for (var field : thisClass.getFields())
                    printField(sb, field, insideLeftPadding);
                sb.append(BR);
            }

            for (var method : thisClass.getConstructors())
                new PreparedMethod(method, className, insideLeftPadding).print(sb);

            for (var method : thisClass.getMethods())
                new PreparedMethod(method, insideLeftPadding, thisClass.isInterface()).print(sb);

            return sb.toString();
        }

        private void printSignature(StringBuilder sb, String classBody) {
            sb.append(leftPadding);
            printModifiers(sb, thisClass.getModifiers());
            if (thisClass.isInterface()) {
                sb.append("interface ");
                printClassName(sb, classBody);
                if (!thisClass.getImplements().isEmpty())
                    printInterfaceList(sb, " extends ", thisClass.getImplements());
            } else if (thisClass.isEnum()) {
                sb.append("enum ");
                printClassName(sb, classBody);
                if (!thisClass.getImplements().isEmpty())
                    printInterfaceList(sb, " implements ", thisClass.getImplements());
            } else {
                sb.append("class ");
                printClassName(sb, classBody);
                if (!thisClass.getSuperClass().getCanonicalName().equals("java.lang.Object"))
                    sb.append(" extends ").append(replacePatterns(thisClass.getSuperClass().getGenericValue()));
                if (!thisClass.getImplements().isEmpty())
                    printInterfaceList(sb, " implements ", thisClass.getImplements());
            }
            sb.append(" {").append(BR).append(BR);
        }

        private void printInterfaceList(StringBuilder sb, String keywordBefore, List<JavaType> types) {
            List<String> temp = types.stream()
                .map(JavaType::getGenericValue)
                .map(Template.this::replacePatterns)
                .collect(Collectors.toList());
            sb.append(keywordBefore).append(String.join(", ", temp));
        }

        private void printClassName(StringBuilder sb, String classBody) {
            sb.append(className);
            var list = thisClass.getTypeParameters().stream()
                .filter(param ->
                    (thisClass.isStatic() || !parentTypeParameters.contains(param))
                        && Pattern.compile("\\b" + param + "\\b").matcher(classBody).find())
                .map(JavaType::getGenericValue)
                .map(Template::removeQuotationMarks)
                .collect(Collectors.toList());
            if (!list.isEmpty()) {
                sb.append("<");
                sb.append(StringUtils.join(list, ", "));
                sb.append(">");
            }
        }

        private void printField(StringBuilder sb, JavaField field, String insideLeftPadding) {
            printComment(field.getComment(), field.getTags(), sb, insideLeftPadding);
            printAnnotations(field.getAnnotations(), sb, insideLeftPadding);
            sb.append(insideLeftPadding);
            if (!thisClass.isEnum()) {
                printModifiers(sb, field.getModifiers());
                sb.append(replacePatterns(field.getType().getGenericValue())).append(" ");
            }
            sb.append(field.getName());
            String initializer = field.getInitializationExpression();
            if (!initializer.isBlank())
                sb.append(" = ").append(replacePatterns(initializer));
            sb.append(thisClass.isEnum() ? "," : ";").append(BR);
        }
    }

    class PreparedMethod {
        String comment;
        List<DocletTag> tags;
        List<JavaAnnotation> annotations;
        List<String> modifiers;
        String returnType;
        String baseName;
        String parameterList;
        String sourceCode;
        String leftPadding;
        List<Pattern> patterns;
        List<List<String>> replacementsList;
        boolean isAbstract;
        String typeParameters;
        String throwsList;

        public PreparedMethod(JavaMethod method, String leftPadding, boolean isInInterface) {
            this.comment = method.getComment();
            this.tags = method.getTags();
            this.annotations = method.getAnnotations();
            this.modifiers = method.getModifiers();
            this.returnType = method.getReturnType().getGenericValue() + " ";
            this.baseName = method.getName();
            this.parameterList = getParameterList(method.getCodeBlock(), method.getSourceCode(), method.getParameters());
            this.sourceCode = method.getSourceCode();
            this.isAbstract = isInInterface ? !method.isDefault() && !method.isStatic() : method.isAbstract();
            this.leftPadding = leftPadding;
            this.typeParameters = String.join(", ",
                method.getTypeParameters().stream().map(JavaType::getGenericValue).collect(Collectors.toList()));
            this.throwsList = String.join(", ",
                method.getExceptionTypes().stream().map(JavaType::getValue).collect(Collectors.toList()));

            var pair = processAnnotations(method.getAnnotations(), false);
            this.patterns = pair.getLeft();
            this.replacementsList = pair.getRight();

            if (isInInterface && method.isDefault() && !method.isStatic() && !modifiers.contains("default"))
                modifiers.add("default");
        }

        public PreparedMethod(JavaConstructor method, String className, String leftPadding) {
            this.comment = method.getComment();
            this.tags = method.getTags();
            this.annotations = method.getAnnotations();
            this.modifiers = method.getModifiers();
            this.returnType = "";
            this.baseName = className;
            this.parameterList = getParameterList(method.getCodeBlock(), method.getSourceCode(), method.getParameters());
            this.sourceCode = method.getSourceCode();
            this.isAbstract = false;
            this.leftPadding = leftPadding;
            this.typeParameters = "";
            this.throwsList = String.join(", ",
                method.getExceptionTypes().stream().map(JavaType::getValue).collect(Collectors.toList()));

            var pair = processAnnotations(method.getAnnotations(), false);
            this.patterns = pair.getLeft();
            this.replacementsList = pair.getRight();
        }

        public void print(StringBuilder sb) {
            if (patterns.isEmpty()) {
                printInternal(sb);
            } else {
                List<String> patternsAsStrings = patterns.stream()
                    .map(Pattern::toString)
                    .map(str -> str.startsWith("\\b") ? str.substring(2, str.length() - 2) : str)
                    .collect(Collectors.toList());
                printInternal(sb, getNewMethodName(1, patternsAsStrings), List.of());
                for (int i = 0; i < replacementsList.size(); i++)
                    printInternal(sb, getNewMethodName(i + 2, replacementsList.get(i)), replacementsList.get(i));
            }
        }

        private void printInternal(StringBuilder sb) {
            StringBuilder sb2 = new StringBuilder();
            printComment(comment, tags, sb2, leftPadding);
            printAnnotations(annotations, sb2, leftPadding);

            sb2.append(leftPadding);
            printModifiers(sb2, modifiers);
            if (!this.typeParameters.isBlank())
                sb2.append(typeParameters).append(" ");

            sb2.append(returnType).append(baseName).append("(")
                .append(parameterList).append(")");
            if (!throwsList.isBlank())
                sb2.append(" throws ").append(throwsList);
            if (isAbstract)
                sb2.append(";");
            else
                sb2.append(" {").append(replacePatterns(sourceCode)).append("}");
            sb2.append(BR).append(BR);
            sb.append(replacePatterns(sb2.toString()));
        }

        private void printInternal(StringBuilder sb, String methodNewName, List<String> replacements) {
            StringBuilder sb2 = new StringBuilder();
            printComment(comment, tags, sb2, leftPadding);
            printAnnotations(annotations, sb2, leftPadding);

            sb2.append(leftPadding);
            printModifiers(sb2, modifiers);
            if (!this.typeParameters.isBlank())
                sb2.append(typeParameters).append(" ");

            sb2.append(returnType).append(methodNewName).append("(")
                .append(parameterList).append(")");
            if (!throwsList.isBlank())
                sb2.append(" throws ").append(throwsList);
            if (isAbstract)
                sb2.append(";");
            else
                sb2.append(" {").append(sourceCode).append("}");
            sb2.append(BR).append(BR);

            String temp = replacePatterns(sb2.toString());
            for (int i = 0; i < replacements.size(); i++)
                temp = patterns.get(i).matcher(temp).replaceAll(replacements.get(i));

            sb.append(temp);
        }

        private String getNewMethodName(int index, List<String> replacements) {
            boolean hasSignatureChanged = patterns.stream()
                .anyMatch(p -> p.matcher(parameterList).find() || p.matcher(baseName).find());
            if (hasSignatureChanged)
                return baseName;
            for (int i = 0; i < patterns.size(); i++) {
                String regex = patterns.get(i).toString();
                String deregexified = regex.substring(2, regex.length() - 2);
                if (deregexified.matches("[a-zA-Z_][a-zA-Z0-9_]*"))
                    return baseName + "With" + StringUtils.capitalize(replacements.get(i));
            }
            return baseName + String.valueOf(index);
        }

        private String getParameterList(String codeBlock, String body, List<JavaParameter> params) {
            if (params.isEmpty())
                return "";
            int startOfBody = body.isBlank() ? codeBlock.length() : codeBlock.indexOf(body);
            int parametersEnd = codeBlock.lastIndexOf(")", startOfBody);
            int parametersStart = codeBlock.lastIndexOf("(", parametersEnd);
            String parametersString = codeBlock.substring(parametersStart, parametersEnd);
            List<String> updatedParams = new ArrayList<>();
            for (int i = 0; i < params.size(); i++) {
                String paramType = params.get(i).getGenericValue();
                String paramName = params.get(i).getName();
                if (Pattern.compile("\\.\\.\\.[ ]?" + paramName).matcher(parametersString).find())
                    updatedParams.add(paramType + "... " + paramName);
                else
                    updatedParams.add(paramType + " " + paramName);
            }
            return String.join(", ", updatedParams);
        }

    }

    private static String getAnnotationString(JavaAnnotation annotation) {
        JavaClass annotationClass = annotation.getType();
        return annotation.getCodeBlock().replace(annotationClass.getCanonicalName(), annotationClass.getValue());
    }

    private static List<JavaAnnotation> filterAnnotations(List<JavaAnnotation> src, Class<?> annotationClass,
            boolean skipMatches) {
        return src.stream()
                .filter(annot -> skipMatches ^ annot.getType().getCanonicalName().equals(annotationClass.getName()))
                .collect(Collectors.toList());
    }

    private Pair<List<Pattern>, List<List<String>>> processAnnotations(List<JavaAnnotation> annotations, boolean isClassLevel) {
        List<JavaAnnotation> replaceAnnotation = filterAnnotations(annotations, Replace.class, false);
        List<JavaAnnotation> patternsAnnotations = filterAnnotations(annotations, Patterns.class, false);
        if (!replaceAnnotation.isEmpty() && !patternsAnnotations.isEmpty())
            throw new IllegalArgumentException(CONFLICTING_REPLACE_OR_REPLACEMENTS_ANNOTATIONS);
        else if (!patternsAnnotations.isEmpty()) {
            List<JavaAnnotation> replacementAnnotations = filterAnnotations(annotations, Replacements.class, false);
            return processPatternReplacementsAnnotations(patternsAnnotations, replacementAnnotations, isClassLevel);
        } else if (!replaceAnnotation.isEmpty()) {
            return processReplaceAnnotations(replaceAnnotation);
        } else {
            return Pair.of(List.of(), List.of());
        }
    }

    @SuppressWarnings("unchecked")
    private Pair<List<Pattern>, List<List<String>>> processPatternReplacementsAnnotations(
            List<JavaAnnotation> patternsAnnotations, List<JavaAnnotation> replacementAnnotations, boolean isClassLevel) {
        List<Pattern> patterns;
        List<List<String>> replacementsList;

        if (replacementAnnotations.isEmpty())
            throw new IllegalArgumentException(MISSING_REPLACEMENT_ANNOTATION);
        patterns = ((List<String>) patternsAnnotations.get(0).getNamedParameter("value")).stream()
                .map(Template::removeQuotationMarks).map(this::toRegex)
                .collect(Collectors.toList());
        replacementsList = new ArrayList<>();
        for (var replacementAnnotation : replacementAnnotations) {
            List<String> replacements = removeQuotationMarks(getParam(replacementAnnotation, "value"));
            List<String> localExtraImports = removeQuotationMarks(getParam(replacementAnnotation, "extraImports"));
            if (!isClassLevel && !localExtraImports.isEmpty())
                throw new IllegalArgumentException(EXTRAIMPORTS_ON_METHOD_LEVEL);
            if (isClassLevel)
                extraImports.add(localExtraImports);
            if (replacements.size() != patterns.size())
                throw new IllegalArgumentException(SIZE_MISMATCH + replacementAnnotation.getCodeBlock().trim());
            replacementsList.add(replacements);
        }
        return Pair.of(patterns, replacementsList);
    }

    private Pair<List<Pattern>, List<List<String>>> processReplaceAnnotations(List<JavaAnnotation> replaceAnnotation) {
        List<Pattern> patterns;
        List<List<String>> replacementsList;
        JavaAnnotation annotation = replaceAnnotation.get(0);
        patterns = List
                .of(toRegex(removeQuotationMarks((String) annotation.getNamedParameter("pattern"))));
        replacementsList = new ArrayList<>();
        removeQuotationMarks(getParam(annotation, "replacements"))
            .forEach(item -> replacementsList.add(List.of(item)));
        return Pair.of(patterns, replacementsList);
    }

    @SuppressWarnings("unchecked")
    private List<String> getParam(JavaAnnotation annotation, String paramName) {
        Object paramValue = annotation.getNamedParameter(paramName);
        if (paramValue == null)
            return List.of();
        if (paramValue instanceof List)
            return (List<String>) paramValue;
        return List.of((String) paramValue);
    }

    private Pattern toRegex(String str) {
        if (str.startsWith("/") && str.endsWith("/"))
            return Pattern.compile(removeQuotationMarks(str).replace("\\\\", "\\"));
        str = str.replace("\\\"", "\"");
        String regexified = SPECIAL_REGEX_CHARS.matcher(str).replaceAll("\\\\$0");
        boolean isValidIdentifier = str.matches("[a-zA-Z_][a-zA-Z0-9_]*");
        return isValidIdentifier ? Pattern.compile("\\b" + regexified + "\\b") : Pattern.compile(regexified);
    }

    private static String removeQuotationMarks(String str) {
        str = str.trim();
        return str.substring(1, str.length() - 1);
    }

    private static List<String> removeQuotationMarks(List<String> strList) {
        return strList.stream().map(Template::removeQuotationMarks).collect(Collectors.toList());
    }

    private static boolean areEqual(JavaAnnotation annotation, Class<?> annotationClass) {
        return annotation.getType().getCanonicalName().equals(annotationClass.getName());
    }

    private void printComment(String comment, List<DocletTag> doclets, StringBuilder stringBuilder,
            String leftPadding) {
        if (comment == null && doclets.isEmpty())
            return;

        stringBuilder.append(leftPadding).append("/**").append(BR);
        if (comment != null) {
            comment = replacePatterns(comment).replace(BR, "\n");
            for (String line : comment.split("\n"))
                stringBuilder.append(leftPadding).append(" * ").append(line).append(BR);
            if (!comment.isBlank() && !doclets.isEmpty())
                stringBuilder.append(leftPadding).append(" * ").append(BR);
        }
        for (DocletTag doclet : doclets) {
            stringBuilder.append(leftPadding).append(" * @").append(doclet.getName()).append(" ");
            String docletValue = replacePatterns(doclet.getValue()).replace(BR, "\n");
            stringBuilder.append(String.join(BR + leftPadding + " * ", docletValue.trim().split("\n")));
            stringBuilder.append(BR);
        }

        stringBuilder.append(leftPadding).append(" */").append(BR);
    }

    private void printAnnotations(List<JavaAnnotation> annotations, StringBuilder sb, String leftPadding) {
        for (var annotation : annotations) {
            if (areEqual(annotation, Replace.class) || areEqual(annotation, Patterns.class)
                    || areEqual(annotation, Replacements.class) || areEqual(annotation, ClassTemplate.class))
                continue;
            sb.append(leftPadding).append(replacePatterns(getAnnotationString(annotation).replace("value=", "")));
        }
    }

    private static void printModifiers(StringBuilder sb, List<String> modifiers) {
        if (!modifiers.isEmpty())
            sb.append(String.join(" ", modifiers)).append(" ");
    }

    private String replacePatterns(String str) {
        if (!shouldReplacePatterns)
            return str;
        str = str.replace(oldClassName, newNamePattern);
        for (var entry : slugs)
            str = entry.getKey().matcher(str).replaceAll(entry.getValue());
        return str;
    }

    private String randomUniqueString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        String str;
        do {
            str = random.ints(leftLimit, rightLimit + 1).limit(SLUG_LENGTH)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
        } while (alreadyUsedNames.contains(str));
        alreadyUsedNames.add(str);
        return "#" + str + "#";
    }

}