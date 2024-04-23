package com.example.demo.Services;

import com.example.demo.DTO.OnboardStudentRequest;
import com.example.demo.Models.Mentor;
import com.example.demo.Models.Student;
import com.example.demo.Models.Subject;
import com.example.demo.Repository.MentorRepository;
import com.example.demo.Repository.StudentRepository;
import com.example.demo.Repository.SubjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Service
public class StudentService {

    private static final int MAX_SUBJECTS_PER_STUDENT = 6;
    private static final int MAX_ELECTIVES_PER_STUDENT = 1;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    public Student save(Student student){
        studentRepository.save(student);
        return student;
    }


    public Student onboardStudent(OnboardStudentRequest request) {
        log.info("Received request to onboard mentor {}", request);
        Student student = new Student();
        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setAge(request.getAge());

        if (request.getMentorId() != null) {
            Mentor mentor = mentorRepository.findById(request.getMentorId()).orElse(null);
            if (mentor != null) {
                student.setMentor(mentor);
            }
        }

        if (request.getSubjectIds() != null && !request.getSubjectIds().isEmpty()) {
            if (request.getSubjectIds().size() > MAX_SUBJECTS_PER_STUDENT) {
                log.error("Requested number of subjects can't be greater than {}", MAX_SUBJECTS_PER_STUDENT);
                throw new RuntimeException(String.format("Requested number of subjects can't be greater than %d", MAX_SUBJECTS_PER_STUDENT));
            }

            List<Subject> subjects = subjectRepository.findAllById(request.getSubjectIds());

            long electiveCount = subjects.stream().filter(Subject::isElective).count();
            if (electiveCount > MAX_ELECTIVES_PER_STUDENT) {
                log.error("Requested number of electives can't be greater than {}", MAX_ELECTIVES_PER_STUDENT);
                throw new RuntimeException(String.format("Requested number of electives can't be greater than %d", MAX_ELECTIVES_PER_STUDENT));
            }
            student.setSubjects(new HashSet<>(subjects));
        }

        Student updatedStudent = studentRepository.save(student);
        return student;
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student getStudentSubjects(long id){
        return studentRepository.findById(id).orElse(null);
    }

    public Subject getStudentElectiveSubjects(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        for(Subject sub: student.getSubjects()){
            if(sub.isElective()) return sub;
        }
        return null;

    }


    public List<Subject> getSubjectsForStudent(long id) {
        List<Subject> subjects = subjectRepository.findByStudents_id(id);
        return subjects;
    }
}
