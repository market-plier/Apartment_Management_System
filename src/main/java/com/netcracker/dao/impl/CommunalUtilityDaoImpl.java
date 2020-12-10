package com.netcracker.dao.impl;

import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.dao.mapper.CommunalUtilityMapper;
import com.netcracker.models.CommunalUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class CommunalUtilityDaoImpl implements CommunalUtilityDao {
    @Autowired
    final JdbcTemplate jdbcTemplate;

    public CommunalUtilityDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CommunalUtility> getAllCommunalUtilities() {
        return jdbcTemplate.query(getAllCommunalUtilities, new CommunalUtilityMapper());
    }

    @Override
    public CommunalUtility getCommunalUtilityById(BigInteger id) {
        return jdbcTemplate.queryForObject(getCommunalUtilityById, new CommunalUtilityMapper(), id);

    }

    @Override
    public void updateCommunalUtility(CommunalUtility communalUtility) {
        jdbcTemplate.update(updateCommunalUtility, communalUtility.getName(),
                communalUtility.getDurationType().getDurationCode(),
                communalUtility.getStatus().getStatusCode(),
                communalUtility.getDeadline(),
                communalUtility.getCommunalUtilityId());
        jdbcTemplate.update(updateCommunalUtilityReference,
                communalUtility.getCalculationMethod().getCalculationMethodId(),
                communalUtility.getCommunalUtilityId());

    }

    @Override
    public void createCommunalUtility(CommunalUtility communalUtility) {
        jdbcTemplate.update(createCommunalUtility,
                communalUtility.getName(),
                communalUtility.getDurationType().getDurationCode(),
                communalUtility.getStatus().getStatusCode(),
                communalUtility.getDeadline(),
                communalUtility.getCalculationMethod().
                        getCalculationMethodId());
    }
}
