package com.netcracker.dao;

import com.netcracker.models.ApartmentSubBill;

import java.math.BigInteger;
import java.util.List;

public interface ApartmentSubBillDao {
    List<ApartmentSubBill> getAllApartmentSubBills();

    ApartmentSubBill getApartmentSubBillById(BigInteger apartmentSubBillId);

    void updateApartmentSubBill(ApartmentSubBill apartmentSubBill);

    void createApartmentSubBill(ApartmentSubBill apartmentSubBill);

}
