package io.github.hakkelt.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Repeatable(CollectedReplacements.class)
public @interface Replacements {
    String[] value();
    String[] extraImports() default {};
}
