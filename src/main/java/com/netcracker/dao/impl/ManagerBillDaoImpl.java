package com.netcracker.dao.impl;

import com.netcracker.dao.Constants;
import com.netcracker.dao.ManagerBillDao;
import com.netcracker.dao.mapper.ManagerBillMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.models.ManagerBill;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;

@Repository
@Log4j
public class ManagerBillDaoImpl implements ManagerBillDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerBillDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ManagerBill getManagerBillById(BigInteger id) throws DaoAccessException {
        try {
            return jdbcTemplate.queryForObject(GET_MANAGER_BILL_BY_ID, new ManagerBillMapper(), id);
        } catch (DataAccessException e) {
            log.error("IN getManagerBillById: "+ EXCEPTION_GET_MANAGER_BILL_BY_ID);
            throw new DaoAccessException(EXCEPTION_GET_MANAGER_BILL_BY_ID, id, ErrorCodes._FAIL_TO_SELECT_MANAGER_BILL);
        }

    }

    @Override
    public void updateManagerBill(ManagerBill managerBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_BILL, managerBill.getCardNumber(), managerBill.getManagerBillId());
        } catch (DataAccessException e) {
            log.error("IN updateManagerBill: " + EXCEPTION_UPDATE_MANAGER_BILL);
            throw new DaoAccessException(EXCEPTION_UPDATE_MANAGER_BILL, ErrorCodes._FAIL_TO_UPDATE_MANAGER_BILL);
        }

    }

    @Override
    public void createManagerBill(ManagerBill managerBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_MANAGER_BILL, Constants.MANAGER_BILL_TYPE, Constants.MANAGER_BILL_ATTR_CARD_NUMBER,
                    managerBill.getCardNumber(), Constants.MANAGER_BILL_ATTR_OWNER_REF, managerBill.getManager().getAccountId());
        } catch (DataAccessException e) {
            log.error("IN createManagerBill: "+ EXCEPTION_CREATE_MANAGER_BILL);
            throw new DaoAccessException(EXCEPTION_CREATE_MANAGER_BILL, ErrorCodes._FAIL_TO_INSERT_MANAGER_BILL);
        }


    }
}
