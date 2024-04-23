package com.example.demo.Controller;

import com.example.demo.Models.Mentor;
import com.example.demo.Repository.MentorRepository;
import com.example.demo.Services.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/mentor")
public class MentorController {

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorService mentorService;

    @PostMapping("/onboard")
    public ResponseEntity<Mentor> onboardMentor(@RequestBody Mentor mentorRequest) {
//        log.info("Received request to onboard mentor {}", mentorRequest);
//        Mentor mentor = mentorRepository.save(mentorRequest);
//        log.info("successfully onboarded mentor {}", mentor);

        Mentor mentor = mentorService.save(mentorRequest);
        return ResponseEntity.ok(mentor);
    }

}
