package com.example.school.service;

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
    public Grade getGradeById(Long id) {
        if(gradeRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("No grade with id: "+id);
        }
        return gradeRepository.findById(id).get();
    }

    public Grade createGrade(Grade grade) {
        validateGrade(grade); // Validate the grade data
        return gradeRepository.save(grade);
    }

    /**
     * Update an existing grade.
     *
     * @param id    Grade ID
     * @param grade Grade object with updated data
     * @return Updated grade object
     */
    public Grade updateGrade(Long id, Grade grade) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Grade not found with ID: " + id));

        validateGrade(grade); // Validate the grade data

        // Update fields of the existing grade
        existingGrade.setValue(grade.getValue());
        existingGrade.setDescription(grade.getDescription());

        return gradeRepository.save(existingGrade);
    }

    /**
     * Delete a grade by ID.
     *
     * @param id Grade ID
     */
    public void deleteGrade(Long id) {
        if (!gradeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Grade not found with ID: " + id);
        }
        gradeRepository.deleteById(id);
    }
}
