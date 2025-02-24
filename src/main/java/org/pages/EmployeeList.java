package org.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.http.services.RequestGlobals;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Role;
import org.data.services.EmployeeService;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.data.services.PermissionService;

public class EmployeeList {
        @Property
        private List<Employee> employees;
//        @Property
//        private Employee loggedInEmployee;

        @Property
        private Employee employee;
        @Inject
        private EmployeeService employeeService;

        @Inject
        private PermissionService permissionService;

        @InjectPage
        private AddNewEmployee addNewEmployee;

        @Inject
        private RequestGlobals requestGlobals;

    @Property
    private boolean isAdmin;
   public void setupRender() {
        // Fetch the logged-in user from the session
//        loggedInEmployee = (Employee) requestGlobals.getHTTPServletRequest().getSession().getAttribute("loggedInEmployee");
//
//        if (loggedInEmployee == null) {
//            throw new RuntimeException("User is not logged in!");
//        }
//
//        employees = employeeService.getAllEmployees();
//        isAdmin = loggedInEmployee.getRole().equals(Role.ADMIN);
        // Retrieve logged-in user from session
         employee = (Employee) requestGlobals.getRequest().getSession(true).getAttribute("loggedInUser");

        if (employee == null) {
            System.out.println("No logged-in user found in session.");
            return;
        }

        System.out.println("User in session: " + employee.getUsername() + " | Role: " + employee.getRole());

        // Check if user is admin
        isAdmin = employee.getRole().equals(Role.ADMIN);

        // Fetch employees from DB
        employees = employeeService.getAllEmployees();

        if (employees == null || employees.isEmpty()) {
            System.out.println("No employees found in the database.");
        }
    }

//    public boolean getShowAddButton() {
//        return isAdmin;
//    }
    public boolean canEdit(Employee employee) {
    return employee != null && (employee.getRole() == Role.ADMIN || permissionService.hasEditPermission(employee.getId()));
}
//    boolean canEdit(Employee employee) {
//        return isAdmin || permissionService.hasEditPermission(employee.getId());
//    }

//        void setup(Employee user) {
//            loggedInEmployee = user;
//            employees = employeeService.getAllEmployees();
//        }
//
//        boolean getShowAddButton() {
//            return loggedInEmployee.getRole().equals(Role.ADMIN);
//        }
//
//        boolean getCanEdit(Employee employee) {
//            return loggedInEmployee.getRole() .equals(Role.ADMIN) || loggedInEmployee.getPermissions().contains("EDIT");
//        }
    }
