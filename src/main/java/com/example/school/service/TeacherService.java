package com.example.school.service;

import com.example.school.model.Teacher;
import com.example.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    public Teacher getTeacherById(Long id) {
        if(teacherRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("there is no teacher with id: "+id);
        }
        return teacherRepository.findById(id).get();
    }

    public Teacher createTeacher(Teacher teacher) {
        if(!teacher.validate()){
            throw new IllegalArgumentException("name, email and subject specialisation are required");
        }
        return teacherRepository.save(teacher);
    }
    public Teacher updateTeacher(Long id, Teacher teacher) {
        if(teacherRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("there is no teacher with id: "+id);
        }
        Teacher existingTeacher = teacherRepository.findById(id).get();
        if(!teacher.validate()){
            throw new IllegalArgumentException("updated teacher doesn't have required fields");
        }
        if(!existingTeacher.getEmail().equals(teacher.getEmail())){
            existingTeacher.setEmail(teacher.getEmail());
        }
        if(!existingTeacher.getName().equals(teacher.getName())){
            existingTeacher.setName(teacher.getName());
        }
        if(!existingTeacher.getSubjects().equals(teacher.getSubjects())){
            existingTeacher.setSubjects(teacher.getSubjects());
        }
        return teacherRepository.save(existingTeacher);
    }

    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new IllegalArgumentException("Teacher not found with ID: " + id);
        }
        teacherRepository.deleteById(id);
    }

}

