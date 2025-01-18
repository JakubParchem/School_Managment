package com.example.school.repository;

import com.example.school.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindById() {
        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");
        student.setEnrollmentDate(LocalDate.now());

        Student savedStudent = studentRepository.save(student);

        assertNotNull(savedStudent.getId());
        assertEquals("John Doe", savedStudent.getName());
        assertEquals("john.doe@example.com", savedStudent.getEmail());
    }

    @Test
    void testFindAll() {
        Student student1 = new Student();
        student1.setName("John Doe");
        student1.setEmail("john.doe@example.com");
        student1.setEnrollmentDate(LocalDate.now());

        Student student2 = new Student();
        student2.setName("Jane Doe");
        student2.setEmail("jane.doe@example.com");
        student2.setEnrollmentDate(LocalDate.now().minusDays(1));

        studentRepository.save(student1);
        studentRepository.save(student2);

        List<Student> students = studentRepository.findAll();

        assertEquals(2, students.size());
    }
}
