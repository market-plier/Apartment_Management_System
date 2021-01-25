package com.netcracker.dao.mapper;

import com.netcracker.models.ApartmentOperation;
import com.netcracker.models.PojoBuilder.ApartmentOperationBuilder;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApartmentOperationWithCommUtilMapper implements RowMapper<ApartmentOperation> {

    @Override
    public ApartmentOperation mapRow(ResultSet resultSet, int i) throws SQLException {


        return new ApartmentOperationBuilder()
                .withOperationId(new BigInteger(resultSet.getString("operation_id")))
                .withSum(Double.parseDouble(resultSet.getString("sum")))
                .withCreatedAt(resultSet.getTimestamp("created_at"))
                .withApartmentSubBill(new ApartmentSubBillBuilder()
                        .withCommunalUtility(new CommunalUtilityBuilder().withName(resultSet.getString("communal_name")).build())
                        .withSubBillId(new BigInteger(resultSet.getString("apartment_sub_bill_id")))
                        .build())
                .build();
    }
}
