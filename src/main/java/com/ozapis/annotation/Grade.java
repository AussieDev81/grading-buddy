package com.ozapis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Provide details on the total marks available, and total marks awarded for the annotated item
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Grade {

    /**
     * Provide the total marks available for the annotated item
     * @return A double value indicating the total marks available
     */
    double marksAvailable() default 0;

    /**
     * Provide the marks awarded for the annotated item
     * @return The total marks/grades awarded for this particular item
     */
    double marksAwarded();
}
