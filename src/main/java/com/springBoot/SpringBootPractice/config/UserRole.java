package com.springBoot.SpringBootPractice.config;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.springBoot.SpringBootPractice.config.UserPermission.*;

public enum UserRole {
    EMPLOYEE(Sets.newHashSet(EMPLOYEE_READ )),
    ADMIN(Sets.newHashSet(EMPLOYEE_WRITE,EMPLOYEE_READ,DEPARTMENT_READ,DEPARTMENT_WRITE));

    private final Set<UserPermission>permissions;


    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }
}
