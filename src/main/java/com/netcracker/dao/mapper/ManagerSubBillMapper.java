package com.netcracker.dao.mapper;

import com.netcracker.models.*;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import com.netcracker.models.PojoBuilder.ManagerBillBuilder;
import com.netcracker.models.PojoBuilder.ManagerBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerSubBillMapper implements RowMapper<ManagerSubBill> {


    @Override
    public ManagerSubBill mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagerSubBill managerSubBill = new ManagerSubBillBuilder()
                .withSubBillId(new BigInteger(rs.getString("sub_bill_id")))
                .withBalance(Double.valueOf(rs.getString("balance")))
                .withCommunalUtility(new CommunalUtilityBuilder()
                        .withCommunalUtilityId(new BigInteger(rs.getString("communal_util_id")))
                        .withCalculationMethod(new CalculationMethod
                                (new BigInteger(rs.getString("calc_method_id")),
                                rs.getString("calc_name")))
                        .withDeadline(rs.getDate("dead_line"))
                        .withDurationType(CommunalUtility.Duration.valueOf(rs.getString("duration_type")))
                        .withName(rs.getString("communal_name"))
                        .withStatus(CommunalUtility.Status.valueOf(rs.getString("status")))
                        .build()
                )
                .withManager(new ManagerBuilder()
                        .withAccountId(new BigInteger(rs.getString("account_id")))
                        .build())
                .build();

        return managerSubBill;
    }
}
