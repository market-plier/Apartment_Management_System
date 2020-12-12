package com.netcracker.dao.impl;

import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.dao.mapper.ManagerSubBillMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSubBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.Collection;

@Repository
@Transactional
public class ManagerSubBillDaoImpl implements ManagerSubBillDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Collection<ManagerSubBill> getAllManagerSubBills() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_MANAGER_SUB_BILL, new ManagerSubBillMapper());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_MANAGER_SUB_BILL, e.getCause());
        }
    }

    @Override
    public ManagerSubBill getManagerSubBillById(BigInteger id) throws DaoAccessException {
        try {
            ManagerSubBill managerSubBill = jdbcTemplate.queryForObject(GET_MANAGER_SUB_BILL_BY_ID, new ManagerSubBillMapper(), id);
            return managerSubBill;
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_MANAGER_SUB_BILL_BY_ID, e.getCause());
        }
    }

    @Override
    public void updateManagerSubBill(ManagerSubBill managerSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_MANAGER_SUB_BILL,
                    managerSubBill.getBalance(),
                    managerSubBill.getSubBillId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_UPDATE_MANAGER_SUB_BILL, e.getCause());
        }
    }

    @Override
    public void createManagerSubBill(ManagerSubBill managerSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_OBJECTS,
                    managerSubBill.getCommunalUtility().getCommunalUtilityId());
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_ATTRIBUTES,
                    managerSubBill.getBalance());
            jdbcTemplate.update(CREATE_MANAGER_SUB_BILL_REFERENCE,
                    managerSubBill.getManager().getAccountId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_INSERT_MANAGER_SUB_BILL, e.getCause());
        }
    }
}
