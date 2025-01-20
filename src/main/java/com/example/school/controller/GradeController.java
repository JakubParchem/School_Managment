package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Grade;
import com.example.school.service.GradeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/grades")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }

    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        return ResponseEntity.ok(gradeService.getAllGrades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) throws ResourceNotFoundException {
        Grade grade = gradeService.getGradeById(id);
        return grade != null ? ResponseEntity.ok(grade) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Grade> createGrade(@RequestBody @Valid Grade grade) throws InvalidInputException {
        Grade createdGrade = gradeService.createGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @RequestBody @Valid Grade grade) throws InvalidInputException, ResourceNotFoundException {
        Grade updatedGrade = gradeService.updateGrade(id, grade);
        return ResponseEntity.ok(updatedGrade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) throws ResourceNotFoundException {
        gradeService.deleteGrade(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    public void deleteAllGrades(){
        gradeService.deleteAllGrades();
    }
}
