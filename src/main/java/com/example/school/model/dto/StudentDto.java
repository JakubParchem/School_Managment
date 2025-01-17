package com.example.school.model.dto;

import java.time.LocalDate;
import java.util.List;

public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate enrollmentDate;
    private Long classGroupId;
    private List<Long> subjectIds;

    public StudentDto(Long id, String name, String email, LocalDate enrollmentDate, Long classGroupId, List<Long> subjectIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.classGroupId = classGroupId;
        this.subjectIds = subjectIds;
    }

    public StudentDto() {
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

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Long getClassGroupId() {
        return classGroupId;
    }

    public void setClassGroupId(Long classGroupId) {
        this.classGroupId = classGroupId;
    }

    public List<Long> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<Long> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
