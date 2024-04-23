package com.example.demo.ControllerTest;


import com.example.demo.Controller.MentorController;
import com.example.demo.Models.Mentor;
import com.example.demo.Repository.MentorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

public class MentorControllerTest {

    @InjectMocks
    private MentorController mentorController;

    @Mock
    private MentorRepository mentorRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOnboardMentor() {
        // Mocking mentor request
        Mentor mentorRequest = new Mentor();
        mentorRequest.setName("John Doe");
        mentorRequest.setPhone("9876543456");

        // Mocking repository save method
        when(mentorRepository.save(mentorRequest)).thenReturn(mentorRequest);

        // Calling controller method
        ResponseEntity<Mentor> responseEntity = mentorController.onboardMentor(mentorRequest);

        // Verifying repository save method is called
        verify(mentorRepository, times(1)).save(mentorRequest);

        // Verifying response entity status code
        assert responseEntity.getStatusCode().equals(HttpStatus.OK);

        // Verifying response entity body
        assert responseEntity.getBody().equals(mentorRequest);
    }
}