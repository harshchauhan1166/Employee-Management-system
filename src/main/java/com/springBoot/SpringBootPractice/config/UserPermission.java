package com.springBoot.SpringBootPractice.config;

public enum                                 UserPermission {
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_WRITE("employee:write"),
    DEPARTMENT_READ("department:read"),
    DEPARTMENT_WRITE("department:write");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
