package com.netcracker.dao.mapper;

import com.netcracker.models.CommunalUtility;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import com.netcracker.models.PojoBuilder.ManagerBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerSubBillNoManagerMapper implements RowMapper<ManagerSubBill> {
    @Override
    public ManagerSubBill mapRow(ResultSet rs, int rowNum) throws SQLException {
        ManagerSubBill managerSubBill = new ManagerSubBillBuilder()
                .withSubBillId(new BigInteger(rs.getString("sub_bill_id")))
                .withBalance(Double.valueOf(rs.getString("balance").replace(",",".")))
                .withCommunalUtility(new CommunalUtilityBuilder()
                        .withCommunalUtilityId(new BigInteger(rs.getString("communal_util_id")))
                        .withName(rs.getString("communal_name"))
                        .withDurationType(CommunalUtility.Duration.valueOf(rs.getString("duration_type")))
                        .withStatus(CommunalUtility.Status.valueOf(rs.getString("status")))
                        .withDeadline(rs.getDate("dead_line"))
                        .build())
                .build();
        return managerSubBill;
    }
}
