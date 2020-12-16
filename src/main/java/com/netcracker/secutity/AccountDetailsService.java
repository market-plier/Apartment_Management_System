package com.netcracker.secutity;

import com.netcracker.models.Account;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.secutity.jwt.JwtAccountFactory;
import com.netcracker.services.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@Service("AccountDetailsService")
public class AccountDetailsService implements UserDetailsService {


    private final AccountService accountService;

    @Autowired
    public AccountDetailsService(AccountService accountService) {
        this.accountService = accountService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Account account = accountService.getAccountByEmail(email);

        if (account == null) {
            throw new UsernameNotFoundException("user not found");
        }

         JwtAccount jwtAccount = JwtAccountFactory.create(account);

        log.info("IN loadUserByUsername - user with email: {} successfully loaded", email);

        return jwtAccount;
    }

}
