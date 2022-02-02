package com.learnproj.sheduler.model;

public enum Permission {
    ALL_READ("all:read"),
    ADMIN_WRITE("admin:write"),
    ALL_WRITE("all:write");



    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
