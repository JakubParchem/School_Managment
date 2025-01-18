package com.example.school.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubjectTest {

    private Subject subject;
    private Teacher mockTeacher;
    private List<Student> mockStudents;

    @BeforeEach
    void setUp() {
        mockTeacher = mock(Teacher.class);
        mockStudents = new ArrayList<>();
        mockStudents.add(mock(Student.class));
        mockStudents.add(mock(Student.class));

        subject = new Subject("Mathematics", mockTeacher, mockStudents);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertEquals("Mathematics", subject.getName());
        assertEquals(mockTeacher, subject.getTeacher());
        assertEquals(mockStudents, subject.getStudents());

        // Test setters
        subject.setName("Physics");
        Teacher newTeacher = mock(Teacher.class);
        subject.setTeacher(newTeacher);
        List<Student> newStudents = new ArrayList<>();
        subject.setStudents(newStudents);

        assertEquals("Physics", subject.getName());
        assertEquals(newTeacher, subject.getTeacher());
        assertEquals(newStudents, subject.getStudents());
    }

    @Test
    void testValidateReturnsTrueForValidSubject() {
        assertTrue(subject.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyName() {
        subject.setName("");
        assertFalse(subject.validate());
    }

    @Test
    void testValidateReturnsFalseForNullTeacher() {
        subject.setTeacher(null);
        assertFalse(subject.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyStudentsList() {
        subject.setStudents(new ArrayList<>());
        assertFalse(subject.validate());
    }

    @Test
    void testDefaultConstructor() {
        Subject defaultSubject = new Subject();
        assertNull(defaultSubject.getId());
        assertNull(defaultSubject.getName());
        assertNull(defaultSubject.getTeacher());
        assertNull(defaultSubject.getStudents());
    }

    @Test
    void testSetStudentsWithNullList() {
        subject.setStudents(null);
        assertNull(subject.getStudents());
    }

    @Test
    void testSetStudentsWithEmptyList() {
        List<Student> emptyList = new ArrayList<>();
        subject.setStudents(emptyList);
        assertEquals(0, subject.getStudents().size());
    }
}
