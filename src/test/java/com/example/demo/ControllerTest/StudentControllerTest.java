package com.example.demo.ControllerTest;

import com.example.demo.Controller.StudentController;
import com.example.demo.DTO.OnboardStudentRequest;
import com.example.demo.Models.Student;
import com.example.demo.Models.Subject;
import com.example.demo.Services.StudentService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentControllerTest {


    @InjectMocks
    private StudentController studentController;

    @Mock
    private StudentService studentService;

    private Validator validator;

    public StudentControllerTest() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testGetAllStudents() {
        // Mocking the service method
        List<Student> students = new ArrayList<>();
        when(studentService.getAll()).thenReturn(students);

        // Testing the controller method
        List<Student> result = studentController.getAllStudents();

        // Verifying the service method invocation
        verify(studentService, times(1)).getAll();

        // Asserting the result
        assertEquals(students, result);
    }

    @Test
    public void testGetElectiveSubjects() {
        // Mocking the service method
        Long studentId = 1L;
        Subject electiveSubjects = new Subject();
        when(studentService.getStudentElectiveSubjects(studentId)).thenReturn(electiveSubjects);

        // Testing the controller method
        ResponseEntity<Subject> responseEntity = studentController.getElectiveSubjects(studentId);

        // Verifying the service method invocation
        verify(studentService, times(1)).getStudentElectiveSubjects(studentId);

        // Asserting the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(electiveSubjects, responseEntity.getBody());
    }

    @Test
    public void testSaveStudent() {
        // Mocking the request object
        OnboardStudentRequest onboardStudentRequest = new OnboardStudentRequest();

        // Mocking the service method
        Student savedStudent = new Student();
        when(studentService.onboardStudent(onboardStudentRequest)).thenReturn(savedStudent);

        // Testing the controller method
        ResponseEntity<Student> responseEntity = studentController.saveStudent(onboardStudentRequest);

        // Verifying the service method invocation
        verify(studentService, times(1)).onboardStudent(onboardStudentRequest);

        // Asserting the response entity
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedStudent, responseEntity.getBody());
    }

    @Test
    public void testGetSubjectsForStudent() {
        // Mocking the service method
        long studentId = 1L;
        List<Subject> subjects = new ArrayList<>();
        when(studentService.getSubjectsForStudent(studentId)).thenReturn(subjects);

        // Testing the controller method
        ResponseEntity<List<Subject>> responseEntity = studentController.getSubjectsForStudent(studentId);

        // Verifying the service method invocation
        verify(studentService, times(1)).getSubjectsForStudent(studentId);

        // Asserting the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subjects, responseEntity.getBody());
    }
}