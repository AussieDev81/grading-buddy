package com.ozapis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Grade {

    double marksAvailable() default 0;
    double marksAwarded();
}
