package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Teacher;
import com.example.school.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherService teacherService;

    private Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");
    }

    @Test
    void testCreateTeacher() throws InvalidInputException {
        when(teacherRepository.save(any(Teacher.class))).thenReturn(teacher);

        Teacher result = teacherService.createTeacher(teacher);

        assertNotNull(result);
        assertEquals("Mr. Smith", result.getName());
        assertEquals("mr.smith@example.com", result.getEmail());
        assertEquals("Mathematics", result.getSubjectSpecialization());
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    void testCreateTeacherInvalidInput() {
        teacher.setName(""); // Invalid input: empty name

        assertThrows(InvalidInputException.class, () -> teacherService.createTeacher(teacher));
    }

    @Test
    void testGetTeacherById() throws ResourceNotFoundException {
        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        Teacher result = teacherService.getTeacherById(1L);

        assertNotNull(result);
        assertEquals("Mr. Smith", result.getName());
        assertEquals("mr.smith@example.com", result.getEmail());
        assertEquals("Mathematics", result.getSubjectSpecialization());
    }

    @Test
    void testGetTeacherByIdNotFound() {
        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> teacherService.getTeacherById(1L));
    }

    @Test
    void testUpdateTeacher() throws ResourceNotFoundException, InvalidInputException {
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setName("Mr. John");
        updatedTeacher.setEmail("mr.john@example.com");
        updatedTeacher.setSubjectSpecialization("Physics");

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));
        when(teacherRepository.save(any(Teacher.class))).thenReturn(updatedTeacher);

        Teacher result = teacherService.updateTeacher(1L, updatedTeacher);

        assertNotNull(result);
        assertEquals("Mr. John", result.getName());
        assertEquals("mr.john@example.com", result.getEmail());
        assertEquals("Physics", result.getSubjectSpecialization());
        verify(teacherRepository, times(1)).save(any(Teacher.class));
    }

    @Test
    void testUpdateTeacherNotFound() {
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setName("Mr. John");

        when(teacherRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> teacherService.updateTeacher(1L, updatedTeacher));
    }

    @Test
    void testUpdateTeacherInvalidInput() {
        Teacher updatedTeacher = new Teacher();
        updatedTeacher.setName(""); // Invalid input: empty name

        when(teacherRepository.findById(1L)).thenReturn(Optional.of(teacher));

        assertThrows(InvalidInputException.class, () -> teacherService.updateTeacher(1L, updatedTeacher));
    }

    @Test
    void testDeleteTeacher() throws ResourceNotFoundException {
        when(teacherRepository.existsById(1L)).thenReturn(true);

        teacherService.deleteTeacher(1L);

        verify(teacherRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteTeacherNotFound() {
        when(teacherRepository.existsById(1L)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> teacherService.deleteTeacher(1L));
    }

    @Test
    void testGetAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(teacher);

        when(teacherRepository.findAll()).thenReturn(teachers);

        List<Teacher> result = teacherService.getAllTeachers();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mr. Smith", result.get(0).getName());
    }
}
