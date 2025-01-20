package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Subject;
import com.example.school.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) throws ResourceNotFoundException {
        if (subjectRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Subject not found with ID: " + id);
        }
        return subjectRepository.findById(id).get();
    }

    public Subject createSubject(Subject subject) throws InvalidInputException {
        if (!subject.validate()) {
            throw new InvalidInputException("name, teacher and students fields are required");
        }
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(Long id, Subject subject) throws InvalidInputException, ResourceNotFoundException {
        if (subjectRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Subject not found with ID: " + id);
        }
        Subject existingSubject = subjectRepository.findById(id).get();

        if (!subject.validate()) {
            throw new InvalidInputException("name, teacher and students fields are required");
        }
//        if (!subject.getStudents().equals(existingSubject.getStudents())) {
//            existingSubject.setStudents(subject.getStudents());
//        }
        if (!subject.getTeacher().equals(existingSubject.getTeacher())) {
            existingSubject.setTeacher(subject.getTeacher());
        }
        return subjectRepository.save(existingSubject);
    }

    public void deleteSubject(Long id) throws ResourceNotFoundException {
        if (subjectRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Subject not found with ID: " + id);
        }
        subjectRepository.deleteById(id);
    }
    public void deleteAllSubjects(){
        subjectRepository.deleteAll();
    }
}
