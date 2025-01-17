package com.example.school.model.dto;

import java.util.List;

public class SubjectDto {
    private Long id;
    private String name;
    private Long teacherId;
    private List<Long> studentIds;

    public SubjectDto(Long id, String name, Long teacherId, List<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.teacherId = teacherId;
        this.studentIds = studentIds;
    }

    public SubjectDto() {
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

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
