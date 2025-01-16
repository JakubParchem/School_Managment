package com.example.school.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class ClassGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // np. "1A", "2B"
    private int capacity;

    @OneToMany(mappedBy = "classRoom") // Jedna klasa ma wielu uczniów
    private List<Student> students;
}
