package com.example.school.model;

import com.example.school.exception.InvalidInputException;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private float value;
    private LocalDate date;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Grade(Long id, float value, LocalDate date, Student student, Subject subject) throws InvalidInputException {
        this.id = id;
        this.value=value;
        this.date = date;
        this.student = student;
        this.subject = subject;
    }
    public Grade() {

    }
    public float getValue() {
        return value;
    }
    boolean validateValue(float value) throws InvalidInputException {
        if((value*10)%5==0 && value>=2 && value<=5){
            return true;
        }
        return false;
    }
    public void setValue(float value){
        this.value=value;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    public boolean validate() throws InvalidInputException {
        return validateValue(this.value) && date!=null && student!=null && subject!=null;
    }
}
