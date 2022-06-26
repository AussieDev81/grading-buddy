package com.ozapis.handler;

import com.ozapis.annotation.Grade;
import com.ozapis.model.Report;
import com.ozapis.model.Student;
import com.ozapis.model.Teacher;

import java.lang.annotation.Annotation;
import java.util.Calendar;
import java.util.stream.Stream;

public interface ReportHandler {

    /**
     * Generate a report object containing all attributes associated with grading an assignment.<br>
     * An example of creating a new {@link Report} with 2 annotation classes:
     * <pre>
     * var report = new Report( teacher, student, "com.example", 79.5, Feedback.class, Grade.class );
     * </pre>
     * @param teacher The teacher grading the assignment
     * @param student The student who authored the assignment
     * @param packageName The package name where the assignment files can be found
     * @param overallGrade The overall grade being awarded for the assignment
     * @param annotations One or more annotation classes to be included in the report
     * @return A {@link Report} instance
     * @see AnnotationHandler
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html">More about varargs</a>
     */
    @SafeVarargs
    static Report generateReport(Teacher teacher, Student student, String packageName, double overallGrade, Class<? extends Annotation>... annotations){
        return new Report(
                Calendar.getInstance(),
                teacher,
                student,
                AnnotationHandler.getAnnotatedClasses(packageName, annotations),
                AnnotationHandler.getAnnotatedMethods(packageName, annotations),
                AnnotationHandler.getAnnotatedFields(packageName, annotations),
                overallGrade);
    }

    /**
     * Gets the total awarded grades from all classes, methods, and fields.<br>
     * All {@link Grade#marksAwarded()} values within the given package will be summed and returned as a double value.
     * @param packageName The package to scan
     * @return The sum of all awarded marks
     */
    static double getTotalGrades(String packageName) {
        return Stream.of(
                //Sum marks awarded at class level annotations
                AnnotationHandler.getAnnotatedClasses(packageName, Grade.class).stream()
                        .map(c -> c.getAnnotation(Grade.class).marksAwarded())
                        .mapToDouble(Double::doubleValue).sum(),
                //Sum marks awarded at method level annotations
                AnnotationHandler.getAnnotatedMethods(packageName, Grade.class).stream()
                        .map(m -> m.getAnnotation(Grade.class).marksAwarded())
                        .mapToDouble(Double::doubleValue).sum(),
                //Sum marks awarded at field level annotations
                AnnotationHandler.getAnnotatedFields(packageName, Grade.class).stream()
                        .map(m -> m.getAnnotation(Grade.class).marksAwarded())
                        .mapToDouble(Double::doubleValue).sum())
                //Total of all values
                .mapToDouble(Double::doubleValue).sum();
    }

}
