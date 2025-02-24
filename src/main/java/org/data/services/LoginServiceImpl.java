package org.data.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Role;
import org.data.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService {

//    @Inject
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public Employee isValidUser(String username, String password) {
        Employee employee = employeeRepository.findByUsername(username);

        if (employee != null && employee.getPassword().equals(password)) {
            return employee; // Return the authenticated employee object
        }
        return null; //  Return null if authentication fails
    }


    @Override
    @Transactional
    public Employee getLoggedInEmployee(String username) {
        return employeeRepository.findByUsername(username);
    }

//    @Override
//    @Transactional(readOnly = true)
//    public User authenticate(String username, String password) {
//        return userRepository.checkCredentials(username, password);
//    }
//
//    @Override
//    public boolean isValidUser(String username, String password) {
//        User user = userRepository.findByUsername(username);
//        return user != null && user.getPassword().equals(password); // Use hashing in production
//    }
//
//    @Override
//    public Optional<Role> getUserRole(String username) {
//        return Optional.ofNullable(userRepository.findByUsername(username))
//                .map(User::getRole);
//    }

}

