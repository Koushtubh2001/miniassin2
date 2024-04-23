package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "subject")
public class Subject {

    // Columns
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subject code is required")
    @Pattern(regexp = "\\d{6}", message = "Subject code must be a 6-digit number")
    @Getter
    private String code;

    @Getter
    @Setter
    @Column(name = "Elective")
    @JsonProperty("isElective")
    private boolean isElective;



    @NotBlank(message = "Subject name is required")
    @Size(max = 30, message = "Subject name must be less than 30 characters")
    @Getter
    private String name;

    public void setCode(String code) {
        if(code.length()!=6) throw new IllegalArgumentException("Subject code must be a 6-digit number");
        this.code = code;
    }

    public void setName(String name) {
        if(name.length()>30) throw new IllegalArgumentException("Subject name must be less than size of 30");
        this.name = name;
    }

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private Set<Student> students;


    // Getters & Setters

}