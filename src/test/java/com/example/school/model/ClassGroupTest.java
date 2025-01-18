package com.example.school.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClassGroupTest {

    private ClassGroup classGroup;
    private Teacher mockTeacher;
    private List<Student> mockStudents;

    @BeforeEach
    void setUp() {
        mockTeacher = mock(Teacher.class);
        mockStudents = new ArrayList<>();
        mockStudents.add(mock(Student.class));
        mockStudents.add(mock(Student.class));

        classGroup = new ClassGroup(1L, "Math Group", 30, mockTeacher, mockStudents);
    }

    @Test
    void testGettersAndSetters() {
        // Test getters
        assertEquals(1L, classGroup.getId());
        assertEquals("Math Group", classGroup.getName());
        assertEquals(30, classGroup.getCapacity());
        assertEquals(mockTeacher, classGroup.getClass_teacher());
        assertEquals(mockStudents, classGroup.getStudents());

        // Test setters
        Teacher newTeacher = mock(Teacher.class);
        List<Student> newStudents = new ArrayList<>();

        classGroup.setId(2L);
        classGroup.setName("Science Group");
        classGroup.setCapacity(25);
        classGroup.setClass_teacher(newTeacher);
        classGroup.setStudents(newStudents);

        assertEquals(2L, classGroup.getId());
        assertEquals("Science Group", classGroup.getName());
        assertEquals(25, classGroup.getCapacity());
        assertEquals(newTeacher, classGroup.getClass_teacher());
        assertEquals(newStudents, classGroup.getStudents());
    }

    @Test
    void testValidateReturnsTrueForValidName() {
        classGroup.setName("Valid Name");
        assertTrue(classGroup.validate());
    }

    @Test
    void testValidateReturnsFalseForEmptyName() {
        classGroup.setName("");
        assertFalse(classGroup.validate());
    }

    @Test
    void testDefaultConstructor() {
        ClassGroup defaultClassGroup = new ClassGroup();
        assertNull(defaultClassGroup.getId());
        assertNull(defaultClassGroup.getName());
        assertEquals(0, defaultClassGroup.getCapacity());
        assertNull(defaultClassGroup.getClass_teacher());
        assertNull(defaultClassGroup.getStudents());
    }
}
