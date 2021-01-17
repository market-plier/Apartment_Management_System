package com.netcracker.dao.mapper;


import com.netcracker.models.PojoBuilder.ApartmentBuilder;
import com.netcracker.models.PojoBuilder.ApartmentOperationBuilder;
import com.netcracker.models.ApartmentOperation;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentOperationMapper implements RowMapper<ApartmentOperation> {
    @Override
    public ApartmentOperation mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ApartmentOperationBuilder()
                .withOperationId(new BigInteger(resultSet.getString("operation_id")))
                .withSum(Double.parseDouble(resultSet.getString("sum")))
                .withCreatedAt(resultSet.getDate("created_at"))
                .withApartmentSubBill(new ApartmentSubBillBuilder()
                                .withSubBillId(new BigInteger(resultSet.getString("apartment_sub_bill_id")))
                                .build())
                .build();
    }
}
