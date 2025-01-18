package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.model.Student;
import com.example.school.model.Teacher;
import com.example.school.repository.ClassGroupRepository;
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

class ClassGroupServiceTest {

    @Mock
    private ClassGroupRepository classGroupRepository;

    @InjectMocks
    private ClassGroupService classGroupService;

    private ClassGroup classGroup;
    private Teacher teacher;
    private List<Student> students;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        students = new ArrayList<>();
        Student student = new Student();
        student.setId(1L);
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        students.add(student);

        classGroup = new ClassGroup();
        classGroup.setId(1L);
        classGroup.setName("Mathematics Group");
        classGroup.setCapacity(30);
        classGroup.setClass_teacher(teacher);
        classGroup.setStudents(students);
    }

    @Test
    void testGetAllClassGroups() {
        List<ClassGroup> classGroups = new ArrayList<>();
        classGroups.add(classGroup);

        when(classGroupRepository.findAll()).thenReturn(classGroups);

        List<ClassGroup> result = classGroupService.getAllClassGroups();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Mathematics Group", result.get(0).getName());
    }

    @Test
    void testGetClassGroupById() throws ResourceNotFoundException {
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        ClassGroup result = classGroupService.getClassGroupById(1L);

        assertNotNull(result);
        assertEquals("Mathematics Group", result.getName());
    }

    @Test
    void testGetClassGroupByIdNotFound() {
        when(classGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> classGroupService.getClassGroupById(1L));
    }

    @Test
    void testCreateClassGroup() throws InvalidInputException {
        when(classGroupRepository.save(classGroup)).thenReturn(classGroup);

        ClassGroup result = classGroupService.createClassGroup(classGroup);

        assertNotNull(result);
        assertEquals("Mathematics Group", result.getName());
        verify(classGroupRepository, times(1)).save(classGroup);
    }

    @Test
    void testCreateClassGroupInvalidInput() {
        classGroup.setName("");  // Invalid name

        assertThrows(InvalidInputException.class, () -> classGroupService.createClassGroup(classGroup));
    }

    @Test
    void testUpdateClassGroup() throws ResourceNotFoundException, InvalidInputException {
        ClassGroup updatedClassGroup = new ClassGroup();
        updatedClassGroup.setId(1L);
        updatedClassGroup.setName("Updated Mathematics Group");
        updatedClassGroup.setCapacity(35);
        updatedClassGroup.setClass_teacher(teacher);
        updatedClassGroup.setStudents(students);

        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));
        when(classGroupRepository.save(updatedClassGroup)).thenReturn(updatedClassGroup);


        assertDoesNotThrow(()->classGroupService.updateClassGroup(updatedClassGroup, 1L));
    }

    @Test
    void testUpdateClassGroupNotFound() {
        ClassGroup updatedClassGroup = new ClassGroup();
        updatedClassGroup.setId(1L);

        when(classGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> classGroupService.updateClassGroup(updatedClassGroup, 1L));
    }

    @Test
    void testUpdateClassGroupInvalidInput() {
        ClassGroup updatedClassGroup = new ClassGroup();
        updatedClassGroup.setId(1L);
        updatedClassGroup.setName("");  // Invalid name

        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        assertThrows(InvalidInputException.class, () -> classGroupService.updateClassGroup(updatedClassGroup, 1L));
    }

    @Test
    void testDeleteClassGroup() throws ResourceNotFoundException {
        when(classGroupRepository.findById(1L)).thenReturn(Optional.of(classGroup));

        classGroupService.deleteClassGroup(1L);

        verify(classGroupRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteClassGroupNotFound() {
        when(classGroupRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> classGroupService.deleteClassGroup(1L));
    }
}
