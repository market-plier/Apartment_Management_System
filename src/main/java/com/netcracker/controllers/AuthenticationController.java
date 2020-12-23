package com.netcracker.controllers;


import com.netcracker.controllers.request.AuthenticationRequest;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.services.AuthenticationService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = "/auth/")
@Log4j
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @RequestMapping(value="login",method= RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationRequest authenticationRequest) throws DaoAccessException,AuthenticationException
    {
            return ResponseEntity.ok(authenticationService.login(authenticationRequest.getEmail()
                    ,authenticationRequest.getPassword()));
    }

    @RequestMapping(value="logout",method= RequestMethod.POST)
    public void logout(HttpServletRequest request, HttpServletResponse response)
    {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }


}
