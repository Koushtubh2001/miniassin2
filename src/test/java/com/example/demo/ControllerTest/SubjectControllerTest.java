package com.example.demo.ControllerTest;

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

public class SubjectControllerTest {

    @InjectMocks
    private SubjectController subjectController;

    @Mock
    private SubjectService subjectService;

    public SubjectControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        // Mocking the service method
        List<Subject> subjects = new ArrayList<>();
        when(subjectService.getAllSubjects()).thenReturn(subjects);

        // Testing the controller method
        ResponseEntity<List<Subject>> responseEntity = subjectController.getAllSubjects();

        // Verifying the service method invocation
        verify(subjectService, times(1)).getAllSubjects();

        // Asserting the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subjects, responseEntity.getBody());
    }

    @Test
    public void testSaveSubject() {
        // Mocking the request object
        Subject subject = new Subject();

        // Mocking the service method
        when(subjectService.saveSubject(subject)).thenReturn(subject);

        // Testing the controller method
        ResponseEntity<Subject> responseEntity = subjectController.saveSubject(subject);

        // Verifying the service method invocation
        verify(subjectService, times(1)).saveSubject(subject);

        // Asserting the response entity
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(subject, responseEntity.getBody());
    }
}