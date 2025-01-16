package com.example.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // np. "Matematyka", "Historia"
    private String description;

    @ManyToOne
    @JoinColumn(name = "teacher_id") // Przedmiot jest prowadzony przez jednego nauczyciela
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects") // Przedmiot może być uczony wielu uczniom
    private List<Student> students;

    // Gettery, settery, konstruktory...
}
