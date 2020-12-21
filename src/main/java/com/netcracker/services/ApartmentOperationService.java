package com.netcracker.services;

import com.netcracker.dao.ApartmentOperationDao;
import com.netcracker.models.ApartmentOperation;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class ApartmentOperationService {
    private final ApartmentOperationDao apartmentOperationDao;

    public ApartmentOperationService(ApartmentOperationDao apartmentOperationDao) {
        this.apartmentOperationDao = apartmentOperationDao;
    }

    public void createApartmentOperation(ApartmentOperation apartmentOperation) {
        apartmentOperationDao.createApartmentOperation(apartmentOperation);
    }

    public List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId) {
        return apartmentOperationDao.getAllApartmentOperationsBySubBillId(subBillId);
    }

    public List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId){
        return apartmentOperationDao.getAllApartmentOperationsByApartmentId(apartmentId);
    }

    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentId(BigInteger apartmentId, Date from, Date to){
        return apartmentOperationDao.getApartmentOperationsByDateRangeAndApartmentId(apartmentId, from, to);
    }

    public List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger apartmentSubBillId, Date from, Date to){
        return apartmentOperationDao.getApartmentOperationsByDateRangeAndApartmentSubBillId(apartmentSubBillId, from, to);
    }
}
