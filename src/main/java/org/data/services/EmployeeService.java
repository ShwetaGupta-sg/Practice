package org.data.services;

import org.data.entities.Employee;
import org.data.entities.Role;

import java.util.List;


public interface EmployeeService {
//    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);


    void addEmployee(String name, int age, String address, Role role);
}
