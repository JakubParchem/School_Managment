package com.example.school.model.dto;

import java.time.LocalDate;

public class GradeDto {
    private Long id;
    private float value;
    private LocalDate date;
    private Long studentId;
    private Long subjectId;

    public GradeDto(Long id, float value, LocalDate date, Long studentId, Long subjectId) {
        this.id = id;
        this.value = value;
        this.date = date;
        this.studentId = studentId;
        this.subjectId = subjectId;
    }

    public GradeDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
