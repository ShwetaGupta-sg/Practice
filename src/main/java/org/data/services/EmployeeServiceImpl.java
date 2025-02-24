package org.data.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Permission;
import org.data.entities.Role;
import org.data.repositories.EmployeeRepository;
import org.data.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

//    @Inject
    @Autowired
    private EmployeeRepository employeeRepository;

//    @Inject
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    @Override
    public void addEmployee(String name, int age, String address, Role role) {
        Employee employee = new Employee();
//        employee.setUsername(name);
//        employee.setPassword(password);
        employee.setName(name);
        employee.setAge(age);
        employee.setAddress(address);
        employee.setRole(role);

        Set<Permission> permissions = new HashSet<>();

        if (role.equals(Role.ADMIN)) {
            permissions.add(permissionRepository.findByName("ADD"));
            permissions.add(permissionRepository.findByName("EDIT"));
            permissions.add(permissionRepository.findByName("VIEW"));
        } else {
            permissions.add(permissionRepository.findByName("VIEW"));
        }

        employee.setPermissions(permissions);
        employeeRepository.save(employee);
    }
}
