package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Subject;
import com.example.school.service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) throws ResourceNotFoundException {
        Subject subject = subjectService.getSubjectById(id);
        return subject != null ? ResponseEntity.ok(subject) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Subject> createSubject(@RequestBody @Valid Subject subject) throws InvalidInputException {
        Subject createdSubject = subjectService.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody @Valid Subject subject) throws InvalidInputException, ResourceNotFoundException {
        Subject updatedSubject = subjectService.updateSubject(id, subject);
        return ResponseEntity.ok(updatedSubject);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) throws ResourceNotFoundException {
        subjectService.deleteSubject(id);
        return ResponseEntity.noContent().build();
    }
}
