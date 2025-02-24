package org.data.repositories;
import org.data.entities.Employee;
import java.util.List;

public interface EmployeeRepository {
//    void saveEmployee(Employee employee);
//
//    List<Employee> getAllEmployees();
//
//    Employee getEmployeeById(int id);
//
//
//    User checkCredentials(String username, String password);

    void save(Employee employee);
    Employee findByUsername(String username);
    List<Employee> findAll();

    Employee findById(Long id);
}
