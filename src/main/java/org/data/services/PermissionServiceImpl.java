package org.data.services;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.data.entities.Employee;
import org.data.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

//    @Inject
    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public boolean hasEditPermission(Long employeeId) {
        return permissionRepository.hasEditPermission(employeeId);
    }
}