package com.example.school.service;

import com.example.school.model.ClassGroup;
import com.example.school.repository.ClassGroupRepository;

import java.util.List;

public class ClassGroupService {
    private final ClassGroupRepository classGroupRepository;

    public ClassGroupService(ClassGroupRepository classGroupRepository) {
        this.classGroupRepository = classGroupRepository;
    }
    public List<ClassGroup> getAllClassGroups(){
        return classGroupRepository.findAll();
    }
    public ClassGroup getClassGroupById(Long id){
        if(classGroupRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("ClassGroup not found with ID: " + id);
        }
        return classGroupRepository.findById(id).get();
    }
    public ClassGroup createClassGroup(ClassGroup classGroup){
        if(!classGroup.validate()){
            throw new IllegalArgumentException("name is a required field");
        }
       return classGroupRepository.save(classGroup);
    }
    public ClassGroup updateClassGroup(ClassGroup classGroup, Long id){
        if(classGroupRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("ClassGroup not found with ID: " + id);
        }
        ClassGroup existingClassGroup = classGroupRepository.findById(id).get();
        if(!classGroup.validate()){
            throw new IllegalArgumentException("name is a required field");
        }
        if(classGroup.getCapacity()!=existingClassGroup.getCapacity()){
            existingClassGroup.setCapacity(classGroup.getCapacity());
        }
        if(!classGroup.getStudents().equals(existingClassGroup.getStudents())){
            existingClassGroup.setStudents(classGroup.getStudents());
        }
        return classGroupRepository.save(existingClassGroup);
    }
    public void deleteClassGroup(Long id){
        if(classGroupRepository.findById(id).isEmpty()){
            throw new IllegalArgumentException("ClassGroup not found with ID: " + id);
        }
        classGroupRepository.deleteById(id);
    }
}
