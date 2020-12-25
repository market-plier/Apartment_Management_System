package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.ApartmentDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.Role;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service("ApartmentInfoService")
@Log4j
public class ApartmentInfoService {
    @Autowired
    private ApartmentSubBillService apartmentSubBillService;
    private final AccountService accountService;
    private final ApartmentDao apartmentDao;
    private final AccountDao accountDao;
    final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApartmentInfoService(AccountService accountService, ApartmentDao apartmentDao, AccountDao accountDao, BCryptPasswordEncoder passwordEncoder) {
        this.accountService = accountService;
        this.apartmentDao = apartmentDao;
        this.accountDao = accountDao;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Apartment> getAllApartments() throws DaoAccessException {
        try {
            return apartmentDao.getAllApartments();
        } catch (NullPointerException e) {
            log.error("ApartmentInfoService method getAllApartments: " + e.getMessage(), e);
            throw e;
        }
    }

    public Apartment createApartment(Apartment apartment) throws DaoAccessException {
        try {
            apartment.setAccountId(null);
            if (!isUnique(apartment)) {
                return null;
            }
            apartment.setPassword(passwordEncoder.encode(apartment.getPassword()));
            if (apartmentDao.createApartment(apartment)) {
                apartment.setAccountId(accountService.getAccountByEmail(apartment.getEmail()).getAccountId());
                apartmentSubBillService.addApartmentSubBillsToApartment(apartment);
            }
            return apartment;
        } catch (NullPointerException e) {
            log.error("ApartmentInfoService method createApartment: " + e.getMessage(), e);
            throw e;
        }
    }

    public Apartment getApartmentById(BigInteger accountId) throws DaoAccessException {
        try {
            return apartmentDao.getApartmentById(accountId);
        } catch (NullPointerException e) {
            log.error("ApartmentInfoService method getApartmentById: " + e.getMessage(), e);
            throw e;
        }
    }

    public Apartment updateApartmentPassword(Apartment apartment) throws DaoAccessException {
        try {
            Account account = accountService.getAccountByEmail(apartment.getEmail());
            if (!account.getAccountId().equals(apartment.getAccountId())) {
                throw new NotBelongToAccountException("Can not change this account password");
            }

            account.setPassword(passwordEncoder.encode(apartment.getPassword()));
            accountDao.updateAccount(account);
            return apartment;
        } catch (NullPointerException e) {
            log.error("ApartmentInfoService method updateApartmentPassword: " + e.getMessage(), e);
            throw e;
        }
    }

    public Apartment updateApartment(Apartment apartment, Account updater) throws DaoAccessException {
        try {
            Apartment old = apartmentDao.getApartmentById(apartment.getAccountId());
            Account account = accountDao.getAccount(apartment.getAccountId());

            //OWNER can only change email or password
            if (updater.getRole().equals(Role.OWNER)
                    && updater.getAccountId().equals(apartment.getAccountId())) {
                account.setEmail(apartment.getEmail());
            }
            //MANAGER change all fields
            if (updater.getRole().equals(Role.MANAGER)) {
                account.setEmail(apartment.getEmail());
                old = apartment;
                account.setFirstName(apartment.getFirstName());
                account.setLastName(apartment.getLastName());
                account.setPhoneNumber(apartment.getPhoneNumber());
            }

            if (isUnique(apartment)) {
                apartmentDao.updateApartment(old);
                accountDao.updateAccount(account);
            }
            return old;
        } catch (NullPointerException e) {
            log.error("ApartmentInfoService method updateApartment: " + e.getMessage(), e);
            throw e;
        }
    }

    private boolean isUnique(Apartment apartment) throws IllegalArgumentException {
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
        return true;
    }
}
