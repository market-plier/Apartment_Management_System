package com.netcracker.controllers.request;

import lombok.Data;

import java.math.BigInteger;

@Data
public class UserResponse {
    BigInteger accountId;
    String role;
    String email;
}
