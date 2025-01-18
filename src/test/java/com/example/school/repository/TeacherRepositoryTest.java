package com.example.school.repository;

import com.example.school.model.Teacher;
import com.example.school.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TeacherRepositoryTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Test
    void testSaveAndFindById() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        Teacher savedTeacher = teacherRepository.save(teacher);

        assertNotNull(savedTeacher.getId());
        assertEquals("Mr. Smith", savedTeacher.getName());
        assertEquals("mr.smith@example.com", savedTeacher.getEmail());
        assertEquals("Mathematics", savedTeacher.getSubjectSpecialization());
    }

    @Test
    void testFindAll() {
        Teacher teacher1 = new Teacher();
        teacher1.setName("Mr. Smith");
        teacher1.setEmail("mr.smith@example.com");
        teacher1.setSubjectSpecialization("Mathematics");

        Teacher teacher2 = new Teacher();
        teacher2.setName("Mrs. Johnson");
        teacher2.setEmail("mrs.johnson@example.com");
        teacher2.setSubjectSpecialization("English");

        teacherRepository.save(teacher1);
        teacherRepository.save(teacher2);

        List<Teacher> teachers = teacherRepository.findAll();

        assertEquals(2, teachers.size());
    }

    @Test
    void testDelete() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        Teacher savedTeacher = teacherRepository.save(teacher);
        teacherRepository.delete(savedTeacher);

        assertTrue(teacherRepository.findById(savedTeacher.getId()).isEmpty());
    }

    @Test
    void testAddSubjectsToTeacher() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        Teacher savedTeacher = teacherRepository.save(teacher);

        Subject subject = new Subject();
        subject.setName("Math 101");
        subject.setTeacher(savedTeacher);

        subjectRepository.save(subject);

        assertTrue(savedTeacher.getSubjects().contains(subject));
    }
}
