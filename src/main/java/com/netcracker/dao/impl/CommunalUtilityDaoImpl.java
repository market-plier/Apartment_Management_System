package com.netcracker.dao.impl;

import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.dao.mapper.CommunalUtilityMapper;
import com.netcracker.dao.mapper.CommunalUtilityWithCalculationMethodMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.CommunalUtility;
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
public class CommunalUtilityDaoImpl implements CommunalUtilityDao {
    final JdbcTemplate jdbcTemplate;

    public CommunalUtilityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilities() throws DaoAccessException {
        try {
            return jdbcTemplate.query(getAllCommunalUtilities, new CommunalUtilityMapper());
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_ALL_COMMUNAL_UTILITIES)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilitiesFilterByStatus(CommunalUtility.Status status) throws DaoAccessException {
        try {
            return jdbcTemplate.query(getAllCommunalUtilitiesFilterByStatus, new CommunalUtilityMapper(), status.getStatusCode());
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_ALL_COMMUNAL_UTILITIES_FILTER_BY_STATUS + status)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilitiesByCalculationMethodId(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.query(getAllCommunalUtilitiesByCalculationMethodId,
                    new CommunalUtilityMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_ALL_COMMUNAL_UTILITIES)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public CommunalUtility getCommunalUtilityById(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(getCommunalUtilityById, new CommunalUtilityMapper(), id);
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID)
                    .withId(id)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public CommunalUtility getCommunalUtilityByIdWithCalculationMethod(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(getCommunalUtilityWithCalculationMethodById, new CommunalUtilityWithCalculationMethodMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID)
                    .withId(id)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void updateCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            jdbcTemplate.update(updateCommunalUtility,
                    communalUtility.getName(),
                    communalUtility.getDurationType().getDurationCode(),
                    communalUtility.getStatus().getStatusCode(),
                    communalUtility.getDeadline(),
                    communalUtility.getCommunalUtilityId());
            if (communalUtility.getCalculationMethod() != null) {
                jdbcTemplate.update(updateCommunalUtilityReference,
                        communalUtility.getCalculationMethod().getCalculationMethodId(),
                        communalUtility.getCommunalUtilityId());
            }
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_UPDATE_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_UPDATE_COMMUNAL_UTILITY)
                    .withId(communalUtility.getCommunalUtilityId())
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void createCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            jdbcTemplate.update(createCommunalUtility,
                    communalUtility.getCalculationMethod().
                            getCalculationMethodId(),
                    communalUtility.getName(),
                    communalUtility.getDurationType().getDurationCode(),
                    communalUtility.getStatus().getStatusCode(),
                    communalUtility.getDeadline()
            );
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_CREATE_COMMUNAL_UTILITIES)
                    .withId(communalUtility.getCommunalUtilityId())
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public CommunalUtility getUniqueCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(getCommunalUtilityUnique, new CommunalUtilityMapper(),
                    communalUtility.getName());
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_GET_UNIQUE_COMMUNAL_UTILITY)
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }

    @Override
    public void createCommunalUtilityWithRef(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            jdbcTemplate.update(createCommunalUtilityWithRef,
                    communalUtility.getCalculationMethod().
                            getCalculationMethodId(),
                    communalUtility.getName(),
                    communalUtility.getDurationType().getDurationCode(),
                    communalUtility.getStatus().getStatusCode(),
                    communalUtility.getDeadline(),
                    communalUtility.getCalculationMethod().getCalculationMethodId()
            );
        } catch (DataAccessException e) {
            DaoAccessException exception = new DaoAccessExceptionBuilder()
                    .withCause(e.getCause())
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_COMMUNAL_UTILITY)
                    .withMessage(EXCEPTION_CREATE_COMMUNAL_UTILITIES)
                    .withId(communalUtility.getCommunalUtilityId())
                    .build();
            log.error(e.getMessage(), exception);
            throw exception;
        }
    }
}
