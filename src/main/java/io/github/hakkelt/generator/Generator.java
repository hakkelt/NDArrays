package io.github.hakkelt.generator;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.thoughtworks.qdox.JavaProjectBuilder;
import com.thoughtworks.qdox.model.JavaAnnotation;
import com.thoughtworks.qdox.model.JavaSource;

import lombok.SneakyThrows;

class GeneratorException extends RuntimeException {
    public GeneratorException(Exception e) {
        super(e);
    }
}

public class Generator {

    public static void main(String[] args) {
        var templates = getClassTemplates();
        templates.forEach(Template::build);
    }

    private static List<Template> getClassTemplates() {
        return getSourceFiles().map(Generator::pathToSource)
                .map(src -> {
                    if (src == null)
                        return null;
                    for (var cls : src.getClasses())
                        if (hasThisTypeOfAnnotation(cls.getAnnotations(), ClassTemplate.class))
                            return new Template(src, cls, System.getProperty("user.dir"));
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @SneakyThrows
    private static String getClassName(JavaSource source) {
        return new File(source.getURL().toURI()).getName().split("\\.")[0];
    }

    @SneakyThrows
    private static Stream<Path> getSourceFiles() {
        Path sourcePath = Paths.get(System.getProperty("user.dir"), "src");
        return Files.find(sourcePath, Integer.MAX_VALUE, Generator::filterJavaFiles);
    }

    @SneakyThrows
    private static boolean filterJavaFiles(Path filePath, BasicFileAttributes fileAttr) {
        if (!fileAttr.isRegularFile()
                || (!filePath.toString().endsWith(".java") && !filePath.toString().endsWith(".tpl"))
                || filePath.toFile().getName().equals("ClassTemplate.java"))
            return false;
        try (var scanner = new Scanner(new File(filePath.toString()))) {
            String content = scanner.useDelimiter("\\Z").next();
            return content.contains("@" + ClassTemplate.class.getSimpleName());
        }
    }

    @SneakyThrows
    private static JavaSource pathToSource(Path path) {
        return new JavaProjectBuilder().addSource(path.toFile());
    }

    private static boolean hasThisTypeOfAnnotation(List<JavaAnnotation> src, Class<?> annotationClass) {
        return src.stream()
                .anyMatch(annot -> annot.getType().getSimpleName().equals(annotationClass.getSimpleName()));
    }

}
