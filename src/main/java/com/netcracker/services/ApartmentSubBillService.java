
package com.netcracker.services;

import com.netcracker.dao.ApartmentSubBillDao;
import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.*;
import com.netcracker.models.PojoBuilder.ApartmentOperationBuilder;
import com.netcracker.models.PojoBuilder.ApartmentSubBillBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.Set;


@Service
@Log4j
@Transactional
public class ApartmentSubBillService {
    @Autowired
    private ApartmentSubBillDao apartmentSubBillDao;
    @Autowired
    private ApartmentOperationService apartmentOperationService;
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private ApartmentPaymentService apartmentPaymentService;
    @Autowired
    private DebtPaymentOperationService debtPaymentOperationService;
    @Autowired
    private CommunalUtilityService communalUtilityService;

    public List<ApartmentSubBill> getAllApartmentSubBills() throws DaoAccessException {
        List<ApartmentSubBill> apartmentSubBills = apartmentSubBillDao.getAllApartmentSubBills();
        for (ApartmentSubBill apartmentSubBill : apartmentSubBills) {
            BigInteger apartmentSubBillId = apartmentSubBill.getSubBillId();
            apartmentSubBill.setApartmentOperations(apartmentOperationService.getAllApartmentOperationsBySubBillId(apartmentSubBillId));
            apartmentSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByApartmentSubBillId(apartmentSubBillId));
        }
        return apartmentSubBills;
    }

    public ApartmentSubBill getApartmentSubBill(BigInteger apartmentSubBillId) throws DaoAccessException {

        ApartmentSubBill apartmentSubBill = apartmentSubBillDao.getApartmentSubBillById(apartmentSubBillId);
        apartmentSubBill.setApartmentOperations(apartmentOperationService.getAllApartmentOperationsBySubBillId(apartmentSubBillId));
        apartmentSubBill.setDebtPaymentOperations(debtPaymentOperationService.getDebtPaymentOperationsByApartmentSubBillId(apartmentSubBillId));
        return apartmentSubBill;
    }


    public void createApartmentSubBill(CommunalUtility communalUtility) throws DaoAccessException {
        for (Apartment apartment : apartmentInfoService.getAllApartments()) {
            apartmentSubBillDao.createApartmentSubBill(new ApartmentSubBillBuilder()
                    .withApartment(apartment)
                    .withCommunalUtility(communalUtility)
                    .withDept((double) 0)
                    .withBalance((double) 0)
                    .build());
        }
    }

    public void addApartmentSubBillsToApartment(Apartment apartment) {
        try {
            for (CommunalUtility communalUtility : communalUtilityService.getAllCommunalUtilities(CommunalUtility.Status.Enabled)) {
                apartmentSubBillDao.createApartmentSubBill(new ApartmentSubBillBuilder()
                        .withApartment(apartment)
                        .withCommunalUtility(communalUtility)
                        .withDept((double) 0)
                        .withBalance((double) 0)
                        .build());
            }
        } catch (NullPointerException e) {
            log.error("ApartmentSubBillService method addApartmentSubBillsToApartment: " + e.getMessage(), e);
            throw e;
        }
    }

    public void createApartmentSubBillTransfer(BigInteger apartmentId, String transferFromCommunalUtilityName, String transferToCommunalUtilityName, Double value)
            throws IllegalArgumentException {
        try {

            ApartmentSubBill subBillFrom = apartmentSubBillDao.getApartmentSubBillByApartmentIdAndCommunalUtilityName(apartmentId, transferFromCommunalUtilityName);
            ApartmentSubBill subBillTo = apartmentSubBillDao.getApartmentSubBillByApartmentIdAndCommunalUtilityName(apartmentId, transferToCommunalUtilityName);

            if (!subBillFrom.getApartment().getAccountId().equals(subBillTo.getApartment().getAccountId())) {
                throw new NotBelongToAccountException("Wrong transfer SubBills");
            }

            if (subBillFrom.getBalance() < value) {
                throw new IllegalArgumentException("Wrong transfer Data");
            }
            subBillFrom.setBalance(subBillFrom.getBalance() - value);
            subBillTo.setBalance(subBillTo.getBalance() + value);

            apartmentSubBillDao.updateApartmentSubBill(subBillFrom);
            apartmentSubBillDao.updateApartmentSubBill(subBillTo);

            apartmentOperationService.createApartmentOperation(new ApartmentOperationBuilder()
                    .withApartmentSubBill(new ApartmentSubBillBuilder().withSubBillId(subBillFrom.getSubBillId()).build())
                    .withSum(value)
                    .build());
            apartmentOperationService.createApartmentOperation(new ApartmentOperationBuilder()
                    .withApartmentSubBill(new ApartmentSubBillBuilder().withSubBillId(subBillTo.getSubBillId()).build())
                    .withSum(-value)
                    .build());

        } catch (NullPointerException |
                IllegalArgumentException e) {
            log.error("ApartmentSubBillService method createApartmentSubBillTransfer: " + e.getMessage(), e);
            throw e;
        }
    }

    public void updateApartmentSubBillByApartmentOperation(ApartmentOperation apartmentOperation) throws DaoAccessException {
        ApartmentSubBill apartmentSubBill = apartmentSubBillDao.getApartmentSubBillById(apartmentOperation.getApartmentSubBill().getSubBillId());

        apartmentSubBill.setBalance(apartmentSubBill.getBalance() + apartmentOperation.getSum());
        apartmentSubBillDao.updateApartmentSubBill(apartmentSubBill);

        apartmentOperationService.createApartmentOperation(apartmentOperation);

        Double oldBalance = apartmentSubBill.getBalance();
        Double oldDebt = apartmentSubBill.getDebt();

        if (oldBalance >= oldDebt) {
            apartmentSubBill.setDebt(0d);
            apartmentSubBill.setBalance(oldBalance - oldDebt);
            apartmentSubBillDao.updateApartmentSubBill(apartmentSubBill);
            debtPaymentOperationService.createDebtPaymentOperation(apartmentSubBill, oldDebt);
        }
    }

    public void updateApartmentSubBillsByDebt() throws DaoAccessException {

        List<ApartmentSubBill> apartmentSubBills = apartmentSubBillDao.getAllApartmentSubBills();

        for (ApartmentSubBill apartmentSubBill : apartmentSubBills) {
            CommunalUtility communalUtility = apartmentSubBill.getCommunalUtility();
            CommunalUtility.CalculationMethod calculationMethod = communalUtility.getCalculationMethod();
            Apartment apartment = apartmentInfoService.getApartmentById(apartmentSubBill.getApartment().getAccountId());
            switch (calculationMethod) {
                case SquareMeters:
                    apartmentSubBill.setDebt(apartmentSubBill.getDebt() + new CommunalUtilityWrapper(communalUtility)
                            .evaluate((double) apartment.getSquareMetres()));
                    break;
                case PeopleCount:
                    apartmentSubBill.setDebt(apartmentSubBill.getDebt() + new CommunalUtilityWrapper(communalUtility)
                            .evaluate((double) apartment.getPeopleCount()));
                    break;
                case Floor:
                    apartmentSubBill.setDebt(apartmentSubBill.getDebt() + new CommunalUtilityWrapper(communalUtility)
                            .evaluate((double) apartment.getFloor()));
                    break;
                default:
                    break;
            }
            apartmentSubBillDao.updateApartmentSubBill(apartmentSubBill);
        }
    }

    public List<ApartmentSubBill> getAllApartmentSubBillsByAccountId(BigInteger accountId) throws DaoAccessException {
        return apartmentSubBillDao.getAllApartmentSubBillsByAccountId(accountId);
    }

    public List<ApartmentSubBill> getApartmentSubBillsByCommunalUtilityList(BigInteger accountId, Set<BigInteger> communaUtill) throws DaoAccessException {
        return apartmentSubBillDao.getApartmentSubBillsByCommunalUtilityList(accountId, communaUtill);
    }

    Double getApartmentDebtByCommunalUtilityList(BigInteger accountId, Set<BigInteger> communalList) throws DaoAccessException {
        return apartmentSubBillDao.getApartmentDebtByCommunalUtilityList(accountId, communalList);
    }
}
