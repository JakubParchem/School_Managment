package com.example.school.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private String email;
    private LocalDate enrollmentDate;
    @ManyToOne
    @JoinColumn(name = "classgroup_id")
    private ClassGroup classGroup;
    @ManyToMany
    private List<Subject> subjects;

    @OneToMany
    private List<Grade> grades;
    public Student(Long id, String name, String email, LocalDate enrollmentDate, ClassGroup classGroup,List<Subject> subjects,List<Grade> grades) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.classGroup = classGroup;
        this.subjects=subjects;
        this.grades=grades;
    }

    public Student() {
    }
    public boolean validate(){
        if(name==null || email==null){
            return false;
        }
        return !name.isEmpty() && !email.isEmpty() && enrollmentDate!=null && id!=null;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
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
    public ClassGroup getClassGroup() {
        return classGroup;
    }
    public void setClassGroup(ClassGroup classGroup) {
        this.classGroup = classGroup;
    }
    public List<Subject> getSubjects() {
        return subjects;
    }
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public void setEnrollment(LocalDate date) {
        this.enrollmentDate=date;
    }
}

