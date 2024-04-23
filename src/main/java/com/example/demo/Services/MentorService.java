package com.example.demo.Services;

import com.example.demo.Models.Mentor;
import com.example.demo.Models.Subject;
import lombok.extern.slf4j.Slf4j;
import com.example.demo.Repository.MentorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class MentorService {
    @Autowired
    private MentorRepository mentorRepository;

    public Mentor save(Mentor mentorRequest) {
        log.info("Received request to onboard mentor {}", mentorRequest);
        Mentor mentor = mentorRepository.save(mentorRequest);
        log.info("successfully onboarded mentor {}", mentor);
        return mentor;

    }

    public Mentor saveMentor(Mentor mentor) {
        return mentorRepository.save(mentor);
    }
    public List<Mentor> getAllMentors() {
        return mentorRepository.findAll();
    }
}
