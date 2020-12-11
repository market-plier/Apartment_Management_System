package com.netcracker.models;



public enum Role {
    MANAGER(6),
    OWNER(5);

    private int roleCode;

    Role(int roleCode) {
        this.roleCode = roleCode;
    }
}


