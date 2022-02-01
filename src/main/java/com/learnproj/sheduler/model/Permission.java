package com.learnproj.sheduler.model;

public enum Permission {
    ALL_READ("all:read"),
    ALL_WRITE("admin:write"),
    MANAGER_WRITE("manager:write");



    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
