package com.netcracker.dao.impl;

import com.netcracker.dao.Constants;
import com.netcracker.dao.ManagerBillDao;
import com.netcracker.dao.mapper.ManagerBillMapper;
import com.netcracker.models.ManagerBill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigInteger;

@Repository
@Slf4j
public class ManagerBillDaoImpl implements ManagerBillDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ManagerBillDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public ManagerBill getManagerBillById(BigInteger id) {
            return jdbcTemplate.queryForObject(GET_MANAGER_BILL_BY_ID,new ManagerBillMapper(),id);
    }

    @Override
    public void updateManagerBill(ManagerBill managerBill) {
            jdbcTemplate.update(UPDATE_MANAGER_BILL,managerBill.getCardNumber(),managerBill.getManagerBillId());
    }

    @Override
    public void createManagerBill(ManagerBill managerBill) {
        jdbcTemplate.update(CREATE_MANAGER_BILL, Constants.MANAGER_BILL_TYPE,  Constants.MANAGER_BILL_ATTR_CARD_NUMBER,
                             managerBill.getCardNumber(), Constants.MANAGER_BILL_ATTR_OWNER_REF, managerBill.getManager().getAccountId());

    }
}
