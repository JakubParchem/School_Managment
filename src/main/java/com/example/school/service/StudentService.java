package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.model.Student;
import com.example.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    public Student createStudent(Student student) throws InvalidInputException {
        if(!student.validate()){
            throw new InvalidInputException("id, name, email and enrollment are required fields");
        }
        return studentRepository.save(student);
    }
    public Student getStudentById(Long id) throws ResourceNotFoundException {
        if(!studentRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("could not find student with id: "+id);
        }
        return studentRepository.findById(id).get();
    }
    public Student updateStudent(Long actualStudentId, Student updatedStudent) throws ResourceNotFoundException {
        Student actualStudent = getStudentById(actualStudentId);
        if(!actualStudent.getEmail().equals(updatedStudent.getEmail()) && !updatedStudent.getEmail().isEmpty()){
            actualStudent.setEmail(updatedStudent.getEmail());
        }
        if(!actualStudent.getClassGroup().equals(updatedStudent.getClassGroup())&& updatedStudent.getClassGroup()!=null){
            actualStudent.setClassGroup(updatedStudent.getClassGroup());
        }
        return studentRepository.save(actualStudent);
    }
    public void deleteStudent(Long id) throws ResourceNotFoundException {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

}
