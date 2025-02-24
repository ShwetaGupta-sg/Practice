package org.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.http.services.RequestGlobals;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Role;
import org.data.services.EmployeeService;

import java.util.List;

import org.apache.tapestry5.annotations.Property;

public class EmployeeList {
        @Property
        private List<Employee> employees;

        @Property
        private Employee loggedInEmployee;

        @Inject
        private EmployeeService employeeService;

        @InjectPage
        private AddNewEmployee addNewEmployee;

        @Inject
        private RequestGlobals requestGlobals;

        void setup(Employee user) {
            loggedInEmployee = user;
            employees = employeeService.getAllEmployees();
        }

        boolean getShowAddButton() {
            return loggedInEmployee.getRole().equals(Role.ADMIN);
        }

        boolean getCanEdit(Employee employee) {
            return loggedInEmployee.getRole() .equals(Role.ADMIN) || loggedInEmployee.getPermissions().contains("EDIT");
        }
    }
