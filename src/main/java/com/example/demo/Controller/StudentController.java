package com.example.demo.Controller;

import com.example.demo.Models.Student;
import com.example.demo.Models.Subject;
import com.example.demo.Services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.DTO.OnboardStudentRequest;

import java.util.List;
import java.util.Set;

@RequestMapping("/student")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> getAllStudents(){
        List<Student> allStudents = studentService.getAll();
        return  allStudents;
    }


//    @GetMapping("{id}/{name}")
//    public ResponseEntity<Set<Subject>> getSubjects(@PathVariable Long id){
//        Student student = studentService.getStudentSubjects(id);
//        return ResponseEntity.ok(student.getSubjects());
//    }

    @GetMapping("{id}")
    public ResponseEntity<Subject> getElectiveSubjects(@PathVariable Long id){
        Subject eSubjects = studentService.getStudentElectiveSubjects(id);
        return ResponseEntity.ok(eSubjects);
    }


    @PostMapping("/onboard")
    public ResponseEntity<Student> saveStudent(@Valid @RequestBody OnboardStudentRequest onboardStudentRequest) {
        Student savedStudent = studentService.onboardStudent(onboardStudentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<List<Subject>> getSubjectsForStudent(@PathVariable long id) {
        List<Subject> subjects = studentService.getSubjectsForStudent(id);
        return ResponseEntity.ok(subjects);
    }

}
