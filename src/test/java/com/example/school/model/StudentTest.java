package com.example.school.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentTest {

    private Student student;
    private ClassGroup mockClassGroup;
    private List<Subject> mockSubjects;

    @BeforeEach
    void setUp() {
        mockClassGroup = mock(ClassGroup.class);
        mockSubjects = new ArrayList<>();
        mockSubjects.add(mock(Subject.class));
        mockSubjects.add(mock(Subject.class));

        student = new Student(1L, "John Doe", "john.doe@example.com", LocalDate.of(2023, 9, 1), mockClassGroup, mockSubjects);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertEquals(1L, student.getId());
        assertEquals("John Doe", student.getName());
        assertEquals("john.doe@example.com", student.getEmail());
        assertEquals(LocalDate.of(2023, 9, 1), student.getEnrollmentDate());
        assertEquals(mockClassGroup, student.getClassGroup());
        assertEquals(mockSubjects, student.getSubjects());

        // Test setters
        student.setId(2L);
        student.setName("Jane Doe");
        student.setEmail("jane.doe@example.com");
        student.setEnrollmentDate(LocalDate.of(2024, 1, 1));
        ClassGroup newClassGroup = mock(ClassGroup.class);
        List<Subject> newSubjects = new ArrayList<>();
        student.setClassGroup(newClassGroup);
        student.setSubjects(newSubjects);

        assertEquals(2L, student.getId());
        assertEquals("Jane Doe", student.getName());
        assertEquals("jane.doe@example.com", student.getEmail());
        assertEquals(LocalDate.of(2024, 1, 1), student.getEnrollmentDate());
        assertEquals(newClassGroup, student.getClassGroup());
        assertEquals(newSubjects, student.getSubjects());
    }

    @Test
    void testValidateReturnsTrueForValidStudent() {
        assertTrue(student.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyName() {
        student.setName("");
        assertFalse(student.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyEmail() {
        student.setEmail("");
        assertFalse(student.validate());
    }

    @Test
    void testValidateReturnsFalseForNullEnrollmentDate() {
        student.setEnrollmentDate(null);
        assertFalse(student.validate());
    }

    @Test
    void testValidateReturnsFalseForNullId() {
        student.setId(null);
        assertFalse(student.validate());
    }

    @Test
    void testDefaultConstructor() {
        Student defaultStudent = new Student();
        assertNull(defaultStudent.getId());
        assertNull(defaultStudent.getName());
        assertNull(defaultStudent.getEmail());
        assertNull(defaultStudent.getEnrollmentDate());
        assertNull(defaultStudent.getClassGroup());
        assertNull(defaultStudent.getSubjects());
    }

    @Test
    void testSetSubjectsWithEmptyList() {
        List<Subject> emptySubjects = new ArrayList<>();
        student.setSubjects(emptySubjects);
        assertEquals(0, student.getSubjects().size());
    }

    @Test
    void testSetClassGroup() {
        ClassGroup newClassGroup = mock(ClassGroup.class);
        student.setClassGroup(newClassGroup);
        assertEquals(newClassGroup, student.getClassGroup());
    }
}
