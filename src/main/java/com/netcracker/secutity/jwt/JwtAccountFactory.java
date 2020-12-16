package com.netcracker.secutity.jwt;

import com.netcracker.models.Account;
import com.netcracker.models.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public final class JwtAccountFactory {
    public JwtAccountFactory() {
    }

    public static JwtAccount create(Account account)
    {
        return new JwtAccount(
                account.getAccountId(),
                account.getEmail(),
                account.getFirstName(),
                account.getLastName(),
                account.getPassword(),
                mapToGrantedAuthorities(account.getRole())
        );
    }

    private static GrantedAuthority mapToGrantedAuthorities(Role role) {
        String ROLE_PREFIX = "ROLE_";
        return new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName());
    }
}