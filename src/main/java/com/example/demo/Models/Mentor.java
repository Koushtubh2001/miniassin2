package com.example.demo.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.util.HashSet;
import java.util.Set;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "mentor")

public class Mentor {

    // Columns

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name", unique=true)
    private String name ;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10-digit number")
    @Column(name = "phone")
    private String phone;

    // Relations

//    @OneToOne
//    @JoinColumn(name = "subject_id")
//    private Subject subject;
    @OneToMany(mappedBy = "mentor", fetch = FetchType.LAZY)
    private Set<Student> students;

    // Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {

        if(phone!=null && phone.length()!=10) throw new IllegalArgumentException("Phone number must be a 10-digit number");
        this.phone = phone;
    }

//    public Subject getSubject() {
//        return subject;
//    }
//
//    public void setSubject(Subject subject) {
//        this.subject = subject;
//    }
}