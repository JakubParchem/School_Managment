package com.example.school.repository;

import com.example.school.model.Subject;
import com.example.school.model.Teacher;
import com.example.school.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SubjectRepositoryTest {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Test
    void testSaveAndFindById() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        teacherRepository.save(teacher);

        Subject subject = new Subject();
        subject.setName("Math 101");
        subject.setTeacher(teacher);

        Subject savedSubject = subjectRepository.save(subject);

        assertNotNull(savedSubject.getId());
        assertEquals("Math 101", savedSubject.getName());
        assertEquals(teacher, savedSubject.getTeacher());
    }

    @Test
    void testFindAll() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        teacherRepository.save(teacher);

        Subject subject1 = new Subject();
        subject1.setName("Math 101");
        subject1.setTeacher(teacher);

        Subject subject2 = new Subject();
        subject2.setName("Math 102");
        subject2.setTeacher(teacher);

        subjectRepository.save(subject1);
        subjectRepository.save(subject2);

        List<Subject> subjects = subjectRepository.findAll();

        assertEquals(2, subjects.size());
    }

    @Test
    void testDelete() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        teacherRepository.save(teacher);

        Subject subject = new Subject();
        subject.setName("Math 101");
        subject.setTeacher(teacher);

        Subject savedSubject = subjectRepository.save(subject);
        subjectRepository.delete(savedSubject);

        assertTrue(subjectRepository.findById(savedSubject.getId()).isEmpty());
    }

    @Test
    void testAddStudentsToSubject() {
        Teacher teacher = new Teacher();
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialization("Mathematics");

        teacherRepository.save(teacher);

        Student student = new Student();
        student.setName("John Doe");
        student.setEmail("john.doe@example.com");

        studentRepository.save(student);

        Subject subject = new Subject();
        subject.setName("Math 101");
        subject.setTeacher(teacher);

        List<Student> students = new ArrayList<>();
        students.add(student);

        subject.setStudents(students);

        Subject savedSubject = subjectRepository.save(subject);

        assertTrue(savedSubject.getStudents().contains(student));
    }
}
