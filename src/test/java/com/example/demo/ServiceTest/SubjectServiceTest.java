package com.example.demo.ServiceTest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubjectServiceTest {

    @InjectMocks
    private SubjectService subjectService;

    @Mock
    private SubjectRepository subjectRepository;

    public SubjectServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllSubjects() {
        // Mocking the list of subjects
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject());
        subjects.add(new Subject());

        // Mocking the subject repository find all method
        when(subjectRepository.findAll()).thenReturn(subjects);

        // Testing the service method
        List<Subject> result = subjectService.getAllSubjects();

        // Verifying the repository find all method invocation
        verify(subjectRepository, times(1)).findAll();

        // Asserting the result
        assertEquals(subjects.size(), result.size());
        assertEquals(subjects.get(0), result.get(0));
        assertEquals(subjects.get(1), result.get(1));
    }

    @Test
    public void testSaveSubject() {
        // Mocking the subject
        Subject subject = new Subject();

        // Mocking the subject repository save method
        when(subjectRepository.save(subject)).thenReturn(subject);

        // Testing the service method
        Subject savedSubject = subjectService.saveSubject(subject);

        // Verifying the repository save method invocation
        verify(subjectRepository, times(1)).save(subject);

        // Asserting the result
        assertEquals(subject, savedSubject);
    }
}