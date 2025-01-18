package com.example.school.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherTest {

    private Teacher teacher;
    private List<Subject> mockSubjects;

    @BeforeEach
    void setUp() {
        mockSubjects = new ArrayList<>();
        mockSubjects.add(mock(Subject.class));
        mockSubjects.add(mock(Subject.class));

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("John Smith");
        teacher.setEmail("john.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");
        teacher.setSubjects(mockSubjects);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertEquals(1L, teacher.getId());
        assertEquals("John Smith", teacher.getName());
        assertEquals("john.smith@example.com", teacher.getEmail());
        assertEquals("Mathematics", teacher.getSubjectSpecialization());
        assertEquals(mockSubjects, teacher.getSubjects());

        // Test setters
        teacher.setId(2L);
        teacher.setName("Jane Doe");
        teacher.setEmail("jane.doe@example.com");
        teacher.setSubjectSpecialization("Physics");
        List<Subject> newSubjects = new ArrayList<>();
        teacher.setSubjects(newSubjects);

        assertEquals(2L, teacher.getId());
        assertEquals("Jane Doe", teacher.getName());
        assertEquals("jane.doe@example.com", teacher.getEmail());
        assertEquals("Physics", teacher.getSubjectSpecialization());
        assertEquals(newSubjects, teacher.getSubjects());
    }

    @Test
    void testValidateReturnsTrueForValidTeacher() {
        assertTrue(teacher.validate());
    }

    @Test
    void testValidateReturnsFalseForNullId() {
        teacher.setId(null);
        assertFalse(teacher.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyName() {
        teacher.setName("");
        assertFalse(teacher.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyEmail() {
        teacher.setEmail("");
        assertFalse(teacher.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptySubjectSpecialization() {
        teacher.setSubjectSpecialization("");
        assertFalse(teacher.validate());
    }

    @Test
    void testDefaultConstructor() {
        Teacher defaultTeacher = new Teacher();
        assertNull(defaultTeacher.getId());
        assertNull(defaultTeacher.getName());
        assertNull(defaultTeacher.getEmail());
        assertNull(defaultTeacher.getSubjectSpecialization());
        assertNull(defaultTeacher.getSubjects());
    }

    @Test
    void testSetSubjectsWithNullList() {
        teacher.setSubjects(null);
        assertNull(teacher.getSubjects());
    }

    @Test
    void testSetSubjectsWithEmptyList() {
        List<Subject> emptySubjects = new ArrayList<>();
        teacher.setSubjects(emptySubjects);
        assertEquals(0, teacher.getSubjects().size());
    }
}
