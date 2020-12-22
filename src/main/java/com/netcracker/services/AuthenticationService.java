package com.netcracker.services;

import com.netcracker.models.Account;
import com.netcracker.secutity.jwt.JwtTokenProvider;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Log4j
public class AuthenticationService {



    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final AccountService accountService;

    @Autowired
    public AuthenticationService(AuthenticationManager authenticationManager,
                                    JwtTokenProvider jwtTokenProvider,
                                    AccountService accountService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.accountService = accountService;
    }

    public Map<Object, Object> login(String email, String password) throws AuthenticationException
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Account account = accountService.getAccountByEmail(email);

        if (account == null)
        {
            log.error("IN Service method login Invalid password or login");
            throw new UsernameNotFoundException("Invalid password or login");
        }

        String token = jwtTokenProvider.createToken(email,account.getRole());

        Map<Object, Object> response = new HashMap<>();
        response.put("username", account.getEmail());
        response.put("token", token);
        return response;
    }


}
