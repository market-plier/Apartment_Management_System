package com.netcracker.dao.mapper;

import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;
import com.netcracker.models.PojoBuilder.DebtPaymentOperationBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class DebtPaymentOperationMapper implements RowMapper<DebtPaymentOperation> {
    @SneakyThrows
    @Override
    public DebtPaymentOperation mapRow(ResultSet resultSet, int i) throws SQLException {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        try {
            return new DebtPaymentOperationBuilder()
                    .withOperationId(new BigInteger(resultSet.getString("operation_id")))
                    .withSum(format.parse(resultSet.getString("sum")).doubleValue())
                    .withCreatedAt(resultSet.getDate("created_at"))
                    .withApartmentSubBill(new ApartmentSubBillBuilder()
                            .withSubBillId(new BigInteger(resultSet.getString("apartment_sub_bill_id")))
                            .build())
                    .withManagerSubBill(new ManagerSubBillBuilder()
                            .withSubBillId(new BigInteger(resultSet.getString("manager_sub_bill_id")))
                            .build())
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
