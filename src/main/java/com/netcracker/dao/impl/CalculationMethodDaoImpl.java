package com.netcracker.dao.impl;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.mapper.CalculationMethodMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.CalculationMethod;
import lombok.extern.log4j.Log4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Log4j
@Repository
@Transactional
@Deprecated
public class CalculationMethodDaoImpl implements CalculationMethodDao {
    final
    JdbcTemplate jdbcTemplate;

    public CalculationMethodDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CalculationMethod> getAllCalculationMethods() throws DaoAccessException {
        try {
            return jdbcTemplate.query(getAllCalculationMethods, new CalculationMethodMapper());
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_CALCULATION_METHOD)
                    .withMessage(EXCEPTION_GET_ALL_CALCULATION_METHODS)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public CalculationMethod getCalculationMethodById(BigInteger id) throws DaoAccessException {
        try {
            return (jdbcTemplate.queryForObject(getCalculationMethodById,
                    new CalculationMethodMapper(),
                    id));
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_CALCULATION_METHOD)
                    .withMessage(EXCEPTION_GET_CALCULATION_METHOD_BY_ID)
                    .withId(id)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public CalculationMethod getCalculationMethodByCommunalUtilityId(BigInteger id) throws DaoAccessException {
        try {
            return (jdbcTemplate.queryForObject(getCalculationMethodByCommunalUtilityId,
                    new CalculationMethodMapper(),
                    id));
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_CALCULATION_METHOD_BY_COMMUNAL_UTILITY_ID)
                    .withCause(e.getCause())
                    .withId(id)
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_CALCULATION_METHOD)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void updateCalculationMethod(CalculationMethod calculationMethod) throws DaoAccessException {
        try {
            jdbcTemplate.update(updateCalculationMethod,
                    calculationMethod.getName(),
                    calculationMethod.getCoefficient(),
                    calculationMethod.getCalculationMethodId());
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_UPDATE_CALCULATION_METHOD)
                    .withMessage(EXCEPTION_UPDATE_CALCULATION_METHOD)
                    .withId(calculationMethod.getCalculationMethodId())
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void createCalculationMethod(CalculationMethod calculationMethod) throws DaoAccessException {
        try {
            jdbcTemplate.update(createCalculationMethodAttributes,
                    calculationMethod.getName(),
                    calculationMethod.getCoefficient());
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_CALCULATION_METHOD)
                    .withMessage(EXCEPTION_CREATE_CALCULATION_METHOD)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void deleteCalculationMethod(BigInteger id) throws DaoAccessException {
        try {
            jdbcTemplate.update(deleteCalculationMethod, id);
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_DELETE_CALCULATION_METHOD)
                    .withMessage(EXCEPTION_DELETE_CALCULATION_METHODS_)
                    .withId(id)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }
}
