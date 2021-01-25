package com.netcracker.services;

import com.netcracker.dao.ApartmentOperationDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ApartmentOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ApartmentOperationService {
    private final ApartmentOperationDao apartmentOperationDao;

    @Autowired
    public ApartmentOperationService(ApartmentOperationDao apartmentOperationDao) {
        this.apartmentOperationDao = apartmentOperationDao;
    }

    public void createApartmentOperation(ApartmentOperation apartmentOperation) throws DaoAccessException {
        apartmentOperationDao.createApartmentOperation(apartmentOperation);
    }

    public List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId) throws DaoAccessException {
        return apartmentOperationDao.getAllApartmentOperationsBySubBillId(subBillId);
    }

    public List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId) throws DaoAccessException {
        return apartmentOperationDao.getAllApartmentOperationsByApartmentId(apartmentId);
    }

    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentNumber(Integer apartmentNumber, Date from, Date to) throws DaoAccessException {
        return apartmentOperationDao.getApartmentOperationsByDateRangeAndApartmentNumber(apartmentNumber, from, to);
    }


    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger apartmentSubBillId, Date from, Date to) throws DaoAccessException {
        return apartmentOperationDao.getApartmentOperationsByDateRangeAndApartmentSubBillId(apartmentSubBillId, from, to);
    }

}
