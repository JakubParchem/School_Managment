package com.example.school.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float value; // np. 5, 4
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "student_id") // Ocena jest przypisana jednemu uczniowi
    private Student student;
    @ManyToOne
    @JoinColumn(name = "subject_id") // Ocena dotyczy jednego przedmiotu
    private Subject subject;

    public Grade(Long id, float value, LocalDate date, Student student, Subject subject) {
        this.id = id;
        setValue(value);
        this.date = date;
        this.student = student;
        this.subject = subject;
    }

    public float getValue() {
        return value;
    }
    boolean validateValue(){
        return value%0.5==0 || value>=2 || value<=5;
    }
    public void setValue(float value) {
        if(!validateValue()){
            throw new IllegalArgumentException("grade has to be between 2.0 and 5.0 in 0.5 interval");
        }
        this.value = value;
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
    public boolean validate(){
        return validateValue() && date!=null && student!=null && subject!=null;
    }
}
