package com.example.demo.DTO;

import com.example.demo.Models.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDepartmentRequest {
    private Long deptId;
    private LocalDate startDate;
}


