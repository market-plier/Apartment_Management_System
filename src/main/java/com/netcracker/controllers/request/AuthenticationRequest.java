package com.netcracker.controllers.request;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class AuthenticationRequest {
    @NotNull(message = "String is null")
    @Email(message = "Not valid email")
    private String email;
    @NotNull(message = "Password is null")
    @NotEmpty(message = "Password is empty")
    private String password;
}
