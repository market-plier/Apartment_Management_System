package com.netcracker.controllers;


import com.netcracker.controllers.dto.AuthenticationRequestDto;
import com.netcracker.models.Account;
import com.netcracker.secutity.jwt.JwtTokenProvider;
import com.netcracker.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth/")
@Slf4j
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final AccountService accountService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
    }

    @RequestMapping(value="login",method= RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto authenticationRequestDto)
    {
        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(),
                    authenticationRequestDto.getPassword()));
            Account account = accountService.getAccountByEmail(authenticationRequestDto.getEmail());

            if (account == null)
            {
                throw new UsernameNotFoundException("not found user");
            }

            String token = jwtTokenProvider.createToken(authenticationRequestDto.getEmail(),account.getRole());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", account.getEmail());
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {

            return new ResponseEntity<>("invalid password or login", HttpStatus.FORBIDDEN);

        }
    }
}
