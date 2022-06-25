package com.ozapis.handler;

import com.ozapis.annotation.Feedback;
import com.ozapis.annotation.Grade;
import com.ozapis.model.Student;
import com.ozapis.model.Teacher;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReportHandlerTest {

    @Test
    void generateReport() {
        var teacher = new Teacher("firstName","lastName","email");
        var student = new Student("firstName","lastName","email");

        var report = ReportHandler.generateReport(
                teacher,
                student,
                "com.ozapis",
                76.5,
                Feedback.class, Grade.class
        );
        assertNotNull(report);
        assertEquals(teacher, report.getTeacher());
        assertEquals(student, report.getStudent());
    }

    @Test
    void getTotalGrades() {
        var expected = 30 + 75.5 + 25;//Values are in AnnotationTest.class
        assertEquals(expected, ReportHandler.getTotalGrades("com.ozapis"));
    }
}