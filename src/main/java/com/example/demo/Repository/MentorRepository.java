package com.example.demo.Repository;

import com.example.demo.Models.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MentorRepository extends JpaRepository<Mentor,Long> {
}
