package com.ozapis.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Annotate a class, method, or field with feedback metadata
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Feedback {

    /**
     * Provide a constructive feedback comment
     * @return A comment about the annotated object
     */
    String comment();

    /**
     * Give advice on this object
     * @return Advice on this object as a string
     */
    String advice() default "";

    /**
     * Share resources that may be helpful to the student.
     * This could include URLs to website resources, or inspirational quotes or phrases etc
     * @return An array of string resources
     */
    String[] resources() default {};
}
