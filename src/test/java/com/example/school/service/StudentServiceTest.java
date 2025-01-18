package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setEnrollment(LocalDate.parse("2023-01-01"));
    }

    @Test
    void testCreateStudentValid() throws InvalidInputException {
        when(studentRepository.save(student)).thenReturn(student);

        Student createdStudent = studentService.createStudent(student);

        assertNotNull(createdStudent);
        assertEquals("John Doe", createdStudent.getName());
        verify(studentRepository, times(1)).save(student);  // Sprawdzamy, czy save został wywołany raz
    }

    @Test
    void testCreateStudentInvalid() {
        student.setName(null);

        assertThrows(InvalidInputException.class, () -> studentService.createStudent(student));
    }

    @Test
    void testGetStudentByIdFound() throws ResourceNotFoundException {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        Student foundStudent = studentService.getStudentById(1L);

        assertNotNull(foundStudent);
        assertEquals("John Doe", foundStudent.getName());
        verify(studentRepository, times(1)).findById(1L);
    }

    @Test
    void testGetStudentByIdNotFound() {
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.getStudentById(1L));
    }

    @Test
    void testUpdateStudentValid() throws ResourceNotFoundException {
        // Testowanie aktualizacji studenta
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setEmail("new.email@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        Student updated = studentService.updateStudent(1L, updatedStudent);

        assertEquals("new.email@example.com", updated.getEmail());
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void testUpdateStudentNotFound() {
        // Testowanie przypadku, gdy student do aktualizacji nie istnieje
        Student updatedStudent = new Student();
        updatedStudent.setId(1L);
        updatedStudent.setEmail("new.email@example.com");

        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.updateStudent(1L, updatedStudent));
    }

    @Test
    void testDeleteStudentValid() throws ResourceNotFoundException {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository, times(1)).deleteById(1L);  // Sprawdzamy, czy deleteById został wywołany
    }

    @Test
    void testDeleteStudentNotFound() {
        // Testowanie przypadku, gdy student do usunięcia nie istnieje
        when(studentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> studentService.deleteStudent(1L));
    }

    @Test
    void testGetAllStudents() {
        // Testowanie pobierania wszystkich studentów
        when(studentRepository.findAll()).thenReturn(List.of(student));

        List<Student> students = studentService.getAllStudents();

        assertNotNull(students);
        assertEquals(1, students.size());
        assertEquals("John Doe", students.get(0).getName());
        verify(studentRepository, times(1)).findAll();
    }
}
