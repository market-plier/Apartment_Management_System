package com.netcracker.dao.impl;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.mapper.CalculationMethodMapper;
import com.netcracker.models.CalculationMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class CalculationMethodDaoImpl implements CalculationMethodDao {
    final
    JdbcTemplate jdbcTemplate;

    public CalculationMethodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CalculationMethod> getAllCalculationMethods() {
        return jdbcTemplate.query(getAllCalculationMethods, new CalculationMethodMapper());
    }

    @Override
    public CalculationMethod getCalculationMethodById(BigInteger id) {
        return (jdbcTemplate.queryForObject(getCalculationMethodById, new CalculationMethodMapper(), id));
    }

    @Override
    public CalculationMethod getCalculationMethodByCommunalUtilityId(BigInteger id) {
        return (jdbcTemplate.queryForObject(getCalculationMethodByCommunalUtilityId, new CalculationMethodMapper(), id));
    }

    @Override
    public void updateCalculationMethod(CalculationMethod calculationMethod) {
        jdbcTemplate.update(updateCalculationMethod,
                calculationMethod.getName(),
                calculationMethod.getCalculationMethodId());
    }

    @Override
    public void createCalculationMethod(CalculationMethod calculationMethod) {
        jdbcTemplate.update(createCalculationMethodObject);
        jdbcTemplate.update(createCalculationMethodAttributes, calculationMethod.getName());
    }

    @Override
    public void deleteCalculationMethod(BigInteger id) {
        jdbcTemplate.update(deleteCalculationMethod, id);
    }
}
