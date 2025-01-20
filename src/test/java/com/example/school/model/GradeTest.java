package com.example.school.model;

import com.example.school.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class GradeTest {

    private Grade grade;
    private Student student;
    private Subject subject;

    @BeforeEach
    void setUp() {
        student = new Student();
        student.setId(1L);
        subject = new Subject();
        subject.setId(1L);

        grade = new Grade();
    }

    @Test
    void testGradeConstructorValidValue() throws InvalidInputException {
        Grade grade = new Grade(1L, 3.5f, LocalDate.now(), student, subject);

        assertEquals(3.5f, grade.getValue());
        assertNotNull(grade.getDate());
        //assertEquals(student, grade.getStudent());
        assertEquals(subject, grade.getSubject());
    }

    @Test
    void testSetValueValid() throws InvalidInputException {
        grade.setValue(4.0f);

        assertEquals(4.0f, grade.getValue());
    }

    @Test
    void testValidateValidGrade() throws InvalidInputException {
        grade.setValue(4.0f);
        grade.setDate(LocalDate.now());
        //grade.setStudent(student);
        grade.setSubject(subject);

        assertTrue(grade.validate());
    }

    @Test
    void testValidateInvalidGrade() throws InvalidInputException {
        grade.setValue(3.0f); // Poprawna ocena
        grade.setDate(null);   // Niepoprawna data (null)
        grade.setStudent(student);
        grade.setSubject(subject);

        assertFalse(grade.validate());
    }

    @Test
    void testValidateInvalidStudent() throws InvalidInputException {
        grade.setValue(4.0f);
        grade.setDate(LocalDate.now());
        grade.setStudent(null); // Brak studenta
        grade.setSubject(subject);

        assertFalse(grade.validate());
    }

    @Test
    void testValidateInvalidSubject() throws InvalidInputException {
        grade.setValue(4.0f);
        grade.setDate(LocalDate.now());
        grade.setStudent(student);
        grade.setSubject(null);

        assertFalse(grade.validate());
    }


    @Test
    void testValidatel() throws InvalidInputException {
        grade.setValue(3.0f);
        grade.setDate(LocalDate.now());
        grade.setStudent(student);
        grade.setSubject(subject);

        assertTrue(grade.validate());
    }

    @Test
    void testValidateValueFromAllowedValues() throws InvalidInputException {
        // Testujemy metodę validateValue() z dozwolonymi wartościami
        float[] allowedValues = {2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f, 5.0f};

        for (float value : allowedValues) {
            assertTrue(grade.validateValue(value));  // Oczekujemy, że każda wartość będzie poprawna
        }
    }

    @Test
    void testValidateValueInvalid() throws InvalidInputException {
        // Testujemy metodę validateValue() z wartością, która nie jest dozwolona
        assertFalse(grade.validateValue(3.2f));  // Niewłaściwa wartość
        assertFalse(grade.validateValue(6.0f));  // Poza zakresem
    }
}
