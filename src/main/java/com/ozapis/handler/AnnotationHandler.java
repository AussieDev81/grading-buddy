package com.ozapis.handler;

import org.reflections.Reflections;
import org.reflections.scanners.FieldAnnotationsScanner;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Set;

public interface AnnotationHandler {

    /**
     * Get a set of all fields (variables & constants) annotated with the given annotation classes.<br/>
     * For example:
     * <pre>
     *{@literal @Feedback(comment="my comment")}
     * String name;
     *
     * var packageName = "com.ozapis";
     *{@literal Set<Field>} fields = AnnotationHandler.getAnnotatedFields(Feedback.class, packageName);
     * </pre>
     * @param packageName The base package to scan
     * @param annotations The annotation classes to be scanned
     * @return A set of {@link Field} objects
     */
    @SafeVarargs
    static Set<Field> getAnnotatedFields(String packageName, Class<? extends Annotation>... annotations){
        Reflections reflections = new Reflections(
                packageName,
                new SubTypesScanner(),
                new FieldAnnotationsScanner());
        Set<Field> results = new LinkedHashSet<>();
        for(Class<? extends Annotation> a: annotations){
            results.addAll(reflections.getFieldsAnnotatedWith(a));
        }
        return results;
    }

    /**
     * Gets a set of all methods having the given annotations at the method level. For example:
     * <pre>
     *{@literal @Feedback(comment="my comment")}
     * public void myMethod() {
     *      //This method will be found
     * }
     * </pre>
     * @param packageName The base package to be scanned
     * @param annotations The annotation classes to be scanned
     * @return A set of {@link Method} objects
     */
    @SafeVarargs
    static Set<Method> getAnnotatedMethods(String packageName, Class<? extends Annotation>... annotations){
        Reflections reflections = new Reflections(
                packageName,
                new SubTypesScanner(),
                new MethodAnnotationsScanner());
        Set<Method> results = new LinkedHashSet<>();
        for(Class<? extends Annotation> a: annotations){
            results.addAll(reflections.getMethodsAnnotatedWith(a));
        }
        return results;
    }

    /**
     * Gets a set of all classes that are annotated (at the class level) with the given annotation classes. For example:
     * <pre>
     *{@literal @Feedback(comment="my comment")}
     * public class MyClass {
     *      //This class will be found
     * }
     * </pre>
     * @param packageName The base package to be scanned
     * @param annotations The annotation classes to be scanned
     * @return A set of {@link Class} objects having the given {@link Annotation}
     */
    @SafeVarargs
    static Set<Class<?>> getAnnotatedClasses(String packageName, Class<? extends Annotation>... annotations){
        Reflections reflections = new Reflections(
                packageName,
                new SubTypesScanner(),
                new TypeAnnotationsScanner());
        Set<Class<?>> results = new LinkedHashSet<>();
        for(Class<? extends Annotation> a: annotations){
            results.addAll(reflections.getTypesAnnotatedWith(a));
        }
        return results;
    }

}
