package com.netcracker.dao.mapper;

import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;
import com.netcracker.models.PojoBuilder.DebtPaymentOperationBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DebtPaymentOperationMapper implements RowMapper<DebtPaymentOperation> {
    @Override
    public DebtPaymentOperation mapRow(ResultSet resultSet, int i) throws SQLException {
        return new DebtPaymentOperationBuilder()
                .withOperationId(new BigInteger(resultSet.getString("operation_id")))
                .withSum(Double.parseDouble(resultSet.getString("sum")))
                .withCreatedAt(resultSet.getDate("created_at"))
                .withApartmentSubBill(new ApartmentSubBillBuilder()
                                .withSubBillId(new BigInteger(resultSet.getString("apartment_sub_bill_id")))
                                .build())
                .withManagerSubBill(new ManagerSubBillBuilder()
                                .withSubBillId(new BigInteger(resultSet.getString("manager_sub_bill_id")))
                                .build())
                .build();
    }
}
