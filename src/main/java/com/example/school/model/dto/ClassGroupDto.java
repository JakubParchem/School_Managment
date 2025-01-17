package com.example.school.model.dto;

import java.util.List;

public class ClassGroupDto {
    private Long id;
    private String name;
    private int capacity;
    private List<Long> studentIds;

    public ClassGroupDto(Long id, String name, int capacity, List<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.studentIds = studentIds;
    }

    public ClassGroupDto() {
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Long> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Long> studentIds) {
        this.studentIds = studentIds;
    }
}
