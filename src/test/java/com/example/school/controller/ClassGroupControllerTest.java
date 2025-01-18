package com.example.school.controller;

import com.example.school.exception.ResourceNotFoundException;
import com.example.school.model.ClassGroup;
import com.example.school.service.ClassGroupService;
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

class ClassGroupControllerTest {

    @Mock
    private ClassGroupService classGroupService;

    @InjectMocks
    private ClassGroupController classGroupController;

    private MockMvc mockMvc;
    private ClassGroup classGroup;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(classGroupController).build();

        classGroup = new ClassGroup();
        classGroup.setId(1L);
        classGroup.setName("Class A");
        classGroup.setCapacity(30);
    }

    @Test
    void testGetAllClassGroups() throws Exception {
        List<ClassGroup> classGroups = Arrays.asList(classGroup);

        when(classGroupService.getAllClassGroups()).thenReturn(classGroups);

        mockMvc.perform(get("/api/class-groups"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Class A"))
                .andExpect(jsonPath("$[0].capacity").value(30));

        verify(classGroupService, times(1)).getAllClassGroups();
    }

    @Test
    void testGetClassGroupById() throws Exception {
        when(classGroupService.getClassGroupById(1L)).thenReturn(classGroup);

        mockMvc.perform(get("/api/class-groups/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Class A"))
                .andExpect(jsonPath("$.capacity").value(30));

        verify(classGroupService, times(1)).getClassGroupById(1L);
    }

    @Test
    void testGetClassGroupByIdNotFound() throws Exception {
        when(classGroupService.getClassGroupById(1L)).thenThrow(new ResourceNotFoundException("ClassGroup not found"));

        mockMvc.perform(get("/api/class-groups/1"))
                .andExpect(status().isNotFound());

        verify(classGroupService, times(1)).getClassGroupById(1L);
    }

    @Test
    void testCreateClassGroup() throws Exception {
        when(classGroupService.createClassGroup(any(ClassGroup.class))).thenReturn(classGroup);

        mockMvc.perform(post("/api/class-groups")
                        .contentType("application/json")
                        .content("{\"name\":\"Class A\", \"capacity\":30}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Class A"))
                .andExpect(jsonPath("$.capacity").value(30));

        verify(classGroupService, times(1)).createClassGroup(any(ClassGroup.class));
    }

    @Test
    void testCreateClassGroupInvalidInput() throws Exception {
        mockMvc.perform(post("/api/class-groups")
                        .contentType("application/json")
                        .content("{\"name\":\"\", \"capacity\":-5}"))
                .andExpect(status().isBadRequest());

        verify(classGroupService, times(0)).createClassGroup(any(ClassGroup.class));
    }

    @Test
    void testUpdateClassGroup() throws Exception {
        ClassGroup updatedClassGroup = new ClassGroup();
        updatedClassGroup.setId(1L);
        updatedClassGroup.setName("Class B");
        updatedClassGroup.setCapacity(35);

        when(classGroupService.updateClassGroup(any(ClassGroup.class), eq(1L))).thenReturn(updatedClassGroup);

        mockMvc.perform(put("/api/class-groups/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Class B\", \"capacity\":35}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Class B"))
                .andExpect(jsonPath("$.capacity").value(35));

        verify(classGroupService, times(1)).updateClassGroup(any(ClassGroup.class), eq(1L));
    }

    @Test
    void testUpdateClassGroupNotFound() throws Exception {
        when(classGroupService.updateClassGroup(any(ClassGroup.class), eq(1L))).thenThrow(new ResourceNotFoundException("ClassGroup not found"));

        mockMvc.perform(put("/api/class-groups/1")
                        .contentType("application/json")
                        .content("{\"name\":\"Class B\", \"capacity\":35}"))
                .andExpect(status().isNotFound());

        verify(classGroupService, times(1)).updateClassGroup(any(ClassGroup.class), eq(1L));
    }

    @Test
    void testDeleteClassGroup() throws Exception {
        doNothing().when(classGroupService).deleteClassGroup(1L);

        mockMvc.perform(delete("/api/class-groups/1"))
                .andExpect(status().isNoContent());

        verify(classGroupService, times(1)).deleteClassGroup(1L);
    }

    @Test
    void testDeleteClassGroupNotFound() throws Exception {
        doThrow(new ResourceNotFoundException("ClassGroup not found")).when(classGroupService).deleteClassGroup(1L);

        mockMvc.perform(delete("/api/class-groups/1"))
                .andExpect(status().isNotFound());

        verify(classGroupService, times(1)).deleteClassGroup(1L);
    }
}
