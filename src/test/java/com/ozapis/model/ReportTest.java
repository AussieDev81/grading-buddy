package com.ozapis.model;

import com.ozapis.annotation.Feedback;
import com.ozapis.handler.AnnotationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private Report report;
    private Calendar dateGraded;
    private Teacher teacher;
    private Student student;
    private Set<Class<?>> classes;
    private Set<Method> methods;
    private Set<Field> fields;
    private double overallGrade;

    @BeforeEach
    void setUp() {
        report = new Report();
        dateGraded = Calendar.getInstance();
        report.setDateGraded(dateGraded);
        teacher = new Teacher("TeacherFirstName","TeacherLastName","TeacherEmail");
        student = new Student("StudentFirstName","StudentLastName","StudentEmail");
        report.setTeacher(teacher);
        report.setStudent(student);
        classes = AnnotationHandler.getAnnotatedClasses("com.ozapis", Feedback.class);
        methods = AnnotationHandler.getAnnotatedMethods("com.ozapis", Feedback.class);
        fields = AnnotationHandler.getAnnotatedFields("com.ozapis", Feedback.class);
        overallGrade = 75.75;
        report.setOverallGrade(overallGrade);
    }

    @Test
    void testAllArgsConstructor(){
        var allArgsReport = new Report(dateGraded, teacher, student, classes, methods, fields, overallGrade);
        report.setAnnotatedClasses(classes);
        report.setAnnotatedMethods(methods);
        report.setAnnotatedFields(fields);
        assertEquals(report, allArgsReport);
    }
    @Test
    void getDateGraded() {
        assertEquals(dateGraded, report.getDateGraded());
    }

    @Test
    void setDateGraded() {
        Calendar modifiedDate = Calendar.getInstance();
        modifiedDate.set(2022,Calendar.JUNE,20);
        assertNotEquals(modifiedDate, dateGraded);
        report.setDateGraded(modifiedDate);
        assertEquals(modifiedDate, report.getDateGraded());
    }

    @Test
    void getTeacher() {
        assertEquals(teacher, report.getTeacher());
    }

    @Test
    void setTeacher() {
        var newTeacher = new Teacher("firstName","lastName","email");
        report.setTeacher(newTeacher);
        assertEquals(newTeacher, report.getTeacher());
    }

    @Test
    void getStudent() {
        assertEquals(student, report.getStudent());
    }

    @Test
    void setStudent() {
        var newStudent = new Student("firstName","lastName","email");
        report.setStudent(newStudent);
        assertEquals(newStudent, report.getStudent());
    }

    @Test
    void getAnnotatedClasses() {
        assertEquals(classes, AnnotationHandler.getAnnotatedClasses("com.ozapis", Feedback.class));
    }

    @Test
    void setAnnotatedClasses() {
        report.setAnnotatedClasses(null);
        assertNull(report.getAnnotatedClasses());
    }

    @Test
    void getAnnotatedMethods() {
        assertEquals(methods, AnnotationHandler.getAnnotatedMethods("com.ozapis", Feedback.class));
    }

    @Test
    void setAnnotatedMethods() {
        report.setAnnotatedMethods(null);
        assertNull(report.getAnnotatedMethods());
    }

    @Test
    void getAnnotatedFields() {
        assertEquals(fields, AnnotationHandler.getAnnotatedFields("com.ozapis", Feedback.class));
    }

    @Test
    void setAnnotatedFields() {
        report.setAnnotatedFields(null);
        assertNull(report.getAnnotatedFields());
    }

    @Test
    void getOverallGrade() {
        assertEquals(overallGrade, report.getOverallGrade());
    }

    @Test
    void setOverallGrade() {
        overallGrade = 27.55;
        report.setOverallGrade(overallGrade);
        assertEquals(overallGrade, report.getOverallGrade());
    }
}