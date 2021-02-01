package com.netcracker.dao.impl;


import com.netcracker.dao.ManagerSpendingOperationDao;
import com.netcracker.dao.mapper.ManagerSpendingOperationMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.ManagerSpendingOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Repository
@Log4j
public class ManagerSpendingOperationDaoImpl implements ManagerSpendingOperationDao {


    private JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ManagerSpendingOperationDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
    }


    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingByManagerSubBill(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION_BY_SUBBILL_ID, new ManagerSpendingOperationMapper(), id);
        } catch (DataAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder().withId(id)
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_OPERATIONS)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationSpendingByManagerSubBill " + accessException.getMessage());
            throw accessException;
        }

    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpending() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION, new ManagerSpendingOperationMapper());
        } catch (DataAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_OPERATIONS)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationSpending " + accessException.getMessage());
            throw accessException;
        }
    }


    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingSortedByCommunalNameAndDate(String communalName, Date start, Date end) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_NAME_AND_DATE,
                    new ManagerSpendingOperationMapper(), communalName, start, end);
        } catch (DataAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_MANAGER_OPERATIONS_BY_LIST_COMM_NAME_AND_DATE)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationSpendingSortedByCommunalNameAndDate " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingSortedByCommunalName(String communalName) {
        try {
            return jdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_NAME, new ManagerSpendingOperationMapper()
                    , communalName);
        } catch (DataAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_OPERATIONS)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationSpendingSortedByCommunalName "  + accessException.getMessage(), e);
            throw accessException;
        }

    }



    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationByDate(Date start, Date end) {
        try {
            return jdbcTemplate.query(GET_MANAGER_OPERATIONS_BY_DATE, new ManagerSpendingOperationMapper()
                    , start, end);
        } catch (DataAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_ALL_MANAGER_OPERATIONS)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationByDate " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationByDateAndCommunalIdList(Date start, Date end, Set<BigInteger> communalIdList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("communalId", communalIdList);
        parameters.addValue("start", start);
        parameters.addValue("end", end);
        try {
            return namedParameterJdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_LIST_NAME_AND_DATE, parameters, new ManagerSpendingOperationMapper());
        } catch (DataAccessException e) {

            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_MANAGER_OPERATIONS_BY_LIST_COMM_NAME_AND_DATE)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationByDateAndCommunalNameList " + accessException.getMessage(), e);
            throw accessException;
        }

    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingSortedByCommunalNameList(Set<BigInteger> communalNameId) {

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("communalId", communalNameId);
        try {
            return namedParameterJdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_NAME_LIST, parameters,
                    new ManagerSpendingOperationMapper());
        } catch (DataAccessException e) {

            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_MANAGER_OPERATIONS_BY_LIST_COMM_NAME_AND_DATE)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getAllManagerOperationSpendingSortedByCommunalNameList " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public ManagerSpendingOperation getManagerOperationSpendingById(BigInteger id) throws DaoAccessException {

        try {
            return jdbcTemplate.queryForObject(GET_MANAGER_OPERATION_SPENDING_BY_ID,
                    new ManagerSpendingOperationMapper(), id);
        } catch (DataAccessException e) {

            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_GET_MANAGER_OPERATION_BY_ID)
                    .withCause(e.getCause())
                    .build();
            log.error("IN getManagerOperationSpendingById " + accessException.getMessage(), e);
            throw accessException;
        }
    }

    @Override
    public void updateManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_OPERATION, managerOperationSpending.getDescription()
                    , managerOperationSpending.getSum().toString(), managerOperationSpending.getOperationId());
        } catch (DataAccessException e) {

            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_UPDATE_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_UPDATE_MANAGER_OPERATION)
                    .withCause(e.getCause())
                    .build();
            log.error("IN updateManagerOperationSpending " + accessException.getMessage(), e);
            throw accessException;
        }

    }

    @Override
    public void createManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_MANAGER_OPERATION_SPENDING, managerOperationSpending.getDescription(),
                    managerOperationSpending.getSum().toString(), managerOperationSpending.getManagerSubBill().getSubBillId());
        } catch (DaoAccessException e) {
            DaoAccessException accessException =  new DaoAccessExceptionBuilder()
                    .withErrorMessage(ErrorCodes._FAIL_TO_INSERT_MANAGER_SPENDING_OPERATION)
                    .withMessage(EXCEPTION_INSERT_MANAGER_OPERATION)
                    .withCause(e.getCause())
                    .build();
            log.error("IN createManagerOperationSpending: " + accessException.getMessage(), e);
            throw accessException;
        }

    }
}
