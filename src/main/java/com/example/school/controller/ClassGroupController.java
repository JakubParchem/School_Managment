package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.service.ClassGroupService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-groups")
public class ClassGroupController {

    private final ClassGroupService classGroupService;
    public ClassGroupController(ClassGroupService classGroupService) {
        this.classGroupService = classGroupService;
    }
    @GetMapping
    public ResponseEntity<List<ClassGroup>> getAllClassGroups() {
        return ResponseEntity.ok(classGroupService.getAllClassGroups());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClassGroup> getClassGroupById(@PathVariable Long id) throws ResourceNotFoundException {
        ClassGroup classGroup = classGroupService.getClassGroupById(id);
        return classGroup != null ? ResponseEntity.ok(classGroup) : ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<ClassGroup> createClassGroup(@RequestBody @Valid ClassGroup classGroup) throws InvalidInputException {
        ClassGroup createdClassGroup = classGroupService.createClassGroup(classGroup);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClassGroup);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClassGroup> updateClassGroup(@PathVariable Long id, @RequestBody @Valid ClassGroup classGroup) throws InvalidInputException, ResourceNotFoundException {
        ClassGroup updatedClassGroup = classGroupService.updateClassGroup(classGroup,id);
        return ResponseEntity.ok(updatedClassGroup);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassGroup(@PathVariable Long id) throws ResourceNotFoundException {
        classGroupService.deleteClassGroup(id);
        return ResponseEntity.noContent().build();
    }
}
