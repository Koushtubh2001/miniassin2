package com.example.demo.ServiceTest;

import com.example.demo.DTO.OnboardStudentRequest;
import com.example.demo.Models.Mentor;
import com.example.demo.Models.Student;
import com.example.demo.Models.Subject;
import com.example.demo.Repository.MentorRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.SubjectRepository;
import com.example.demo.Services.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MentorRepository mentorRepository;

    @Mock
    private SubjectRepository subjectRepository;

    public StudentServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSave() {
        // Mocking the student
        Student student = new Student();

        // Mocking the student repository save method
        when(studentRepository.save(student)).thenReturn(student);

        // Testing the service method
        Student savedStudent = studentService.save(student);

        // Verifying the repository save method invocation
        verify(studentRepository, times(1)).save(student);

        // Asserting the result
        assertEquals(student, savedStudent);
    }

    @Test
    public void testOnboardStudent() {
        // Mocking the request
        OnboardStudentRequest request = new OnboardStudentRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setAge(25);
        request.setMentorId(1L);
        request.setSubjectIds((List<Long>) new ArrayList<>(Arrays.asList(1L, 2L, 3L)));

        // Mocking mentor repository
        Mentor mentor = new Mentor();
        when(mentorRepository.findById(request.getMentorId())).thenReturn(Optional.of(mentor));

        // Mocking subject repository
        List<Subject> subjects = Arrays.asList(new Subject(), new Subject(), new Subject());
        when(subjectRepository.findAllById(request.getSubjectIds())).thenReturn(subjects);

        // Mocking student repository save method
        Student student = new Student();
        when(studentRepository.save(any())).thenReturn(student);

        // Testing the service method
        Student onboardedStudent = studentService.onboardStudent(request);

        // Verifying mentor repository method invocation
        verify(mentorRepository, times(1)).findById(request.getMentorId());

        // Verifying subject repository method invocation
        verify(subjectRepository, times(1)).findAllById(request.getSubjectIds());

        // Verifying student repository method invocation
        verify(studentRepository, times(1)).save(any());

        // Asserting the result
        assertNotNull(onboardedStudent);
        assertEquals(request.getFirstName(), onboardedStudent.getFirstName());
        assertEquals(request.getLastName(), onboardedStudent.getLastName());
        assertEquals(request.getAge(), onboardedStudent.getAge());
        assertEquals(mentor, onboardedStudent.getMentor());
        assertEquals(new HashSet<>(subjects), onboardedStudent.getSubjects());
    }

    // Add tests for other methods here
}