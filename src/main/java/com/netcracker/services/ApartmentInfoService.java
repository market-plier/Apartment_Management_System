package com.netcracker.services;

import com.netcracker.dao.ApartmentDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.Role;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service("ApartmentInfoService")
@Log4j
public class ApartmentInfoService {

    private final ApartmentDao apartmentDao;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApartmentInfoService(ApartmentDao apartmentDao, BCryptPasswordEncoder passwordEncoder) {
        this.apartmentDao = apartmentDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Apartment> getAllApartments() throws DaoAccessException {
        try {
            return apartmentDao.getAllApartments();
        } catch (DaoAccessException e) {
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    public Apartment createApartment(Apartment apartment) throws DaoAccessException {
        try {
            if (isUnique(apartment)) {
                apartment.setPassword(passwordEncoder.encode(apartment.getPassword()));
                apartmentDao.createApartment(apartment);
                return apartment;
            }
        } catch (DaoAccessException e) {
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }

        return null;
    }

    public Apartment getApartmentById(BigInteger accountId) throws DaoAccessException {
        try {
            return apartmentDao.getApartmentById(accountId);
        } catch (DaoAccessException e) {
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
    }

    public Apartment updateApartment(Apartment apartment, Account updater) throws DaoAccessException {

        try {
            Apartment old = apartmentDao.getApartmentById(apartment.getAccountId());

            //OWNER can only change email or password
            if (updater.getRole().equals(Role.OWNER)
                    && updater.getAccountId().equals(apartment.getAccountId())) {
                old.setEmail(apartment.getEmail());
                if (!old.getPassword().equals(apartment.getPassword())) {
                    old.setPassword(passwordEncoder.encode(apartment.getPassword()));
                }
            }

            //MANAGER change all fields
            if (updater.getRole().equals(Role.MANAGER)) {
                if (!old.getPassword().equals(apartment.getPassword())) {
                    apartment.setPassword(passwordEncoder.encode(apartment.getPassword()));
                }
                old = apartment;
            }

            if (isUnique(old)) {
                apartmentDao.updateApartment(old);
            }
        } catch (DaoAccessException e) {
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        return null;
    }

    private boolean isUnique(Apartment apartment) throws IllegalArgumentException {
        try {
            List<Apartment> apartments = apartmentDao.getUniqueApartment(apartment);
            for (Apartment a : apartments) {
                if (apartment.getAccountId() == null
                        || !apartment.getAccountId().equals(a.getAccountId())) {

                    if (a.getEmail().equals(apartment.getEmail())) {
                        throw new IllegalArgumentException("This email is already in use");
                    }
                    if (a.getFloor().equals(apartment.getFloor())) {
                        throw new IllegalArgumentException("This apartment already has an account");
                    }

                }
            }
        } catch (DaoAccessException e) {
            log.log(Level.ERROR, e.getMessage(), e);
            throw e;
        }
        return true;
    }

}
