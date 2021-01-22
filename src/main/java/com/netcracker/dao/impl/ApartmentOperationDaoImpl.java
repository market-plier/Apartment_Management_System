package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentOperationDao;
import com.netcracker.dao.mapper.ApartmentOperationMapper;
import com.netcracker.dao.mapper.ApartmentOperationWithCommUtilMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.models.ApartmentOperation;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class ApartmentOperationDaoImpl implements ApartmentOperationDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public ApartmentOperationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId) throws DaoAccessException {
        try {
            return jdbcTemplate.query(selectApartmentOperationsBySubBillId, new ApartmentOperationMapper(), subBillId);
        } catch (DataAccessException e) {
            log.error("getAllApartmentOperationsBySubBillId select error with id: " + subBillId, e);
            throw new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_SUB_BILL_ID)
                    .withCause(e)
                    .withErrorMessage(new BigInteger("173"))
                    .withId(subBillId)
                    .build();
        }
    }

    @Override
    public List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId) throws DaoAccessException {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByApartmentId, new ApartmentOperationWithCommUtilMapper(), apartmentId);
        } catch (DataAccessException e) {
            log.error("getAllApartmentOperationsByApartmentId select error with id: " + apartmentId, e);
            throw new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_ID)
                    .withCause(e)
                    .withErrorMessage(new BigInteger("173"))
                    .withId(apartmentId)
                    .build();
        }
    }

    @Override
    public void createApartmentOperation(ApartmentOperation apartmentOperation) throws DaoAccessException {
        try {
            jdbcTemplate.update(insertApartmentOperation,
                    apartmentOperation.getSum(),
                    apartmentOperation.getApartmentSubBill().getSubBillId());
        } catch (DataAccessException e) {
            log.error("createApartmentOperation insert error", e);
            throw new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_INSERT_APARTMENT_OPERATION)
                    .withCause(e)
                    .withErrorMessage(new BigInteger("171"))
                    .build();
        }
    }

    @Override
    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentId(BigInteger apartmentId, Date from, Date to) throws DaoAccessException {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByDateRangeAndApartmentId, new ApartmentOperationMapper(), apartmentId, from, to);
        } catch (DataAccessException e) {
            log.error("getApartmentOperationsByDateRangeAndApartmentId select error with id: " + apartmentId + "and dates " + from + ", " + to, e);
            throw new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_ID)
                    .withCause(e)
                    .withErrorMessage(new BigInteger("173"))
                    .withId(apartmentId)
                    .build();
        }
    }

    @Override
    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger apartmentSubBillId, Date from, Date to) throws DaoAccessException {
        try {
            return jdbcTemplate.query(selectApartmentOperationsByDateRangeAndApartmentSubBillId, new ApartmentOperationMapper(), apartmentSubBillId, from, to);
        } catch (DataAccessException e) {
            log.error("getApartmentOperationsByDateRangeAndApartmentSubBillId select error with id: " + apartmentSubBillId + "and dates " + from + ", " + to, e);
            throw new DaoAccessExceptionBuilder()
                    .withMessage(EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_SUB_BILL_ID)
                    .withCause(e)
                    .withErrorMessage(new BigInteger("173"))
                    .withId(apartmentSubBillId)
                    .build();
        }
    }
}
