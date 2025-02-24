package org.pages;


import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.http.services.RequestGlobals;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Role;
import org.data.services.EmployeeService;

import java.util.Arrays;
import java.util.List;

public class AddNewEmployee {

    @Property
    private String name;

    @Property
    private int age;

    @Property
    private String address;

    @Property
    private Role selectedRole;

    @Property
    private String errorMessage;

    @Inject
    private EmployeeService employeeService;

    @Inject
    private RequestGlobals requestGlobals;

    @InjectPage
    private EmployeeList employeeList;

    @Component
    private Form addEmployeeForm;

    // ✅ Role dropdown options
    @Property
    private List<Role> roleOptions = Arrays.asList(Role.ADMIN, Role.EMPLOYEE);

    void onValidateFromAddEmployeeForm() {
        if (name == null || name.isEmpty() || age < 18 || address == null || address.isEmpty() || selectedRole == null) {
            addEmployeeForm.recordError("All fields are required!");
        }
    }

    void onSuccessFromAddEmployeeForm() {

        employeeService.addEmployee(name, age, address, selectedRole);
    }
//        User loggedInUser = (User) requestGlobals.getHTTPServletRequest().getSession().getAttribute("loggedInUser");
//
//        if (loggedInUser != null) {
//            // ✅ Create a new User entity
//            User newUser = new User();
//            newUser.setUsername(name.toLowerCase()); // Example username
//            newUser.setPassword("default123"); // Default password (to be changed later)
//            newUser.setRole(selectedRole); // Assign selected role
//
//            // ✅ Save the user in the database
//            employeeService.saveUser(newUser);
//
//            // ✅ Create and save the employee record
//            Employee newEmployee = new Employee();
//            newEmployee.setName(name);
//            newEmployee.setAge(age);
//            newEmployee.setAddress(address);
//            newEmployee.setUser(newUser); // Link User entity
//
//            employeeService.saveEmployee(newEmployee);
//        }
//    }
}
