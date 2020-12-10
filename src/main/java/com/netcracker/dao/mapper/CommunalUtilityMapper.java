package com.netcracker.dao.mapper;

import com.netcracker.models.CommunalUtility;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommunalUtilityMapper implements RowMapper<CommunalUtility> {
    @Override
    public CommunalUtility mapRow(ResultSet resultSet, int i) throws SQLException {
        return new CommunalUtilityBuilder()
                .withCommunalUtilityId(new BigInteger(resultSet.getString("com_util_id")))
                .withName(resultSet.getString("com_util_name"))
                .withStatus(CommunalUtility.Status.valueOf(resultSet.getString("com_util_status")))
                .withDeadline(resultSet.getDate("com_util_dline"))
                .withDurationType(CommunalUtility.Duration.valueOf(resultSet.getString("com_util_durtype")))
                .build();

    }
}
