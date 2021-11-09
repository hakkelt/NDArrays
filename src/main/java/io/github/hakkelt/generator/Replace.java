package io.github.hakkelt.generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ ElementType.METHOD, ElementType.TYPE })
public @interface Replace {
    String pattern();
    String[] replacements();
}
