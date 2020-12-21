package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentSubBillDao;
import com.netcracker.dao.mapper.ApartmentSubBillMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ApartmentSubBill;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Repository
@Transactional
@Log4j
public class ApartmentSubBillDaoImpl implements ApartmentSubBillDao {

    private JdbcTemplate jdbcTemplate;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public ApartmentSubBillDaoImpl(DataSource dataSource) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ApartmentSubBill> getAllApartmentSubBills() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_APARTMENT_SUB_BILLS, new ApartmentSubBillMapper());
        } catch (
                DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public ApartmentSubBill getApartmentSubBillById(BigInteger apartmentSubBillId) throws DaoAccessException {
        ApartmentSubBill apartmentSubBill;
        try {
            apartmentSubBill = jdbcTemplate.queryForObject(
                    GET_APARTMENT_SUB_BILL_BY_ID, new ApartmentSubBillMapper(), apartmentSubBillId);
            return apartmentSubBill;
        } catch (
                DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_APARTMENT_SUB_BILL_BY_ID, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<ApartmentSubBill> getAllApartmentSubBillsByAccountId(BigInteger accountId) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID,
                    new ApartmentSubBillMapper(), accountId);
        } catch (
                DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void createApartmentSubBill(ApartmentSubBill apartmentSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(CREATE_APARTMENT_SUB_BILL_OBJ,
                    apartmentSubBill.getCommunalUtility().getCommunalUtilityId());
            jdbcTemplate.update(CREATE_APARTMENT_SUB_BILL_ATTRIBUTES,
                    apartmentSubBill.getBalance(),
                    apartmentSubBill.getDebt());
            jdbcTemplate.update(CREATE_APARTMENT_SUB_BILL_REFERENCE,
                    apartmentSubBill.getApartment().getAccountId());
        } catch (
                DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_CREATE_APARTMENT_SUB_BILL, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<ApartmentSubBill> getApartmentSubBillsByCommunalUtilityList(BigInteger accountId, Set<BigInteger> communalList) {
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("communal_list", communalList);
        parameters.addValue("account_id", accountId);

        try {
            return namedParameterJdbcTemplate.query(GET_APARTMENT_DEBT_BY_COMMUNAL_UTILS_LIST,
                    parameters, new ApartmentSubBillMapper());
        } catch (DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_GET_APARTMENT_DEBT_BY_COMMUNAL_UTILS_LIST, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void updateApartmentSubBill(ApartmentSubBill apartmentSubBill) throws DaoAccessException {
        try {
            jdbcTemplate.update(UPDATE_APARTMENT_SUB_BILL,
                    apartmentSubBill.getBalance(),
                    apartmentSubBill.getDebt(),
                    apartmentSubBill.getSubBillId());
        } catch (
                DataAccessException e) {
            e = new DaoAccessException(EXCEPTION_UPDATE_APARTMENT_SUB_BILL, e.getCause());
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }
}
