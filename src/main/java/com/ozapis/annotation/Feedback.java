package com.ozapis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Feedback {

    String comment();
    String advice() default "";
    String[] resources() default {};
}
