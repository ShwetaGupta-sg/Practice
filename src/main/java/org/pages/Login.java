package org.pages;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.InjectPage;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Form;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.http.services.RequestGlobals;
import org.apache.tapestry5.http.services.Session;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PageRenderLinkSource;
import org.data.entities.Employee;
import org.data.services.LoginService;

public class Login {
    @Property
    private String username;

    @Property
    private String password;

    @Property
    private String loginMessage;

    @Inject
    private LoginService loginService;

    @InjectPage
    private EmployeeList employeeList;
    @Component
    private Form loginForm;

    @Inject
    private RequestGlobals requestGlobals;

    void onValidateFromLoginForm() {
        Employee loggedInEmployee = loginService.isValidUser(username, password);

        if (loggedInEmployee == null) {
            loginMessage = "Invalid username or password!"; // ✅ Set the message on failed login
            loginForm.recordError("Invalid username or password.");
        } else {
            requestGlobals.getHTTPServletRequest().getSession(true).setAttribute("loggedInUser", loggedInEmployee);
        }
    }

    Object onSuccessFromLoginForm() {
        return EmployeeList.class; // Redirect to EmployeeList page after successful login
    }
}