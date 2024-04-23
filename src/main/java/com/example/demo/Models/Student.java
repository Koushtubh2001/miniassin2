package com.example.demo.Models;



import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}))
public class Student {

    // Columns
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Getters & Setters
    @Getter
    @Setter
    @Column(name = "first_name")
    private String firstName ;

    @Getter
    @Setter
    @Column(name = "last_name")
    private String lastName ;

    @Getter
    @Column(name = "age")
    private int age;

    public void setAge(int age){
        if(age<4 || age> 30) throw new IllegalArgumentException("Age must be between 4 to 30!");
        this.age = age;
    }

    // Relations

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", referencedColumnName = "id")
    private Mentor mentor;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "subjects",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id")
    )
    private Set<Subject> subjects;

}
