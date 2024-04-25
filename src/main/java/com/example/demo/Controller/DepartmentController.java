package com.example.demo.Controller;


import com.example.demo.DTO.DepartmentEmployees;
import com.example.demo.Models.Department;
import com.example.demo.Models.DepartmentEmployee;
import com.example.demo.Models.Employee;
import com.example.demo.Repository.DepartmentEmployeeRepository;
import com.example.demo.Services.DepartmentEmployeeService;
import com.example.demo.Services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentEmployeeService departmentEmployeeService;

    @Autowired
    private DepartmentEmployeeRepository departmentEmployeeRepository;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Department addDepartment(@RequestBody Department newDepartment) {
        return departmentService.addDepartment(newDepartment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        return departmentService.updateDepartment(id, updatedDepartment)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        if (departmentService.getDepartmentById(id).isPresent()) {
            departmentService.deleteDepartment(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/getEmployee/{id}")
    public ResponseEntity<List<DepartmentEmployees>> getAllEmployee(@PathVariable long id){



       List<DepartmentEmployees> response = departmentEmployeeRepository.findByDepartmentIdAndEndDateIsNull(id)
               .stream()
               .map(i->{
                   DepartmentEmployees departmentEmployeesResponse = new DepartmentEmployees();
                   Employee e = i.getEmployee();
                   departmentEmployeesResponse.setId(e.getId());
                   departmentEmployeesResponse.setFname(e.getFirstName());
                   departmentEmployeesResponse.setLname(e.getLastName());
                   departmentEmployeesResponse.setEmail(e.getEmail());

                   return  departmentEmployeesResponse;
               })
               .toList();

       return ResponseEntity.ok(response);
    }




//    @GetMapping("/{id}/employees")
//    public ResponseEntity<List<Employee>> getActiveEmployees(@PathVariable Long id) {
//        return ResponseEntity.ok(departmentService.getActiveEmployees(id));
//    }
}