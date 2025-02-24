package org.data.repositories;

import org.data.entities.Employee;
import org.data.entities.Permission;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PermissionRepository {
    Permission findByName(String name);
    List<Permission> findAll();

    List<String> getPermissionsByEmployeeId(Long employeeId);
    boolean hasPermission(Employee employee, String permissionName);
    boolean hasEditPermission(Long employeeId);

}
