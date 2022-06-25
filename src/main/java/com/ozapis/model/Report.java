package com.ozapis.model;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This {@link Report} class is used to gather results after a teacher has graded a student's project/assignment
 */
public class Report {

    private Calendar dateGraded;
    private Teacher teacher;
    private Student student;
    private Set<Class<?>> annotatedClasses;
    private Set<Method> annotatedMethods;
    private Set<Field> annotatedFields;
    private double overallGrade;

    /**
     * Default {@link Report} constructor creates a new report object with basic default values
     */
    public Report() {
        this.dateGraded = Calendar.getInstance();
        this.teacher = null;
        this.student = null;
        this.annotatedClasses = new LinkedHashSet<>();
        this.annotatedMethods = new LinkedHashSet<>();
        this.annotatedFields = new LinkedHashSet<>();
        this.overallGrade = 0;
    }

    /**
     * Create a new Report instance
     * @param dateGraded The date that the report was created
     * @param teacher The {@link Teacher} responsible for grading the project/assignment
     * @param student The student whose work is being assessed
     * @param annotatedClasses A set of class objects having a given annotation
     * @param annotatedMethods A set of methods having a given annotation
     * @param annotatedFields A set of fields having the given annotation
     * @param overallGrade The overall grade being awarded to the student
     */
    public Report(Calendar dateGraded, Teacher teacher, Student student, Set<Class<?>> annotatedClasses, Set<Method> annotatedMethods, Set<Field> annotatedFields, double overallGrade) {
        this.dateGraded = dateGraded;
        this.teacher = teacher;
        this.student = student;
        this.annotatedClasses = annotatedClasses;
        this.annotatedMethods = annotatedMethods;
        this.annotatedFields = annotatedFields;
        this.overallGrade = overallGrade;
    }

    public Calendar getDateGraded() {
        return dateGraded;
    }

    public void setDateGraded(Calendar dateGraded) {
        this.dateGraded = dateGraded;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Set<Class<?>> getAnnotatedClasses() {
        return annotatedClasses;
    }

    public void setAnnotatedClasses(Set<Class<?>> annotatedClasses) {
        this.annotatedClasses = annotatedClasses;
    }

    public Set<Method> getAnnotatedMethods() {
        return annotatedMethods;
    }

    public void setAnnotatedMethods(Set<Method> annotatedMethods) {
        this.annotatedMethods = annotatedMethods;
    }

    public Set<Field> getAnnotatedFields() {
        return annotatedFields;
    }

    public void setAnnotatedFields(Set<Field> annotatedFields) {
        this.annotatedFields = annotatedFields;
    }

    public double getOverallGrade() {
        return overallGrade;
    }

    public void setOverallGrade(double overallGrade) {
        this.overallGrade = overallGrade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (Double.compare(report.overallGrade, overallGrade) != 0) return false;
        if (!Objects.equals(dateGraded, report.dateGraded)) return false;
        if (!Objects.equals(teacher, report.teacher)) return false;
        if (!Objects.equals(student, report.student)) return false;
        if (!Objects.equals(annotatedClasses, report.annotatedClasses))
            return false;
        if (!Objects.equals(annotatedMethods, report.annotatedMethods))
            return false;
        return Objects.equals(annotatedFields, report.annotatedFields);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = dateGraded != null ? dateGraded.hashCode() : 0;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (annotatedClasses != null ? annotatedClasses.hashCode() : 0);
        result = 31 * result + (annotatedMethods != null ? annotatedMethods.hashCode() : 0);
        result = 31 * result + (annotatedFields != null ? annotatedFields.hashCode() : 0);
        temp = Double.doubleToLongBits(overallGrade);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Report{" +
                "dateGraded=" + dateGraded.getTime() +
                ", teacher=" + teacher +
                ", student=" + student +
                ", annotatedClasses=" + annotatedClasses +
                ", annotatedMethods=" + annotatedMethods +
                ", annotatedFields=" + annotatedFields +
                ", overallGrade=" + overallGrade +
                '}';
    }
}
