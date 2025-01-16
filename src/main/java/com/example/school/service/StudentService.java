package com.example.school.service;

import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student){
        if(!student.validate()){
            throw new IllegalArgumentException("id, name, email and enrollment are required fields");
        }
        return studentRepository.save(student);
    }
    public Student getStudentById(Long id){
        if(!studentRepository.existsById(id)){
            throw new IllegalArgumentException("could not find student with id: "+id);
        }
        return studentRepository.findById(id).get();
    }
    public Student updateStudent(Long actualStudentId, Student updatedStudent){
        Student actualStudent = getStudentById(actualStudentId);
        if(!actualStudent.getEmail().equals(updatedStudent.getEmail()) && !updatedStudent.getEmail().isEmpty()){
            actualStudent.setEmail(updatedStudent.getEmail());
        }
        if(!actualStudent.getClassGroup().equals(updatedStudent.getClassGroup())&& updatedStudent.getClassGroup()!=null){
            actualStudent.setClassGroup(updatedStudent.getClassGroup());
        }
        return studentRepository.save(actualStudent);
    }
    public void deleteStudent(Long id){
        getStudentById(id);
        studentRepository.deleteById(id);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
}
