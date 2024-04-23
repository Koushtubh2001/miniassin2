package com.example.demo.ServiceTest;

import com.example.demo.Models.Mentor;
import com.example.demo.Repository.MentorRepository;
import com.example.demo.Services.MentorService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MentorServiceTest {

    @InjectMocks
    private MentorService mentorService;

    @Mock
    private MentorRepository mentorRepository;

    public MentorServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Mocking the mentor request
        Mentor mentorRequest = new Mentor();

        // Mocking the mentor repository save method
        when(mentorRepository.save(mentorRequest)).thenReturn(mentorRequest);

        // Testing the service method
        Mentor savedMentor = mentorService.save(mentorRequest);

        // Verifying the repository save method invocation
        verify(mentorRepository, times(1)).save(mentorRequest);

        // Asserting the result
        assertEquals(mentorRequest, savedMentor);
    }

    @Test
    public void testSaveMentor() {
        // Mocking the mentor
        Mentor mentor = new Mentor();

        // Mocking the mentor repository save method
        when(mentorRepository.save(mentor)).thenReturn(mentor);

        // Testing the service method
        Mentor savedMentor = mentorService.saveMentor(mentor);

        // Verifying the repository save method invocation
        verify(mentorRepository, times(1)).save(mentor);

        // Asserting the result
        assertEquals(mentor, savedMentor);
    }

    @Test
    public void testGetAllMentors() {
        // Mocking the list of mentors
        List<Mentor> mentors = new ArrayList<>();
        mentors.add(new Mentor());
        mentors.add(new Mentor());

        // Mocking the mentor repository find all method
        when(mentorRepository.findAll()).thenReturn(mentors);

        // Testing the service method
        List<Mentor> result = mentorService.getAllMentors();

        // Verifying the repository find all method invocation
        verify(mentorRepository, times(1)).findAll();

        // Asserting the result
        assertEquals(mentors.size(), result.size());
        assertEquals(mentors.get(0), result.get(0));
        assertEquals(mentors.get(1), result.get(1));
    }
}