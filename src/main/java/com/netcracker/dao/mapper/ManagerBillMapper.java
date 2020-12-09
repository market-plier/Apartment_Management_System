package com.netcracker.dao.mapper;


import com.netcracker.models.ManagerBill;
import com.netcracker.models.PojoBuilder.ManagerBillBuilder;
import com.netcracker.models.PojoBuilder.ManagerBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerBillMapper implements RowMapper<ManagerBill> {
    @Override
    public ManagerBill mapRow(ResultSet resultSet, int i) throws SQLException {
        ManagerBill managerBill = new ManagerBillBuilder()
                .withManagerBillId(new BigInteger(resultSet.getString("sub_bill_id")))
                .withCardNumber(resultSet.getString("sub_bill_number"))
                .withManager(new ManagerBuilder().withAccountId(new BigInteger(resultSet.getString("manager_id"))).build())
                .build();
        return managerBill;
    }

}
