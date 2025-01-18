package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Subject;
import com.example.school.model.Teacher;
import com.example.school.service.SubjectService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class SubjectControllerTest {

    @Mock
    private SubjectService subjectService;
    @Mock
    Teacher teacher;
    @InjectMocks
    private SubjectController subjectController;
    private MockMvc mockMvc;
    private Subject subject;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(subjectController).build();
        subject = new Subject();
        subject.setId(1L);
        subject.setName("Math");
        subject.setTeacher(teacher);
    }

    @Test
    void testGetAllSubjects() throws Exception {
        List<Subject> subjects = Arrays.asList(subject);

        when(subjectService.getAllSubjects()).thenReturn(subjects);

        mockMvc.perform(get("/api/subjects"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Math"))
                .andExpect(jsonPath("$[0].teacher").value("Mr. Smith"));

        verify(subjectService, times(1)).getAllSubjects();
    }

    @Test
    void testGetSubjectById() throws Exception {
        when(subjectService.getSubjectById(1L)).thenReturn(subject);

        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Math"))
                .andExpect(jsonPath("$.teacher").value("Mr. Smith"));

        verify(subjectService, times(1)).getSubjectById(1L);
    }

    @Test
    void testGetSubjectByIdNotFound() throws Exception {
        when(subjectService.getSubjectById(1L)).thenThrow(new ResourceNotFoundException("Subject not found"));

        mockMvc.perform(get("/api/subjects/1"))
                .andExpect(status().isNotFound());

        verify(subjectService, times(1)).getSubjectById(1L);
    }

    @Test
    void testCreateSubject() throws Exception {
        when(subjectService.createSubject(any(Subject.class))).thenReturn(subject);

        mockMvc.perform(post("/api/subjects")
                        .contentType("application/json")
                        .content("{\"name\":\"Math\", \"teacher\":\"Mr. Smith\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Math"))
                .andExpect(jsonPath("$.teacher").value("Mr. Smith"));

        verify(subjectService, times(1)).createSubject(any(Subject.class));
    }

    @Test
    void testCreateSubjectInvalidInput() throws Exception {
        mockMvc.perform(post("/api/subjects")
                        .contentType("application/json")
                        .content("{\"name\":\"\", \"teacher\":\"\"}"))
                .andExpect(status().isBadRequest());

        verify(subjectService, times(0)).createSubject(any(Subject.class));
    }

    @Test
    void testUpdateSubject() throws Exception {
        when(subjectService.updateSubject(1L, subject)).thenReturn(subject);

        mockMvc.perform(put("/api/subjects/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Math\", \"teacher\":\"Mr. Smith\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Math"))
                .andExpect(jsonPath("$.teacher").value("Mr. Smith"));

        verify(subjectService, times(1)).updateSubject(1L, subject);
    }

    @Test
    void testUpdateSubjectNotFound() throws Exception {
        when(subjectService.updateSubject(1L, subject)).thenThrow(new ResourceNotFoundException("Subject not found"));

        mockMvc.perform(put("/api/subjects/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Math\", \"teacher\":\"Mr. Smith\"}"))
                .andExpect(status().isNotFound());

        verify(subjectService, times(1)).updateSubject(1L, subject);
    }

    @Test
    void testDeleteSubject() throws Exception {
        doNothing().when(subjectService).deleteSubject(1L);

        mockMvc.perform(delete("/api/subjects/1"))
                .andExpect(status().isNoContent());

        verify(subjectService, times(1)).deleteSubject(1L);
    }

    @Test
    void testDeleteSubjectNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Subject not found")).when(subjectService).deleteSubject(1L);

        mockMvc.perform(delete("/api/subjects/1"))
                .andExpect(status().isNotFound());

        verify(subjectService, times(1)).deleteSubject(1L);
    }
}
