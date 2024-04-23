package com.example.demo.Repository;

import com.example.demo.Models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByStudents_id(long id);
}
