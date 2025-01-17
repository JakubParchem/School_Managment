package com.example.school.model.dto;

import java.util.List;

public class TeacherDto {
    private Long id;
    private String name;
    private String email;
    private String subjectSpecialization;
    private List<Long> subjectIds;

    public TeacherDto(Long id, String name, String email, String subjectSpecialization, List<Long> subjectIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subjectSpecialization = subjectSpecialization;
        this.subjectIds = subjectIds;
    }

    public TeacherDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubjectSpecialization() {
        return subjectSpecialization;
    }

    public void setSubjectSpecialization(String subjectSpecialization) {
        this.subjectSpecialization = subjectSpecialization;
    }

    public List<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
