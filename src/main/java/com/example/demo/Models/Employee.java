package com.example.demo.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor(force = true)
@AllArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee implements Serializable {

    private static final List<String> MNC_LOCATIONS = Arrays.asList("India", "USA", "Japan", "UK", "Singapore", "Brazil","Thailand","Bhutan","Russia", "Pakistan");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MNClocation")
    private final String mncLocation;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "location")
    private String location;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "employee",
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Address> addresses;

    // This is for the manager of this employee
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_id")
    private Employee manager;

    // This is for the employees that this employee manages
    @JsonIgnore
    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Employee> subordinates;

    @JsonIgnore
    @OneToMany(mappedBy = "employee",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ManagerHistory> managerHistory;


    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private Set<DepartmentEmployee> departmentEmployees;

    public Employee(String mncLocation) {
        if(!MNC_LOCATIONS.contains(mncLocation)) throw new IllegalArgumentException("MNC location not available");
        this.mncLocation = mncLocation;
    }

}
