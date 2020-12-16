package com.netcracker.models;




public enum Role {
    MANAGER(6,"MANAGER"),
    OWNER(5,"OWNER");

    private int roleCode;
    private String roleName;


    Role(int roleCode, String roleName) {
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public int getRoleCode() {
        return roleCode;
    }

    public String getRoleName() {
        return roleName;
    }
}


