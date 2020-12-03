package com.netcracker.dao;

import com.netcracker.models.Apartment;

import java.math.BigInteger;
import java.util.List;

public interface ApartmentDao {
    List<Apartment> getAllApartments();

    Apartment getApartmentById(BigInteger apartmentId);

    void updateApartment(Apartment apartment);

    void createApartment(Apartment apartment);
}
