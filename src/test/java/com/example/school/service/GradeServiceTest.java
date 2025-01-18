package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Grade;
import com.example.school.model.Student;
import com.example.school.model.Subject;
import com.example.school.repository.GradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GradeServiceTest {

    @Mock
    private GradeRepository gradeRepository;

    @InjectMocks
    private GradeService gradeService;

    private Grade grade;
    private Student student;
    private Subject subject;

    @BeforeEach
    void setUp() throws InvalidInputException {
        MockitoAnnotations.openMocks(this);

        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");

        subject = new Subject();
        subject.setId(1L);
        subject.setName("Mathematics");

        grade = new Grade();
        grade.setId(1L);
        grade.setValue(4.0f);
        grade.setDate(LocalDate.of(2025, 1, 18));
        grade.setStudent(student);
        grade.setSubject(subject);
    }

    @Test
    void testGetAllGrades() {
        List<Grade> grades = new ArrayList<>();
        grades.add(grade);

        when(gradeRepository.findAll()).thenReturn(grades);

        List<Grade> result = gradeService.getAllGrades();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(4.0f, result.get(0).getValue());
    }

    @Test
    void testGetGradeById() throws ResourceNotFoundException {
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        Grade result = gradeService.getGradeById(1L);

        assertNotNull(result);
        assertEquals(4.0f, result.getValue());
        assertEquals(student, result.getStudent());
    }

    @Test
    void testGetGradeByIdNotFound() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> gradeService.getGradeById(1L));
    }

    @Test
    void testCreateGrade() throws InvalidInputException {
        when(gradeRepository.save(grade)).thenReturn(grade);

        Grade result = gradeService.createGrade(grade);

        assertNotNull(result);
        assertEquals(4.0f, result.getValue());
        assertEquals("John Doe", result.getStudent().getName());
        verify(gradeRepository, times(1)).save(grade);
    }

    @Test
    void testCreateGradeInvalidInput() throws InvalidInputException {
        grade.setValue(6.0f);  // Invalid grade value

        assertThrows(InvalidInputException.class, () -> gradeService.createGrade(grade));
    }

    @Test
    void testUpdateGrade() throws ResourceNotFoundException, InvalidInputException {
        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);
        updatedGrade.setValue(5.0f);
        updatedGrade.setDate(LocalDate.of(2025, 2, 1));
        updatedGrade.setStudent(student);
        updatedGrade.setSubject(subject);

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));
        when(gradeRepository.save(updatedGrade)).thenReturn(updatedGrade);

        assertDoesNotThrow(()-> gradeService.updateGrade(1L, updatedGrade));
    }

    @Test
    void testUpdateGradeNotFound() {
        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);

        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> gradeService.updateGrade(1L, updatedGrade));
    }

    @Test
    void testUpdateGradeInvalidInput() throws InvalidInputException {
        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);
        updatedGrade.setValue(6.0f);  // Invalid grade value

        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        assertThrows(InvalidInputException.class, () -> gradeService.updateGrade(1L, updatedGrade));
    }

    @Test
    void testDeleteGrade() throws ResourceNotFoundException {
        when(gradeRepository.findById(1L)).thenReturn(Optional.of(grade));

        gradeService.deleteGrade(1L);

        verify(gradeRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteGradeNotFound() {
        when(gradeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> gradeService.deleteGrade(1L));
    }
}
