package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.model.Student;
import com.example.school.model.dto.StudentDto;
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
    public StudentDto createStudent(StudentDto studentDto) throws InvalidInputException {
        Student student=mapToEntity(studentDto);
        if(!student.validate()){
            throw new InvalidInputException("id, name, email and enrollment are required fields");
        }
        return mapToDto(studentRepository.save(student));
    }
    public StudentDto getStudentById(Long id) throws ResourceNotFoundException {
        if(!studentRepository.existsById(id)){
            throw new ResourceNotFoundException("could not find student with id: "+id);
        }
        return mapToDto(studentRepository.findById(id).get());
    }
    public StudentDto updateStudent(Long actualStudentId, StudentDto updatedStudentdto) throws ResourceNotFoundException {
        Student actualStudent = mapToEntity(getStudentById(actualStudentId));
        Student updatedStudent = mapToEntity(updatedStudentdto);
        if(!actualStudent.getEmail().equals(updatedStudent.getEmail()) && !updatedStudent.getEmail().isEmpty()){
            actualStudent.setEmail(updatedStudent.getEmail());
        }
        if(!actualStudent.getClassGroup().equals(updatedStudent.getClassGroup())&& updatedStudent.getClassGroup()!=null){
            actualStudent.setClassGroup(updatedStudent.getClassGroup());
        }
        return mapToDto( studentRepository.save(actualStudent));
    }
    public void deleteStudent(Long id) throws ResourceNotFoundException {
        getStudentById(id);
        studentRepository.deleteById(id);
    }
    public List<StudentDto> getAllStudents(){
        return studentRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }
    private StudentDto mapToDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setClassGroupId(student.getClassGroup() != null ? student.getClassGroup().getId() : null);
        return dto;
    }
    private Student mapToEntity(StudentDto dto) {
        Student student = new Student();
        student.setName(dto.getName());
        if (dto.getClassGroupId() != null) {
            ClassGroup classGroup = new ClassGroup();
            classGroup.setId(dto.getClassGroupId());
            student.setClassGroup(classGroup);
        }
        return student;
    }
}
