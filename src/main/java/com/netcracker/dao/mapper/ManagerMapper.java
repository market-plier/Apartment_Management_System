package com.netcracker.dao.mapper;

import com.netcracker.models.*;
import com.netcracker.models.PojoBuilder.ManagerBillBuilder;
import com.netcracker.models.PojoBuilder.ManagerBuilder;
import com.netcracker.models.Role;
import org.springframework.jdbc.core.RowMapper;


import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerMapper implements RowMapper<Manager> {
    @Override
    public Manager mapRow(ResultSet rs, int rowNum) throws SQLException  {
        Manager manager = new ManagerBuilder()
                .withManagerBill(new ManagerBillBuilder()
                        .withManagerBillId(new BigInteger(rs.getString("manager_bill_id")))
                        .withCardNumber(rs.getString("card_number"))
                        .build()
                    )
                .withAccountId(new BigInteger(rs.getString("account_id")))
                .withFirstName(rs.getString("first_name"))
                .withLastName(rs.getString("last_name"))
                .withEmail(rs.getString("email"))
                .withPassword(rs.getString("password"))
                .withPhoneNumber(rs.getString("phone"))
                .withRole(Role.valueOf(rs.getString("role_name")))
                .build();

        return manager;
    }
}
