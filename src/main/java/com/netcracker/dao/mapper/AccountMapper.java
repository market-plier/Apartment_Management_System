package com.netcracker.dao.mapper;

import com.netcracker.models.Account;
import com.netcracker.models.PojoBuilder.AccountBuilder;
import com.netcracker.models.Role;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<Account> {
    @Override
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AccountBuilder().withAccountId(new BigInteger(resultSet.getString("account_id")))
                .withEmail(resultSet.getString("email"))
                .withLastName(resultSet.getString("last_name"))
                .withFirstName(resultSet.getString("first_name"))
                .withPassword(resultSet.getString("password"))
                .withPhoneNumber(resultSet.getString("phone"))
                .withRole(new Role(null,resultSet.getString("role_name")))
                .build();
    }
}
