package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.demo.Models.Employee;
import com.example.demo.Repository.EmployeeRepository;
import java.time.LocalDate;
import java.util.List;

@Component
public class BirthdayScheduler {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Scheduled(cron = "0 0 0 * * *") // Run at midnight every day
    public void printHappyBirthday() {
        LocalDate today = LocalDate.now();
        List<Employee> employees = employeeRepository.findByDateOfBirth(today.getMonthValue(), today.getDayOfMonth());

        for (Employee employee : employees) {
            System.out.println("Happy Birthday, " + employee.getFirstName() + "!");
        }
    }
}
