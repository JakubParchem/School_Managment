package com.example.school.repository;

import com.example.school.exception.InvalidInputException;
import com.example.school.model.Grade;
import com.example.school.model.Student;
import com.example.school.model.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GradeRepositoryTest {

    @Autowired
    private GradeRepository gradeRepository;

    @Test
    void testSaveAndFindById() throws InvalidInputException {
        Grade grade = new Grade();
        grade.setValue(4.5f);
        grade.setDate(LocalDate.now());

        Grade savedGrade = gradeRepository.save(grade);

        assertNotNull(savedGrade.getId());
        assertEquals(4.5f, savedGrade.getValue());
        assertEquals(LocalDate.now(), savedGrade.getDate());
    }

    @Test
    void testFindAll() throws InvalidInputException {
        Grade grade1 = new Grade();
        grade1.setValue(3.0f);
        grade1.setDate(LocalDate.now());

        Grade grade2 = new Grade();
        grade2.setValue(5.0f);
        grade2.setDate(LocalDate.now().minusDays(1));

        gradeRepository.save(grade1);
        gradeRepository.save(grade2);

        List<Grade> grades = gradeRepository.findAll();

        assertEquals(2, grades.size());
    }
}
