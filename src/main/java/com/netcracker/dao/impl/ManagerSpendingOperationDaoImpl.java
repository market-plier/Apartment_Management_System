package com.netcracker.dao.impl;


import com.netcracker.dao.ManagerSpendingOperationDao;
import com.netcracker.dao.mapper.ManagerSpendingOperationMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSpendingOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

@Repository
public class ManagerSpendingOperationDaoImpl implements ManagerSpendingOperationDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerSpendingOperationDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingByManagerSubBill(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION_BY_SUBBILL_ID, new ManagerSpendingOperationMapper(), id);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_MANAGER_OPERATIONS_BY_SUB_BILL_ID, id, e.getCause());
        }

    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpending() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION, new ManagerSpendingOperationMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_MANAGER_OPERATIONS, e.getCause());
        }
    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingSortedByCommunalNameAndDate(String communalName) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_NAME_AND_DATE,
                    new ManagerSpendingOperationMapper(), communalName);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_MANAGER_OPERATIONS, e.getCause());
        }
    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingSortedByCommunalName(String communalName) {
        try {
            return jdbcTemplate.query(GET_MANAGER_OPERATION_BY_COMMUNAL_NAME, new ManagerSpendingOperationMapper()
                    ,communalName);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_MANAGER_OPERATIONS, e.getCause());
        }

    }

    @Override
    public ManagerSpendingOperation getManagerOperationSpendingById(BigInteger id) throws DaoAccessException {

        try {
            return jdbcTemplate.queryForObject(GET_MANAGER_OPERATION_SPENDING_BY_ID,
                    new ManagerSpendingOperationMapper(), id);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_MANAGER_OPERATION_BY_ID, id, e.getCause());
        }
    }

    @Override
    public void updateManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_OPERATION, managerOperationSpending.getDescription()
                    , managerOperationSpending.getSum().toString(), managerOperationSpending.getOperationId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_MANAGER_OPERATION, e.getCause());
        }

    }

    @Override
    public void createManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_MANAGER_OPERATION_SPENDING, managerOperationSpending.getDescription(),
                    managerOperationSpending.getSum(), managerOperationSpending.getManagerSubBill().getSubBillId());
        } catch (DaoAccessException e) {
            throw new DaoAccessException(EXCEPTION_INSERT_MANAGER_OPERATION, e.getCause());
        }

    }
}
