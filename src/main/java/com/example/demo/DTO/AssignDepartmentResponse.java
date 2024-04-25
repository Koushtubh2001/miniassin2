package com.example.demo.DTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AssignDepartmentResponse {
    private Long deptId;
    private Long empId;
    private LocalDate startDate;
}
