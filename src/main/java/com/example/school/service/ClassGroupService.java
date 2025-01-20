package com.example.school.service;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.repository.ClassGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;

    public ClassGroupService(ClassGroupRepository classGroupRepository) {
        this.classGroupRepository = classGroupRepository;
    }
    public List<ClassGroup> getAllClassGroups(){
        return classGroupRepository.findAll();
    }

    public ClassGroup getClassGroupById(Long id) throws ResourceNotFoundException {
        if(classGroupRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("ClassGroup not found with ID: " + id);
        }
        return classGroupRepository.findById(id).get();
    }
    public ClassGroup createClassGroup(ClassGroup classGroup) throws InvalidInputException {
        if(!classGroup.validate()){
            throw new InvalidInputException("name is a required field");
        }
       return classGroupRepository.save(classGroup);
    }
    public ClassGroup updateClassGroup(ClassGroup classGroup, Long id) throws InvalidInputException, ResourceNotFoundException {
        if(classGroupRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("ClassGroup not found with ID: " + id);
        }
        ClassGroup existingClassGroup = classGroupRepository.findById(id).get();
        if(!classGroup.validate()){
            throw new InvalidInputException("name is a required field");
        }
        if(classGroup.getCapacity()!=existingClassGroup.getCapacity()){
            existingClassGroup.setCapacity(classGroup.getCapacity());
        }
//        if(!classGroup.getStudents().equals(existingClassGroup.getStudents())){
//            existingClassGroup.setStudents(classGroup.getStudents());
//        }
        if(!classGroup.getClass_teacher().equals(existingClassGroup.getClass_teacher())){
            existingClassGroup.setClass_teacher(classGroup.getClass_teacher());
        }
        return classGroupRepository.save(existingClassGroup);
    }
    public void deleteClassGroup(Long id) throws ResourceNotFoundException {
        if(classGroupRepository.findById(id).isEmpty()){
            throw new ResourceNotFoundException("ClassGroup not found with ID: " + id);
        }
        classGroupRepository.deleteById(id);
    }
}
