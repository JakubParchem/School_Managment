package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Grade;
import com.example.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    public Grade getGradeById(Long id) throws ResourceNotFoundException {
        if(gradeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No grade with id: "+id);
        }
        return gradeRepository.findById(id).get();
    }

    public Grade createGrade(Grade grade) throws InvalidInputException {
        if(!grade.validate()){
            throw new InvalidInputException("wrong grade or date, student or subject are empty");
        }
        return gradeRepository.save(grade);
    }
    public Grade updateGrade(Long id, Grade grade) throws ResourceNotFoundException, InvalidInputException {
        if(gradeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No grade with id: "+id);
        }
        Grade existingGrade = gradeRepository.findById(id).get();
        if(!grade.validate()){
            throw new InvalidInputException("wrong updated grade or date, student or subject are empty");
        }
        if(grade.getValue()!=existingGrade.getValue())
        {
            existingGrade.setValue(grade.getValue());
        }
        if(!grade.getDate().equals(existingGrade.getDate())){
            existingGrade.setDate(grade.getDate());
        }
        return gradeRepository.save(existingGrade);
    }
    public void deleteGrade(Long id) throws ResourceNotFoundException {
        if(gradeRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("No grade with id: "+id);
        }
        gradeRepository.deleteById(id);
    }
    public void deleteAllGrades(){
        gradeRepository.deleteAll();
    }
}
