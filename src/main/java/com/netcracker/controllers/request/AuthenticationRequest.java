package com.netcracker.controllers.request;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
