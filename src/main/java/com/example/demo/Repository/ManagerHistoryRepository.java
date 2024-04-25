package com.example.demo.Repository;


import com.example.demo.Models.Employee;
import com.example.demo.Models.ManagerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ManagerHistoryRepository extends JpaRepository<ManagerHistory, Long> {

    ManagerHistory findLastByEmployee(Employee employee);
    List<ManagerHistory> findAllByEmployee(Employee employee);

}