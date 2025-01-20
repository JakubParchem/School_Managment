package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Teacher;
import com.example.school.service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping
    public ResponseEntity<List<Teacher>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) throws ResourceNotFoundException {
        Teacher teacher = teacherService.getTeacherById(id);
        return teacher != null ? ResponseEntity.ok(teacher) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid Teacher teacher) throws InvalidInputException {
        Teacher createdTeacher = teacherService.createTeacher(teacher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTeacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Teacher> updateTeacher(@PathVariable Long id, @RequestBody @Valid Teacher teacher) throws InvalidInputException, ResourceNotFoundException {
        Teacher updatedTeacher = teacherService.updateTeacher(id, teacher);
        return ResponseEntity.ok(updatedTeacher);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable Long id) throws ResourceNotFoundException {
        teacherService.deleteTeacher(id);
        return ResponseEntity.noContent().build();
    }
}
