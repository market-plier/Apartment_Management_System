package com.netcracker.dao.impl;

import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.dao.mapper.ManagerSubBillMapper;
import com.netcracker.dao.mapper.ManagerSubBillNoManagerMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.models.CommunalUtility;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.models.PojoBuilder.CommunalUtilityBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;

@Log4j
@Repository
@Transactional
public class ManagerSubBillDaoImpl implements ManagerSubBillDao {

    private final JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ManagerSubBillDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Collection<ManagerSubBill> getAllManagerSubBills() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_SUB_BILL, new ManagerSubBillMapper());
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_SUB_BILL)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(143))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Collection<ManagerSubBill> getAllManagerSubBillsWithOutManager() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_SUB_BILL_NO_MANAGER, new ManagerSubBillNoManagerMapper());
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_SUB_BILL)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(143))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ManagerSubBill getManagerSubBillById(BigInteger managerSubBillId) throws DaoAccessException {
        ManagerSubBill managerSubBill;
        try {
            managerSubBill = jdbcTemplate.queryForObject(
                    GET_MANAGER_SUB_BILL_BY_ID, new ManagerSubBillMapper(), managerSubBillId);
            return managerSubBill;
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_MANAGER_SUB_BILL_BY_ID)
                    .withId(managerSubBillId)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(143))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public Map<ManagerSubBill, Double> getManagerSubBillsDebt() {
        try {
            Map<ManagerSubBill, Double> managerSubBillDoubleMap = namedParameterJdbcTemplate.query(GET_GROUPED_MANAGER_SUB_BILL_WITH_DEBT, rs -> {
                Map<ManagerSubBill, Double> managerSubBillMap = new HashMap<>();
                while (rs.next()) {
                    ManagerSubBill managerSubBill = new ManagerSubBillBuilder()
                            .withCommunalUtility(new CommunalUtilityBuilder()
                                    .withName(rs.getString("communal_name"))
                                    .withStatus(CommunalUtility.Status.valueOf(rs.getString("status")))
                                    .withDurationType(CommunalUtility.Duration.valueOf(rs.getString("duration_type")))
                                    .withDeadline(rs.getDate("dead_line"))
                                    .build()
                            )
                            .build();
                    managerSubBillMap.put(managerSubBill, rs.getDouble("debt"));
                }
                return managerSubBillMap;
            });
            return managerSubBillDoubleMap;
        } catch (DataAccessException e) {
            DaoAccessException daoAccessException = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_MANAGER_SUB_BILLS_BY_COMMUNAL_UTILS_LIST)
                    .withErrorMessage(BigInteger.valueOf(143))
                    .buildWithOutId();
            log.log(Level.ERROR, e.getMessage(), e);
            throw daoAccessException;
        }
    }


    @Override
    public ManagerSubBill getManagerSubBillByCommunalUtilityId(BigInteger id) throws DaoAccessException {
        try {
            ManagerSubBill managerSubBill = jdbcTemplate.queryForObject(GET_MANAGER_SUB_BILL_BY_COMMUNAL_UTILL_ID, new ManagerSubBillMapper(), id);
            return managerSubBill;
        } catch (DataAccessException e) {
            log.error("IN getManagerSubBillByCommunalUtilityId: " + EXCEPTION_GET_MANAGER_SUB_BILL_BY_COMMUNAL_UTILL_ID);
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_MANAGER_SUB_BILL_BY_COMMUNAL_UTILL_ID)
                    .withId(id)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(143))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }


    @Override
    public Map<ManagerSubBill, Double> getManagerSubBillDeptByCommunalUtility(Set<BigInteger> communalUtilityId) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("communal_list", communalUtilityId);

        try {
            Map<ManagerSubBill, Double> managerSubBillDoubleMap = namedParameterJdbcTemplate.query(GET_GROUPED_MANAGER_SUB_BILL_BY_COMM_UTILL, parameters, rs -> {
                Map<ManagerSubBill, Double> managerSubBillMap = new HashMap<>();
                while (rs.next()) {
                    ManagerSubBill managerSubBill = new ManagerSubBillBuilder()
                            .withCommunalUtility(new CommunalUtilityBuilder()
                                    .withName(rs.getString("communal_name"))
                                    .withStatus(CommunalUtility.Status.valueOf(rs.getString("status")))
                                    .withDurationType(CommunalUtility.Duration.valueOf(rs.getString("duration_type")))
                                    .withDeadline(rs.getDate("dead_line"))
                                    .build())
                            .build();
                    managerSubBillMap.put(managerSubBill, rs.getDouble("debt"));
                }
                return managerSubBillMap;
            });
            return managerSubBillDoubleMap;
        } catch (DataAccessException e) {
            DaoAccessException daoAccessException = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_MANAGER_SUB_BILLS_BY_COMMUNAL_UTILS_LIST)
                    .withErrorMessage(BigInteger.valueOf(143))
                    .buildWithOutId();
            log.log(Level.ERROR, e.getMessage(), e);
            throw daoAccessException;
        }
    }




    @Override
    public void updateManagerSubBill(ManagerSubBill managerSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_SUB_BILL,
                    managerSubBill.getBalance(),
                    managerSubBill.getSubBillId());
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_UPDATE_MANAGER_SUB_BILL)
                    .withId(managerSubBill.getSubBillId())
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(142))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void createManagerSubBill(ManagerSubBill managerSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_OBJECTS,
                    managerSubBill.getCommunalUtility().getCommunalUtilityId());
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_ATTRIBUTES,
                    0);
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_REFERENCE,
                    managerSubBill.getManager().getAccountId());
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_INSERT_MANAGER_SUB_BILL)
                    .withId(managerSubBill.getSubBillId())
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(141))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }
}
