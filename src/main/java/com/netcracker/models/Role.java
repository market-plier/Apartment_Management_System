package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Role {
    protected BigInteger roleId;
    protected String name;
}

