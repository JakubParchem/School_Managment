//package com.example.school.controller;
//
//import com.example.school.exception.InvalidInputException;
//import com.example.school.exception.ResourceNotFoundException;
//import com.example.school.service.StudentService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//class StudentControllerTest {
//
//    @Mock
//    private StudentService studentService;
//
//    @InjectMocks
//    private StudentController studentController;
//
//    private MockMvc mockMvc;
//    private StudentDto studentDto;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
//
//        studentDto = new StudentDto();
//        studentDto.setId(1L);
//        studentDto.setName("John Doe");
//        studentDto.setClassGroupId(1L);
//    }
//
//    @Test
//    void testGetAllStudents() throws Exception {
//        List<StudentDto> students = Arrays.asList(studentDto);
//
//        when(studentService.getAllStudents()).thenReturn(students);
//
//        mockMvc.perform(get("/api/students"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1))
//                .andExpect(jsonPath("$[0].name").value("John Doe"))
//                .andExpect(jsonPath("$[0].classGroupId").value(1));
//
//        verify(studentService, times(1)).getAllStudents();
//    }
//
//    @Test
//    void testGetStudentById() throws Exception {
//        when(studentService.getStudentById(1L)).thenReturn(studentDto);
//
//        mockMvc.perform(get("/api/students/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.classGroupId").value(1));
//
//        verify(studentService, times(1)).getStudentById(1L);
//    }
//
//    @Test
//    void testGetStudentByIdNotFound() throws Exception {
//        when(studentService.getStudentById(1L)).thenThrow(new ResourceNotFoundException("Student not found"));
//
//        mockMvc.perform(get("/api/students/1"))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).getStudentById(1L);
//    }
//
//    @Test
//    void testCreateStudent() throws Exception {
//        when(studentService.createStudent(any(StudentDto.class))).thenReturn(studentDto);
//
//        mockMvc.perform(post("/api/students")
//                        .contentType("application/json")
//                        .content("{\"name\":\"John Doe\", \"classGroupId\":1}"))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.classGroupId").value(1));
//
//        verify(studentService, times(1)).createStudent(any(StudentDto.class));
//    }
//
//    @Test
//    void testCreateStudentInvalidInput() throws Exception {
//        mockMvc.perform(post("/api/students")
//                        .contentType("application/json")
//                        .content("{\"name\":\"\", \"classGroupId\":1}"))
//                .andExpect(status().isBadRequest());
//
//        verify(studentService, times(0)).createStudent(any(StudentDto.class));
//    }
//
//    @Test
//    void testUpdateStudent() throws Exception {
//        when(studentService.updateStudent(1L, studentDto)).thenReturn(studentDto);
//
//        mockMvc.perform(put("/api/students/1")
//                        .contentType("application/json")
//                        .content("{\"name\":\"John Doe\", \"classGroupId\":1}"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(1))
//                .andExpect(jsonPath("$.name").value("John Doe"))
//                .andExpect(jsonPath("$.classGroupId").value(1));
//
//        verify(studentService, times(1)).updateStudent(1L, studentDto);
//    }
//
//    @Test
//    void testUpdateStudentNotFound() throws Exception {
//        when(studentService.updateStudent(1L, studentDto)).thenThrow(new ResourceNotFoundException("Student not found"));
//
//        mockMvc.perform(put("/api/students/1")
//                        .contentType("application/json")
//                        .content("{\"name\":\"John Doe\", \"classGroupId\":1}"))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).updateStudent(1L, studentDto);
//    }
//
//    @Test
//    void testDeleteStudent() throws Exception {
//        doNothing().when(studentService).deleteStudent(1L);
//
//        mockMvc.perform(delete("/api/students/1"))
//                .andExpect(status().isNoContent());
//
//        verify(studentService, times(1)).deleteStudent(1L);
//    }
//
//    @Test
//    void testDeleteStudentNotFound() throws Exception {
//        doThrow(new ResourceNotFoundException("Student not found")).when(studentService).deleteStudent(1L);
//
//        mockMvc.perform(delete("/api/students/1"))
//                .andExpect(status().isNotFound());
//
//        verify(studentService, times(1)).deleteStudent(1L);
//    }
//}
