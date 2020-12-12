package com.netcracker.dao.impl;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.mapper.CalculationMethodMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import org.springframework.dao.DataAccessException;
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
        try {
            return jdbcTemplate.query(getAllCalculationMethods, new CalculationMethodMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_CALCULATION_METHODS, e.getCause());
        }
    }

    @Override
    public CalculationMethod getCalculationMethodById(BigInteger id) {
        try {
            return (jdbcTemplate.queryForObject(getCalculationMethodById, new CalculationMethodMapper(), id));
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_CALCULATION_METHOD_BY_ID, e.getCause());
        }
    }

    @Override
    public CalculationMethod getCalculationMethodByCommunalUtilityId(BigInteger id) {
        try {
            return (jdbcTemplate.queryForObject(getCalculationMethodByCommunalUtilityId, new CalculationMethodMapper(), id));
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_CALCULATION_METHOD_BY_ID, e.getCause());
        }
    }

    @Override
    public void updateCalculationMethod(CalculationMethod calculationMethod) {
        try {
            jdbcTemplate.update(updateCalculationMethod,
                    calculationMethod.getName(),
                    calculationMethod.getCalculationMethodId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_CALCULATION_METHOD, e.getCause());
        }
    }

    @Override
    public void createCalculationMethod(CalculationMethod calculationMethod) {
        try {
            jdbcTemplate.update(createCalculationMethodObject);
            jdbcTemplate.update(createCalculationMethodAttributes, calculationMethod.getName());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_CREATE_CALCULATION_METHOD, e.getCause());
        }
    }

    @Override
    public void deleteCalculationMethod(BigInteger id) {
        jdbcTemplate.update(deleteCalculationMethod, id);
    }
}
