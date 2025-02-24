package org.data.services;

import org.data.entities.Employee;
import org.data.entities.Role;

import java.util.Optional;

public interface LoginService {
//    boolean isValidUser(String username, String password);
//    Optional<Role> getUserRole(String username);
//    User authenticate(String username, String password);

    Employee isValidUser(String username, String password);
    Employee getLoggedInEmployee(String username);
}