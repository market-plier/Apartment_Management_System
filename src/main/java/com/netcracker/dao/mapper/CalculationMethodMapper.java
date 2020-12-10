package com.netcracker.dao.mapper;

import com.netcracker.models.CalculationMethod;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CalculationMethodMapper implements RowMapper<CalculationMethod> {
    @Override
    public CalculationMethod mapRow(ResultSet resultSet, int i) throws SQLException {
        CalculationMethod calculationMethod = new CalculationMethod();
        calculationMethod.setCalculationMethodId(new BigInteger(resultSet.getString("calc_id")));
        calculationMethod.setName(resultSet.getString("calc_name"));
        return calculationMethod;
    }
}
