package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentOperationDao;
import com.netcracker.dao.mapper.ApartmentOperationMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ApartmentOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;


@Repository
@Transactional
public class ApartmentOperationDaoImpl implements ApartmentOperationDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ApartmentOperationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId) {
        try {
            return jdbcTemplate.query(selectApartmentOperationsBySubBillId, new ApartmentOperationMapper(), subBillId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_SUB_BILL_ID, subBillId, e.getCause());
        }
    }

    @Override
    public List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId) {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByApartmentId, new ApartmentOperationMapper(), apartmentId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_ID, apartmentId, e.getCause());
        }
    }

    @Override
    public void createApartmentOperation(ApartmentOperation apartmentOperation) {
        try {
            jdbcTemplate.update(insertApartmentOperation,
                    apartmentOperation.getSum(),
                    apartmentOperation.getApartmentSubBill().getSubBillId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_INSERT_APARTMENT_OPERATION, e.getCause());
        }
    }

    @Override
    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentId(BigInteger apartmentId, Date from, Date to) {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByDateRangeAndApartmentId, new ApartmentOperationMapper(), apartmentId, from, to);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_ID, apartmentId, e.getCause());
        }
    }

    @Override
    public List<ApartmentOperation> getAllApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger apartmentSubBillId, Date from, Date to) {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByDateRangeAndApartmentSubBillId, new ApartmentOperationMapper(), apartmentSubBillId, from, to);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_SUB_BILL_ID, apartmentSubBillId, e.getCause());
        }
    }
}
