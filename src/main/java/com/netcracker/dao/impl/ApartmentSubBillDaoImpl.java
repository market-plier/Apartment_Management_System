package com.netcracker.dao.impl;

import com.netcracker.dao.ApartmentSubBillDao;
import com.netcracker.dao.mapper.ApartmentOperationMapper;
import com.netcracker.dao.mapper.ApartmentSubBillMapper;
import com.netcracker.dao.mapper.DebtPaymentOperationMapper;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ApartmentOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.util.List;

import static com.netcracker.dao.ApartmentOperationDao.selectApartmentOperationsBySubBillId;
import static com.netcracker.dao.DebtPaymentOperationDao.getDebtPaymentOperationBySubBillId;

@Repository
@Transactional
public class ApartmentSubBillDaoImpl implements ApartmentSubBillDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ApartmentSubBillDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<ApartmentSubBill> getAllApartmentSubBills() throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_APARTMENT_SUB_BILLS, new ApartmentSubBillMapper());
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS, e.getCause());
        }
    }

    @Override
    public ApartmentSubBill getApartmentSubBillById(BigInteger apartmentSubBillId) throws DaoAccessException {
        ApartmentSubBill apartmentSubBill;
        try {
            apartmentSubBill = jdbcTemplate.queryForObject(
                    GET_APARTMENT_SUB_BILL_BY_ID, new ApartmentSubBillMapper(), apartmentSubBillId);
            // apartmentSubBill=withOperations(apartmentSubBill);
            return apartmentSubBill;
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_APARTMENT_SUB_BILL_BY_ID, e.getCause());
        }
    }

    @Override
    public List<ApartmentSubBill> getAllApartmentSubBillsByAccountId(BigInteger accountId) throws DaoAccessException {
        try {
            return jdbcTemplate.query(GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID,
                    new ApartmentSubBillMapper(), accountId);
        } catch (
                DataAccessException e) {
            throw new DaoAccessException(EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID, e.getCause());
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
            throw new DaoAccessException(EXCEPTION_CREATE_APARTMENT_SUB_BILL, e.getCause());
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
            throw new DaoAccessException(EXCEPTION_UPDATE_APARTMENT_SUB_BILL, e.getCause());
        }
    }

    /*private ApartmentSubBill withOperations(ApartmentSubBill apartmentSubBill) {
        List<ApartmentOperation> list_apt = jdbcTemplate.query(selectApartmentOperationsBySubBillId,
                new ApartmentOperationMapper(),
                apartmentSubBill.getSubBillId()
        );
        List<DebtPaymentOperation> list_dept = jdbcTemplate.query(getDebtPaymentOperationBySubBillId,
                new DebtPaymentOperationMapper(),
                apartmentSubBill.getSubBillId()
        );
        apartmentSubBill.setApartmentOperation(list_apt);
        apartmentSubBill.setDebtPaymentOperation(list_dept);
        return apartmentSubBill;
    }*/

}
