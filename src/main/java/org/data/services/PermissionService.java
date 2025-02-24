package org.data.services;

import org.data.entities.Employee;

public interface PermissionService {

    boolean hasEditPermission(Long employeeId);
}
