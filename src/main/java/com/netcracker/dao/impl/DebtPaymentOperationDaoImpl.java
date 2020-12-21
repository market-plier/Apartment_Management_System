package com.netcracker.dao.impl;

import com.netcracker.dao.DebtPaymentOperationDao;
import com.netcracker.dao.mapper.DebtPaymentOperationMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.DebtPaymentOperation;
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
public class DebtPaymentOperationDaoImpl implements DebtPaymentOperationDao {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public DebtPaymentOperationDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentId(BigInteger apartmentId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByApartmentId, new DebtPaymentOperationMapper(), apartmentId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_APARTMENT_ID, apartmentId, e);
        }
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentSubBillId(BigInteger apartmentSubBillId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByApartmentSubBillID, new DebtPaymentOperationMapper(), apartmentSubBillId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_APARTMENT_SUB_BILL_ID, apartmentSubBillId, e);
        }
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByManagerSubBillId(BigInteger managerSubBilId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByManagerSubBillId, new DebtPaymentOperationMapper(), managerSubBilId);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_MANAGER_SUB_BILL_ID, managerSubBilId, e);
        }
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentId(Date from, Date to, BigInteger apartmentId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByDateRangeAndApartmentId, new DebtPaymentOperationMapper(), apartmentId, from, to);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_APARTMENT_ID, apartmentId, e);
        }
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(Date from, Date to, BigInteger apartmentSubBillId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByDateRangeAndApartmentSubBillId, new DebtPaymentOperationMapper(), apartmentSubBillId, from, to);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_APARTMENT_SUB_BILL_ID, apartmentSubBillId, e);
        }
    }

    @Override
    public List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndManagerSubBillId(Date from, Date to, BigInteger managerSubBillId) {
        try {
            return jdbcTemplate.query(selectDebtPaymentOperationByDateRangeAndManagerSubBillId, new DebtPaymentOperationMapper(), managerSubBillId, from, to);
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_MANAGER_SUB_BILL_ID, managerSubBillId, e);
        }
    }


    @Override
    public void createDebtPaymentOperation(DebtPaymentOperation debtPaymentOperation) {
        try {
            jdbcTemplate.update(insertDebtPaymentOperation,
                    debtPaymentOperation.getSum(),
                    debtPaymentOperation.getApartmentSubBill().getSubBillId(),
                    debtPaymentOperation.getManagerSubBill().getSubBillId());
        } catch (DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_INSERT_DEBT_PAYMENT_OPERATION, e);
        }
    }
}
