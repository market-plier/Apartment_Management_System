package com.netcracker.dao.impl;

import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.dao.mapper.CommunalUtilityMapper;
import com.netcracker.dao.mapper.CommunalUtilityWithCalculationMethodMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CommunalUtility;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class CommunalUtilityDaoImpl implements CommunalUtilityDao {
    final JdbcTemplate jdbcTemplate;

    public CommunalUtilityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilities() {
        try {
            return jdbcTemplate.query(getAllCommunalUtilities, new CommunalUtilityMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_COMMUNAL_UTILITIES, e.getCause());
        }
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilitiesWithCalculationMethod() {
        try {
            return jdbcTemplate.query(getAllCommunalUtilitiesWithCalculationMethod, new CommunalUtilityWithCalculationMethodMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_COMMUNAL_UTILITIES, e.getCause());
        }
    }

    @Override
    public CommunalUtility getCommunalUtilityById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(getCommunalUtilityById, new CommunalUtilityMapper(), id);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID, e.getCause());
        }

    }

    @Override
    public CommunalUtility getCommunalUtilityByIdWithCalculationMethod(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(getCommunalUtilityWithCalculationMethodById, new CommunalUtilityWithCalculationMethodMapper(), id);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_COMMUNAL_UTILITY_BY_ID, e.getCause());
        }
    }

    @Override
    public void updateCommunalUtility(CommunalUtility communalUtility) {
        try {
            jdbcTemplate.update(updateCommunalUtility, communalUtility.getName(),
                    communalUtility.getDurationType().getDurationCode(),
                    communalUtility.getStatus().getStatusCode(),
                    communalUtility.getDeadline(),
                    communalUtility.getCommunalUtilityId());
            jdbcTemplate.update(updateCommunalUtilityReference,
                    communalUtility.getCalculationMethod().getCalculationMethodId(),
                    communalUtility.getCommunalUtilityId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_COMMUNAL_UTILITY, e.getCause());
        }
    }

    @Override
    public void createCommunalUtility(CommunalUtility communalUtility) {
        try {
            jdbcTemplate.update(createCommunalUtility,
                    communalUtility.getCalculationMethod().
                            getCalculationMethodId(),
                    communalUtility.getName(),
                    communalUtility.getDurationType().getDurationCode(),
                    communalUtility.getStatus().getStatusCode(),
                    communalUtility.getDeadline(),
                    communalUtility.getCalculationMethod().getCalculationMethodId()
            );
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_CREATE_COMMUNAL_UTILITIES, e.getCause());
        }
    }
}
