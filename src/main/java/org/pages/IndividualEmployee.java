package org.pages;

import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.http.services.RequestGlobals;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.entities.Role;
import org.data.repositories.PermissionRepository;
import org.data.services.EmployeeService;
import org.data.services.PermissionService;

import java.util.List;

public class IndividualEmployee {

    @Property
    private Employee employee;

    @Property
    private boolean canEdit;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private PermissionService permissionService; // ✅ Check employee permissions

    @InjectPage
    private EmployeeList employeeList;

    @Inject
    private RequestGlobals requestGlobals;

    @Inject
    PermissionRepository permissionRepository;
    private boolean checkEditPermission(Long employeeId) {
        Employee loggedInEmployee = (Employee) requestGlobals.getHTTPServletRequest().getSession().getAttribute("loggedInUser");

        if (loggedInEmployee == null) {
            return false; // No user logged in
        }

        // Admins have all permissions
        if (loggedInEmployee.getRole() .equals(Role.ADMIN)) {
            return true;
        }

        // Check if the employee has "EDIT" permission
        return permissionRepository.hasPermission(loggedInEmployee, "EDIT");
    }

}
