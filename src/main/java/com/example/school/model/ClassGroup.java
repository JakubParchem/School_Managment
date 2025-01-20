package com.example.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @OneToOne
    private Teacher class_teacher;
//    @OneToMany(mappedBy = "classGroup")
//    private List<Student> students;
//    public ClassGroup(Long id, String name, int capacity,Teacher class_teacher, List<Student> students) {
//        this.id = id;
//        this.name = name;
//        this.capacity = capacity;
//        this.students = students;
//        this.class_teacher=class_teacher;
//    }
    public ClassGroup() {}
    public Teacher getClass_teacher() {
        return class_teacher;
    }
    public void setClass_teacher(Teacher class_teacher) {
        this.class_teacher = class_teacher;
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
//    public List<Student> getStudents() {
//        return students;
//    }
//    public void setStudents(List<Student> students) {
//        this.students = students;
//    }
    public boolean validate(){
        return !name.isEmpty();
    }
}
