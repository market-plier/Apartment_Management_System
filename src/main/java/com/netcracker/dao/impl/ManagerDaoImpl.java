package com.netcracker.dao.impl;

import com.netcracker.dao.ManagerDao;
import com.netcracker.dao.mapper.ManagerMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

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
            throw new DaoAccessException(EXCEPTION_GET_MANAGER, e.getCause());
        }
    }

    @Override
    public void updateManager(Manager manager) {
            try {
        jdbcTemplate.update(UPDATE_MANAGER,
                manager.getManagerBill(),
                manager.getAccountId());
            } catch (DataAccessException e) {
                throw new DaoAccessException(EXCEPTION_UPDATE_MANAGER, e.getCause());
            }
    }
}
