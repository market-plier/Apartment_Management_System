package com.netcracker.services;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Apartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service("ApartmentInfoService")
public class ApartmentInfoService {

    @Autowired
    private ApartmentDao apartmentDao;

    public List<Apartment> getAllApartments() throws DaoAccessException {
        return apartmentDao.getAllApartments();
    }

    public Apartment createApartment(Apartment apartment) throws DaoAccessException {
        List<Apartment> apartments = apartmentDao.getUniqueApartment(apartment);
        for (Apartment a : apartments) {
            if (a.getEmail().equals(apartment.getEmail())) {
                throw new DataIntegrityViolationException("This email is already in use");
            }
            if (a.getFloor().equals(apartment.getFloor())) {
                throw new DataIntegrityViolationException("This apartment already has an account");
            }
        }
        apartmentDao.createApartment(apartment);
        return apartment;
    }

    public Apartment getApartmentById(BigInteger accountId) throws DaoAccessException {
        return apartmentDao.getApartmentById(accountId);
    }

    public Apartment updateApartment(Apartment apartment) throws DaoAccessException {
        apartmentDao.updateApartment(apartment);
        return apartment;
    }
}
