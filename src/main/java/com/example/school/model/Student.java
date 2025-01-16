package com.example.school.model;

import jakarta.persistence.*;
import java.time.LocalDate;

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
    public Student(Long id, String name, String email, LocalDate enrollmentDate, ClassGroup classGroup) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.classGroup = classGroup;
    }

    public Student() {
    }
    public boolean validate(){
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

}

