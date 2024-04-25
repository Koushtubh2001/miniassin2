package com.example.demo.Services;


import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Models.Department;
import com.example.demo.Models.Employee;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Models.DepartmentEmployee;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Optional<Department> updateDepartment(Long id, Department updatedDepartment) {
        return departmentRepository.findById(id)
                .map(department -> {
                    department.setName(updatedDepartment.getName());
                    return departmentRepository.save(department);
                });
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }


    public List<Employee> getActiveEmployees(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        return department.getDepartmentEmployees().stream()
                .filter(departmentEmployee -> departmentEmployee.getEndDate() == null)
                .map(DepartmentEmployee::getEmployee)
                .collect(Collectors.toList());
    }
}
