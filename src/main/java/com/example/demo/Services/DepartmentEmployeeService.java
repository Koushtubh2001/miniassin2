package com.example.demo.Services;


import com.example.demo.DTO.AssignDepartmentResponse;
import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Models.Department;
import com.example.demo.Models.DepartmentEmployee;
import com.example.demo.Models.Employee;
import com.example.demo.Repository.DepartmentEmployeeRepository;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepartmentEmployeeService {

//    public List<DepartmentEmployee> getActiveEmployees(Long departmentId) {
//        return departmentEmployeeRepository.findByDepartmentIdAndIsActive(departmentId, true);
//    }
//
//    public List<DepartmentEmployee> getDepartmentsOfEmployee(Long employeeId) {
//        return departmentEmployeeRepository.findByEmployeeId(employeeId);
//    }

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;
    ;
    public AssignDepartmentResponse addDepartmentToEmployee(Long employeeId, Long departmentId, LocalDate startDate) {
        // Get the Employee and Department entities from the database
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        // Create a new DepartmentEmployee entity
        DepartmentEmployee departmentEmployee = new DepartmentEmployee();
        departmentEmployee.setEmployee(employee);
        departmentEmployee.setDepartment(department);
        departmentEmployee.setStartDate(startDate);

        AssignDepartmentResponse assignDepartmentResponse = new AssignDepartmentResponse();
        assignDepartmentResponse.setDeptId(departmentId);
        assignDepartmentResponse.setEmpId(employeeId);
        assignDepartmentResponse.setStartDate(startDate);

        // Save the DepartmentEmployee entity to the database
         departmentEmployeeRepository.save(departmentEmployee);
         return assignDepartmentResponse;
    }


//    public DepartmentEmployee endDepartmentForEmployee(Long employeeId, Long departmentId, LocalDate endDate) {
//        // Get the Employee and Department entities from the database
//        Employee employee = employeeRepository.findById(employeeId)
//                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
//        Department department = departmentRepository.findById(departmentId)
//                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
//
//        // Find the DepartmentEmployee record that needs to be updated
//        DepartmentEmployee departmentEmployee = (DepartmentEmployee) departmentEmployeeRepository
//                .findByEmployeeAndDepartmentAndEndDateIsNull(employee, department)
//                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
//
//        // Set the endDate and save the record
//        departmentEmployee.setEndDate(endDate);
//        return departmentEmployeeRepository.save(departmentEmployee);
//    }
}

