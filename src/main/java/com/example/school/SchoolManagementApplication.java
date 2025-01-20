package com.example.school;

import com.example.school.controller.MainController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolManagementApplication implements CommandLineRunner {
    private final MainController controller;
    public SchoolManagementApplication(MainController controller) {
        this.controller = controller;
    }
    public static void main(String[] args) {
        SpringApplication.run(SchoolManagementApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        controller.deleteAll();
        controller.createSample();
    }
}

