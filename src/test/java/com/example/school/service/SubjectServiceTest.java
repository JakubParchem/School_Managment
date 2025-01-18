package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Subject;
import com.example.school.model.Teacher;
import com.example.school.repository.SubjectRepository;
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

class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;

    @InjectMocks
    private SubjectService subjectService;

    private Subject subject;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");
        subject.setTeacher(teacher);
        subject.setStudents(new ArrayList<>());
    }

    @Test
    void testCreateSubject() throws InvalidInputException {
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject result = subjectService.createSubject(subject);

        assertNotNull(result);
        assertEquals("Mathematics", result.getName());
        assertEquals("Mr. Smith", result.getTeacher().getName());
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void testCreateSubjectInvalidInput() {
        subject.setName(""); // Invalid input: empty name

        assertThrows(InvalidInputException.class, () -> subjectService.createSubject(subject));
    }

    @Test
    void testGetSubjectById() throws ResourceNotFoundException {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        Subject result = subjectService.getSubjectById(1L);

        assertNotNull(result);
        assertEquals("Mathematics", result.getName());
        assertEquals("Mr. Smith", result.getTeacher().getName());
    }

    @Test
    void testGetSubjectByIdNotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.getSubjectById(1L));
    }

    @Test
    void testUpdateSubject() throws ResourceNotFoundException, InvalidInputException {
        Subject updatedSubject = new Subject();
        updatedSubject.setName("Physics");
        updatedSubject.setTeacher(teacher);

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));
        when(subjectRepository.save(any(Subject.class))).thenReturn(subject);

        Subject result = subjectService.updateSubject(1L, updatedSubject);

        assertNotNull(result);
        assertEquals("Physics", result.getName());
        verify(subjectRepository, times(1)).save(any(Subject.class));
    }

    @Test
    void testUpdateSubjectNotFound() {
        Subject updatedSubject = new Subject();
        updatedSubject.setName("Physics");

        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.updateSubject(1L, updatedSubject));
    }

    @Test
    void testUpdateSubjectInvalidInput() {
        Subject updatedSubject = new Subject();
        updatedSubject.setName(""); // Invalid input: empty name

        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        assertThrows(InvalidInputException.class, () -> subjectService.updateSubject(1L, updatedSubject));
    }

    @Test
    void testDeleteSubject() throws ResourceNotFoundException {
        when(subjectRepository.findById(1L)).thenReturn(Optional.of(subject));

        subjectService.deleteSubject(1L);

        verify(subjectRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteSubjectNotFound() {
        when(subjectRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> subjectService.deleteSubject(1L));
    }

    @Test
    void testGetAllSubjects() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);

        when(subjectRepository.findAll()).thenReturn(subjects);

        List<Subject> result = subjectService.getAllSubjects();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mathematics", result.get(0).getName());
    }
}
