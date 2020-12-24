package com.netcracker.services;

import com.netcracker.dao.ApartmentSubBillDao;
import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.*;
import com.netcracker.services.PDFBuilders.ApartmentsDebtsPdfBuilder;
import com.netcracker.services.PDFBuilders.ManagerSpendingOperationPdfBuilder;
import com.netcracker.services.PDFBuilders.ManagerSubBillDebtsPdfBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.sql.Date;
import java.util.*;


@Service
@Log4j
public class ReportService {

    private final ManagerOperationSpendingService managerOperationSpendingService;
    private final ApartmentSubBillDao apartmentSubBillDao;
    private final ApartmentInfoService apartmentInfoService;
    private final ManagerSubBillDao managerSubBillDao;

    @Autowired
    public ReportService(ManagerOperationSpendingService managerOperationSpendingService, ApartmentSubBillDao apartmentSubBillDao, ApartmentInfoService apartmentInfoService, ManagerSubBillDao managerSubBillDao) {
        this.managerOperationSpendingService = managerOperationSpendingService;
        this.apartmentSubBillDao = apartmentSubBillDao;
        this.apartmentInfoService = apartmentInfoService;
        this.managerSubBillDao = managerSubBillDao;
    }


    public ByteArrayInputStream createManagerOperationSpendingReportByCommNameAndDate(Set<BigInteger> communalUtility,
                                                                                      Date start, Date end) throws DaoAccessException {
        try {

            List<ManagerSpendingOperation> managerSpendingOperations = managerOperationSpendingService
                    .getAllManagerOperationByDateAndCommunalUtility(start, end, communalUtility);
            if (managerSpendingOperations != null) {
                ManagerSpendingOperationPdfBuilder managerSpendingOperationPdfBuilder
                        = new ManagerSpendingOperationPdfBuilder(start, end, managerSpendingOperations);
                return managerSpendingOperationPdfBuilder.exportToPdf();
            }
        } catch (NullPointerException e) {
            log.error("IN Service method createManagerOperationSpendingReportByCommNameAndDate: " + e.getMessage(),e);
            throw e;
        }

        return null;
    }

    public ByteArrayInputStream createManagerOperationSpendingReportByDate(Date start, Date end) throws DaoAccessException {
        try {
            List<ManagerSpendingOperation> managerSpendingOperations = managerOperationSpendingService
                    .getAllManagerOperationByDate(start, end);

            if (managerSpendingOperations != null) {
                ManagerSpendingOperationPdfBuilder managerSpendingOperationPdfBuilder
                        = new ManagerSpendingOperationPdfBuilder(start, end, managerSpendingOperations);
                return managerSpendingOperationPdfBuilder.exportToPdf();
            }
        } catch (NullPointerException e) {
            log.error("IN Service method createManagerOperationSpendingReportByDate: " + e.getMessage(),e);
            throw e;
        }

        return null;
    }

    public ByteArrayInputStream createApartmentDebtReportByCommunalID(BigInteger accountID, Set<BigInteger> communalUtility) throws DaoAccessException {
        try {
        List<ApartmentSubBill> apartmentSubBillList = apartmentSubBillDao.getApartmentSubBillsByCommunalUtilityList(accountID, communalUtility);
        Apartment apartment = apartmentInfoService.getApartmentById(accountID);
        if (apartmentSubBillList!=null && apartment!=null)
        {
            ApartmentsDebtsPdfBuilder apartmentsDebtsPdfBuilder
                    = new ApartmentsDebtsPdfBuilder(apartmentSubBillList,apartment);
            return apartmentsDebtsPdfBuilder.exportToPdf();
        }
        } catch (NullPointerException e) {
            log.error("IN Service method createApartmentDebtReportByCommunalID: " + e.getMessage(),e);
            throw e;
        }

        return null;
    }

    public ByteArrayInputStream createManagerSubBillDebtReportByCommunalID(Set<BigInteger> communalUtility)
    {

        Map<ManagerSubBill,Double> managerDebtMap =  managerSubBillDao.getManagerSubBillDeptByCommunalUtility(communalUtility);

        try {
            if (managerDebtMap!=null)
            {
                ManagerSubBillDebtsPdfBuilder managerSubBillDebtsPdfBuilder = new ManagerSubBillDebtsPdfBuilder(managerDebtMap);
                return managerSubBillDebtsPdfBuilder.exportToPdf();
            }
        }catch (NullPointerException e)
        {
            log.error("IN createManagerSubBillDebtReportByCommunalID: "+ e.getMessage(),e);
            throw e;
        }

        return null;
    }

}
