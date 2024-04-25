package com.example.demo.Controller;


import com.example.demo.DTO.AssignDepartmentRequest;
import com.example.demo.DTO.AssignDepartmentResponse;
import com.example.demo.DTO.DepartmentEmployees;
import com.example.demo.DTO.EmplyeeDepartments;
import com.example.demo.Models.*;
import com.example.demo.Repository.DepartmentEmployeeRepository;
import com.example.demo.Services.DepartmentEmployeeService;
import com.example.demo.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;


    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employee != null ? ResponseEntity.ok(employee) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        Employee createdEmployee = employeeService.createEmployee(employee);
        return ResponseEntity.ok(createdEmployee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/assignManager/{mid}")
    public ResponseEntity<Employee> upDateManager(@PathVariable Long id, @PathVariable Long mid) {
        Employee updatedEmployee = employeeService.changeManager(id, mid);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/manager/{managerId}")
    public ResponseEntity<List<Employee>> getEmployeesByManager(@PathVariable Long managerId) {
        return ResponseEntity.ok(employeeService.getEmployeesByManager(managerId));
    }
//    @GetMapping("/{id}/departments")
//    public ResponseEntity<List<DepartmentWithTimePeriod>> getDepartmentsOfEmployee(@PathVariable Long id) {
//        return ResponseEntity.ok(employeeService.getDepartmentsWithTimePeriod(id));
//    }

    @GetMapping("/{id}/prevManager")
    public ResponseEntity<List<ManagerHistory>> managerHistory(@PathVariable Long id){
        return ResponseEntity.ok(employeeService.getPreviousManagers(id));
    }

    @PutMapping("/{id}/addAddress")
    public ResponseEntity<Employee> addAddress(@PathVariable long id, @RequestBody Address address){
        return ResponseEntity.ok(employeeService.addAddress(id, address));
    }

    @PostMapping("/{id}/addDept")
    public ResponseEntity<AssignDepartmentResponse> assignDepartmenmt(@PathVariable long id, @RequestBody AssignDepartmentRequest request){
        return ResponseEntity.ok(departmentEmployeeService.addDepartmentToEmployee(id, request.getDeptId(), request.getStartDate()));
    }

    @GetMapping("GetDept/{id}")
    public ResponseEntity<List<EmplyeeDepartments>> getDepartments(@PathVariable long id){
        List<EmplyeeDepartments> response = departmentEmployeeRepository.findByEmployeeId(id)
                .stream()
                .map(i->{
                    EmplyeeDepartments employeeDepartmentsResponse = new EmplyeeDepartments();
                    Department d = i.getDepartment();
                    employeeDepartmentsResponse.setId(d.getId());
                    employeeDepartmentsResponse.setDName(d.getName());


                    return  employeeDepartmentsResponse;
                })
                .toList();

        return ResponseEntity.ok(response);
    }
}