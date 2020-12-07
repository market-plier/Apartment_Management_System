package com.netcracker.dao.mapper;

import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;

import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentSubBillMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        ApartmentSubBill apartmentSubBill = new ApartmentSubBillBuilder()
                .withSubBillId(new BigInteger(resultSet.getString("sub_bill_id")))
                .withBalance(Double.valueOf(resultSet.getString("balance")))
                .withDept(Double.valueOf(resultSet.getString("debt")))
                .build();
        return apartmentSubBill;
    }
}
