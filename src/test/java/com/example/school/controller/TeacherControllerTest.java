package com.example.school.controller;

import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.Teacher;
import com.example.school.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;
    @InjectMocks
    private TeacherController teacherController;

    private MockMvc mockMvc;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();

        teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName("Mr. Smith");
        teacher.setEmail("mr.smith@example.com");
        teacher.setSubjectSpecialisation("subject");
    }

    @Test
    void testGetAllTeachers() throws Exception {
        List<Teacher> teachers = Arrays.asList(teacher);

        when(teacherService.getAllTeachers()).thenReturn(teachers);

        mockMvc.perform(get("/api/teachers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Mr. Smith"))
                .andExpect(jsonPath("$[0].email").value("mr.smith@example.com"))
                .andExpect(jsonPath("$[0].subjectSpecialisation").value("Math"));

        verify(teacherService, times(1)).getAllTeachers();
    }

    @Test
    void testGetTeacherById() throws Exception {
        when(teacherService.getTeacherById(1L)).thenReturn(teacher);

        mockMvc.perform(get("/api/teachers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mr. Smith"))
                .andExpect(jsonPath("$.email").value("mr.smith@example.com"))
                .andExpect(jsonPath("$.subjectSpecialisation").value("Math"));

        verify(teacherService, times(1)).getTeacherById(1L);
    }

    @Test
    void testGetTeacherByIdNotFound() throws Exception {
        when(teacherService.getTeacherById(1L)).thenThrow(new ResourceNotFoundException("Teacher not found"));

        mockMvc.perform(get("/api/teachers/1"))
                .andExpect(status().isNotFound());

        verify(teacherService, times(1)).getTeacherById(1L);
    }

    @Test
    void testCreateTeacher() throws Exception {
        when(teacherService.createTeacher(any(Teacher.class))).thenReturn(teacher);

        mockMvc.perform(post("/api/teachers")
                        .contentType("application/json")
                        .content("{\"name\":\"Mr. Smith\", \"email\":\"mr.smith@example.com\", \"subjectSpecialisation\":\"Math\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mr. Smith"))
                .andExpect(jsonPath("$.email").value("mr.smith@example.com"))
                .andExpect(jsonPath("$.subjectSpecialisation").value("Math"));

        verify(teacherService, times(1)).createTeacher(any(Teacher.class));
    }

    @Test
    void testCreateTeacherInvalidInput() throws Exception {
        mockMvc.perform(post("/api/teachers")
                        .contentType("application/json")
                        .content("{\"name\":\"\", \"email\":\"\", \"subjectSpecialisation\":\"\"}"))
                .andExpect(status().isBadRequest());

        verify(teacherService, times(0)).createTeacher(any(Teacher.class));
    }

    @Test
    void testUpdateTeacher() throws Exception {
        when(teacherService.updateTeacher(1L, teacher)).thenReturn(teacher);

        mockMvc.perform(put("/api/teachers/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Mr. Smith\", \"email\":\"mr.smith@example.com\", \"subjectSpecialisation\":\"Math\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Mr. Smith"))
                .andExpect(jsonPath("$.email").value("mr.smith@example.com"))
                .andExpect(jsonPath("$.subjectSpecialisation").value("Math"));

        verify(teacherService, times(1)).updateTeacher(1L, teacher);
    }

    @Test
    void testUpdateTeacherNotFound() throws Exception {
        when(teacherService.updateTeacher(1L, teacher)).thenThrow(new ResourceNotFoundException("Teacher not found"));

        mockMvc.perform(put("/api/teachers/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Mr. Smith\", \"email\":\"mr.smith@example.com\", \"subjectSpecialisation\":\"Math\"}"))
                .andExpect(status().isNotFound());

        verify(teacherService, times(1)).updateTeacher(1L, teacher);
    }

    @Test
    void testDeleteTeacher() throws Exception {
        doNothing().when(teacherService).deleteTeacher(1L);

        mockMvc.perform(delete("/api/teachers/1"))
                .andExpect(status().isNoContent());

        verify(teacherService, times(1)).deleteTeacher(1L);
    }

    @Test
    void testDeleteTeacherNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("Teacher not found")).when(teacherService).deleteTeacher(1L);

        mockMvc.perform(delete("/api/teachers/1"))
                .andExpect(status().isNotFound());

        verify(teacherService, times(1)).deleteTeacher(1L);
    }
}
