package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.*;
import com.example.school.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MainController {
    @Autowired
    private ClassGroupController classGroupController;
    @Autowired
    private StudentController studentController;
    @Autowired
    private GradeController gradeController;
    @Autowired
    private TeacherController teacherController;
    @Autowired
    private SubjectController subjectController;

    public void deleteAll() throws ResourceNotFoundException {
        studentController.deleteAllStudents();
        classGroupController.deleteAllClassGroups();
        gradeController.deleteAllGrades();
        subjectController.deleteAllSubjects();
        teacherController.deleteAllTeachers();
    }
    public void createSample() throws InvalidInputException, ResourceNotFoundException {
        Teacher teacher=new Teacher(1L,"Mr. Example","example@mail.com","Math");
        teacherController.createTeacher(teacher);
        Subject subject=new Subject("Math",teacher);
        subjectController.createSubject(subject);
        Grade grade=new Grade(1L,2.5f, LocalDate.now(),subject);
        gradeController.createGrade(grade);
        ClassGroup classGroup=new ClassGroup(1L,"12c",44,teacher);
        classGroupController.createClassGroup(classGroup);
        List<Subject> subjects=new ArrayList<>();
        List<Grade> grades=new ArrayList<>();
        subjects.add(subject);
        grades.add(grade);
        Student student=new Student(1L,"Johnny","jhonny@mail.com",LocalDate.now(),classGroup,subjects,grades);
        studentController.createStudent(student);

    }
}
