package com.netcracker.dao;


import com.netcracker.models.ApartmentOperation;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ApartmentOperationDao {
    List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId);

    List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId);

    void createApartmentOperation(ApartmentOperation apartmentOperation);

    List<ApartmentOperation> getApartmentOperationsByDateRange(Date from, Date to);

    List<ApartmentOperation> getAllApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger ApartmentSubBillId, Date from, Date to);
}
