package com.ozapis.handler;

import com.ozapis.annotation.Feedback;
import com.ozapis.annotation.Grade;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Grade(marksAvailable = 20, marksAwarded = 30)
@Feedback(comment = "getAnnotatedClasses() feedback test")
class AnnotationHandlerTest {

    @Feedback(comment = "getAnnotatedFields() feedback test")
    @Grade(marksAvailable = 100, marksAwarded = 75.5)
    private String field;

    @Test
    void getAnnotatedFields() {
        var packageName = "com.ozapis";
        var fields = AnnotationHandler.getAnnotatedFields(packageName, Feedback.class, Grade.class);

        var feedbackAnnotatedFields = fields.stream()
                .filter(field -> field.isAnnotationPresent(Feedback.class))
                .filter(field -> field.getAnnotation(Feedback.class).comment().equals("getAnnotatedFields() feedback test"))
                .count();
        assertTrue(feedbackAnnotatedFields >= 1, "No feedback annotations were found matching the given params");

        var gradeAnnotatedMethods = fields.stream()
                .filter(field -> field.isAnnotationPresent(Grade.class))
                .filter(field -> field.getAnnotation(Grade.class).marksAwarded() == 75.5)
                .count();
        assertTrue(gradeAnnotatedMethods >= 1, "No grade annotations were found matching the given params");
    }

    @Grade(marksAvailable = 20, marksAwarded = 25)
    @Feedback(comment = "getAnnotatedMethods() feedback test")
    @Test
    void getAnnotatedMethods() {
        var packageName = "com.ozapis";
        var methods = AnnotationHandler.getAnnotatedMethods(packageName, Feedback.class, Grade.class);

        var feedbackAnnotatedMethods = methods.stream()
                .filter(method -> method.isAnnotationPresent(Feedback.class))
                .filter(method -> method.getAnnotation(Feedback.class).comment().equals("getAnnotatedMethods() feedback test"))
                .count();
        assertTrue(feedbackAnnotatedMethods >= 1, "No feedback annotations were found matching the given params");

        var gradeAnnotatedMethods = methods.stream()
                .filter(method -> method.isAnnotationPresent(Grade.class))
                .filter(method -> method.getAnnotation(Grade.class).marksAwarded() == 25)
                .count();
        assertTrue(gradeAnnotatedMethods >= 1, "No grade annotations were found matching the given params");
    }

    @Test
    void getAnnotatedClasses() {
        var packageName = "com.ozapis";
        var classes = AnnotationHandler.getAnnotatedClasses(packageName, Feedback.class, Grade.class);

        var feedbackAnnotatedClasses = classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Feedback.class))
                .filter(clazz -> clazz.getAnnotation(Feedback.class).comment().equals("getAnnotatedClasses() feedback test"))
                .count();
        assertTrue(feedbackAnnotatedClasses >= 1, "No feedback annotations were found matching the given params");

        var gradeAnnotatedClasses = classes.stream()
                .filter(clazz -> clazz.isAnnotationPresent(Grade.class))
                .filter(clazz -> clazz.getAnnotation(Grade.class).marksAwarded() == 30)
                .count();
        assertTrue(gradeAnnotatedClasses >= 1, "No grade annotations were found matching the given params");
    }

}