package com.example.demo.Services;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import com.example.demo.Exceptions.ResourceNotFoundException;
import com.example.demo.Models.Address;
import com.example.demo.Models.Employee;
import com.example.demo.Models.ManagerHistory;
import com.example.demo.Repository.AddressRepository;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.ManagerHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManagerHistoryRepository managerHistoryRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return (Employee) employeeRepository.findById(id).orElse(null);
    }

    public Employee createEmployee(Employee employee) {
        return (Employee) employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = (Employee) employeeRepository.findById(id).orElse(null);
        if (employee != null) {
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            employee.setDateOfBirth(employeeDetails.getDateOfBirth());
            employee.setLocation(employeeDetails.getLocation());
            return (Employee) employeeRepository.save(employee);
        }
        return null;
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee changeManager(Long employeeId, Long newManagerId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        Employee newManager = employeeRepository.findById(newManagerId)
                .orElseThrow(() -> new ResourceNotFoundException("New manager not found"));

        // End the last manager history
        ManagerHistory lastManagerHistory = managerHistoryRepository.findLastByEmployee(employee);
        if (lastManagerHistory != null) {
            lastManagerHistory.setEndDate(new Date());
            managerHistoryRepository.save(lastManagerHistory);
        }

        // Start a new manager history
        ManagerHistory newManagerHistory = new ManagerHistory();
        newManagerHistory.setEmployee(employee);
        newManagerHistory.setManager(newManager);
        newManagerHistory.setStartDate(new Date());
        managerHistoryRepository.save(newManagerHistory);

        // Change the manager
        employee.setManager(newManager);
        employeeRepository.save(employee);

        return employee;

    }

    public List getPreviousManagers(Long employeeId){
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        return managerHistoryRepository.findAllByEmployee(employee);
    }

    public List<Employee> getEmployeesByManager(Long managerId) {
        return employeeRepository.findByManagerId(managerId);
    }



    public Employee addAddress(Long employeeId, Address newAddress) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));


        // Set the current address to false for all addresses
        for (Address address : employee.getAddresses()) {
            address.setCurrent(false);
        }

        newAddress.setEmployee(employee);
        addressRepository.save(newAddress);
        List<Address> list = employee.getAddresses();
        list.add(newAddress);
        employee.setAddresses(list);

        employeeRepository.save(employee);
        return employee;
    }


}