package com.netcracker.dao.impl;


import com.netcracker.dao.ManagerSpendingOperationDao;
import com.netcracker.dao.mapper.ManagerSpendingOperationMapper;
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
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingByManagerSubBill(BigInteger id) {
       return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION_BY_SUBBILL_ID, new ManagerSpendingOperationMapper(), id);
    }

    @Override
    public List<ManagerSpendingOperation> getAllManagerOperationSpending() {
        return jdbcTemplate.query(GET_ALL_MANAGER_OPERATION,new ManagerSpendingOperationMapper());
    }

    @Override
    public ManagerSpendingOperation getManagerOperationSpendingById(BigInteger id) {
        try {
            return jdbcTemplate.queryForObject(GET_MANAGER_OPERATION_SPENDING_BY_ID,
                    new ManagerSpendingOperationMapper(), id);
        }catch (DataAccessException e)
        {
            
        }
        return null;
    }

    @Override
    public void updateManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) {
        jdbcTemplate.update(UPDATE_MANAGER_OPERATION,managerOperationSpending.getDescription()
                ,managerOperationSpending.getSum().toString(),managerOperationSpending.getOperationId());
    }

    @Override
    public void createManagerOperationSpending(ManagerSpendingOperation managerOperationSpending) {
      jdbcTemplate.update(CREATE_MANAGER_OPERATION_SPENDING,managerOperationSpending.getDescription(),
              managerOperationSpending.getSum(),managerOperationSpending.getManagerSubBill().getSubBillId());
    }
}
