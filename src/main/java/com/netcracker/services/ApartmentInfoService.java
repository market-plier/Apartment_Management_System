package com.netcracker.services;

import com.netcracker.dao.AccountDao;
import com.netcracker.dao.ApartmentDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.exception.ObjectNotUniqueException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.Role;
import com.netcracker.secutity.jwt.JwtAccount;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    public ApartmentInfoService(AccountService accountService, ApartmentDao apartmentDao, AccountDao accountDao) {
        this.accountService = accountService;
        this.apartmentDao = apartmentDao;
        this.accountDao = accountDao;
    }

    public List<Apartment> getAllApartments() throws DaoAccessException {
        return apartmentDao.getAllApartments();
    }

    public Apartment createApartment(Apartment apartment) throws DaoAccessException {
        apartment.setAccountId(null);
        if (!isUnique(apartment)) {
            return null;
        }

        apartmentDao.createApartment(apartment);
        apartment.setAccountId(accountService.getAccountByEmail(apartment.getEmail()).getAccountId());
        apartmentSubBillService.addApartmentSubBillsToApartment(apartment);
        return apartment;
    }

    public Apartment getApartmentById(BigInteger accountId) throws DaoAccessException {
        return apartmentDao.getApartmentById(accountId);
    }

    public Apartment getApartmentByApartmentNumber(Integer apartmentsNumber) throws DaoAccessException {
        return apartmentDao.getApartmentByApartmentNumber(apartmentsNumber);
    }

    public List<Apartment> getAllApartmentsByFloor(List<Integer> floor) throws DaoAccessException {
       return apartmentDao.getAllApartmentByFloor(floor);
    }


    public Apartment updateApartmentPassword(JwtAccount updater, Apartment apartment) throws DaoAccessException {
        Account acc = accountService.getAccountByEmail(apartment.getEmail());
        if (!updater.getId().equals(acc.getAccountId())
                && !updater.getAuthorities().toString().equals("[ROLE_MANAGER]")) {
            throw new NotBelongToAccountException("Can not change this account password");
        }

        acc.setPassword(apartment.getPassword());
        accountDao.updateAccount(acc);
        return apartment;
    }

    public Apartment updateApartment(Apartment apartment, Account updater) throws DaoAccessException {
        Apartment apartmentToUpdate = apartmentDao.getApartmentByApartmentNumber(apartment.getApartmentNumber());
        Account account = accountDao.getAccount(apartmentToUpdate.getAccountId());

        //OWNER can only change email or password
        if (updater.getRole().equals(Role.OWNER)) {
            if (!updater.getAccountId().equals(apartmentToUpdate.getAccountId())) {
                throw new NotBelongToAccountException("Can not change this apartments data");
            }
            account.setEmail(apartment.getEmail());
        }

        //MANAGER change all fields
        if (updater.getRole().equals(Role.MANAGER)) {
            account.setEmail(apartment.getEmail());
            account.setFirstName(apartment.getFirstName());
            account.setLastName(apartment.getLastName());
            account.setPhoneNumber(apartment.getPhoneNumber());

            apartmentToUpdate.setPeopleCount(apartment.getPeopleCount());
            apartmentToUpdate.setEmail(apartment.getEmail());
            apartmentToUpdate.setFirstName(account.getFirstName());
            apartmentToUpdate.setLastName(account.getLastName());
            apartmentToUpdate.setPhoneNumber(account.getPhoneNumber());
        }

        if (isUnique(apartmentToUpdate)) {
            apartmentDao.updateApartment(apartmentToUpdate);
            accountDao.updateAccount(account);
        }
        return apartmentToUpdate;

    }

    private boolean isUnique(Apartment apartment) throws IllegalArgumentException {
        List<Apartment> apartments = apartmentDao.getUniqueApartment(apartment);
        for (Apartment a : apartments) {
            if (apartment.getAccountId() == null
                    || !apartment.getApartmentNumber().equals(a.getApartmentNumber())) {

                if (a.getEmail().equals(apartment.getEmail())) {
                    throw new ObjectNotUniqueException("This email is already in use", BigInteger.valueOf(74));
                }
                if (a.getApartmentNumber().equals(apartment.getApartmentNumber())) {
                    throw new ObjectNotUniqueException("This apartment already has an account", BigInteger.valueOf(74));
                }
            }
        }
        return true;
    }

}
