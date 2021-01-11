package com.netcracker.dao.mapper;

import com.netcracker.models.CommunalUtility;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import com.netcracker.models.PojoBuilder.ManagerSpendingOperationBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerSpendingOperationMapper implements RowMapper<ManagerSpendingOperation> {
    @Override
    public ManagerSpendingOperation mapRow(ResultSet resultSet, int i) throws SQLException {

        return new ManagerSpendingOperationBuilder()
                .withOperationId(new BigInteger(resultSet.getString("manager_spending_id")))
                .withCreatedAt(resultSet.getTimestamp("operation_created_at"))
                .withDescription(resultSet.getString("manager_spending_description"))
                .withSum(new Double(resultSet.getString("manager_spending_sum")))
                .withManagerSubBill(new ManagerSubBillBuilder()
                        .withSubBillId(new BigInteger(resultSet.getString("manager_sub_bill")))
                        .withBalance(new Double(resultSet.getString("MANAGER_SUBBIL_BALANCE").replace(",",".")))
                        .withCommunalUtility(new CommunalUtilityBuilder()
                                .withCommunalUtilityId(new BigInteger(resultSet.getString("communal_utility_id")))
                                .withDurationType(CommunalUtility.Duration.valueOf(resultSet.getString("duration_type")))
                                .withName(resultSet.getString("name_communal"))
                                .withStatus(CommunalUtility.Status.valueOf(resultSet.getString("status")))
                                .build())
                        .build())
                .build();


    }
}
