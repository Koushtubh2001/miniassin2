package com.example.demo.Repository;


import com.example.demo.Models.Department;
import com.example.demo.Models.DepartmentEmployee;
import com.example.demo.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentEmployeeRepository extends JpaRepository<DepartmentEmployee, Long> {

    List<DepartmentEmployee> findByDepartmentIdAndEndDateIsNull(Long departmentId);
    List<DepartmentEmployee> findByEmployeeId(Long employeeId);

}