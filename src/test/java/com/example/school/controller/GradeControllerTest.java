package com.example.school.controller;

import com.example.school.exception.InvalidInputException;
import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Grade;
import com.example.school.service.GradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class GradeControllerTest {

    @Mock
    private GradeService gradeService;

    @InjectMocks
    private GradeController gradeController;

    private MockMvc mockMvc;
    private Grade grade;

    @BeforeEach
    void setUp() throws InvalidInputException {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gradeController).build();

        grade = new Grade();
        grade.setId(1L);
        grade.setValue(4.5f);
        grade.setDate(LocalDate.parse("2025-01-15"));
    }

    @Test
    void testGetAllGrades() throws Exception {
        List<Grade> grades = Arrays.asList(grade);

        when(gradeService.getAllGrades()).thenReturn(grades);

        mockMvc.perform(get("/api/grades"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].value").value(4.5))
                .andExpect(jsonPath("$[0].date").value("2025-01-15"));

        verify(gradeService, times(1)).getAllGrades();
    }

    @Test
    void testGetGradeById() throws Exception {
        when(gradeService.getGradeById(1L)).thenReturn(grade);

        mockMvc.perform(get("/api/grades/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.value").value(4.5))
                .andExpect(jsonPath("$.date").value("2025-01-15"));

        verify(gradeService, times(1)).getGradeById(1L);
    }

    @Test
    void testGetGradeByIdNotFound() throws Exception {
        when(gradeService.getGradeById(1L)).thenThrow(new ResourceNotFoundException("Grade not found"));

        mockMvc.perform(get("/api/grades/1"))
                .andExpect(status().isNotFound());

        verify(gradeService, times(1)).getGradeById(1L);
    }

    @Test
    void testCreateGrade() throws Exception {
        when(gradeService.createGrade(any(Grade.class))).thenReturn(grade);

        mockMvc.perform(post("/api/grades")
                        .contentType("application/json")
                        .content("{\"value\":4.5, \"date\":\"2025-01-15\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.value").value(4.5))
                .andExpect(jsonPath("$.date").value("2025-01-15"));

        verify(gradeService, times(1)).createGrade(any(Grade.class));
    }

    @Test
    void testCreateGradeInvalidInput() throws Exception {
        mockMvc.perform(post("/api/grades")
                        .contentType("application/json")
                        .content("{\"value\":-1, \"date\":\"2025-01-15\"}"))
                .andExpect(status().isBadRequest());

        verify(gradeService, times(0)).createGrade(any(Grade.class));
    }

    @Test
    void testUpdateGrade() throws Exception {
        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);
        updatedGrade.setValue(5.0f);
        updatedGrade.setDate(LocalDate.parse("2025-01-16"));

        when(gradeService.updateGrade(1L, updatedGrade)).thenReturn(updatedGrade);

        mockMvc.perform(put("/api/grades/1")
                        .contentType("application/json")
                        .content("{\"value\":5.0, \"date\":\"2025-01-16\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.value").value(5.0))
                .andExpect(jsonPath("$.date").value("2025-01-16"));

        verify(gradeService, times(1)).updateGrade(1L, updatedGrade);
    }

    @Test
    void testUpdateGradeNotFound() throws Exception {
        Grade updatedGrade = new Grade();
        updatedGrade.setId(1L);
        updatedGrade.setValue(5.0f);
        updatedGrade.setDate(LocalDate.parse("2025-01-16"));

        when(gradeService.updateGrade(1L, updatedGrade)).thenThrow(new ResourceNotFoundException("Grade not found"));

        mockMvc.perform(put("/api/grades/1")
                        .contentType("application/json")
                        .content("{\"value\":5.0, \"date\":\"2025-01-16\"}"))
                .andExpect(status().isNotFound());

        verify(gradeService, times(1)).updateGrade(1L, updatedGrade);
    }

    @Test
    void testDeleteGrade() throws Exception {
        doNothing().when(gradeService).deleteGrade(1L);

        mockMvc.perform(delete("/api/grades/1"))
                .andExpect(status().isNoContent());

        verify(gradeService, times(1)).deleteGrade(1L);
    }

    @Test
    void testDeleteGradeNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Grade not found")).when(gradeService).deleteGrade(1L);

        mockMvc.perform(delete("/api/grades/1"))
                .andExpect(status().isNotFound());

        verify(gradeService, times(1)).deleteGrade(1L);
    }
}
