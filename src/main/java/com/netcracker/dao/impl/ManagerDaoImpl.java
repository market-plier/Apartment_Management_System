package com.netcracker.dao.impl;

import com.netcracker.dao.ManagerDao;
import com.netcracker.dao.mapper.ManagerMapper;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.models.Manager;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;

@Log4j
@Repository
@Transactional
public class ManagerDaoImpl implements ManagerDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Manager getManager() {
        try {
            Manager manager = jdbcTemplate.queryForObject(GET_MANAGER, new ManagerMapper());
            return manager;
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_MANAGER)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(83))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateManager(Manager manager) {
        try {
            jdbcTemplate.update(UPDATE_MANAGER,
                    manager.getManagerBill(),
                    manager.getAccountId());
        } catch (DataAccessException e) {
            e = new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_UPDATE_MANAGER)
                    .withCause(e.getCause())
                    .withErrorMessage(BigInteger.valueOf(82))
                    .build();
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

}
