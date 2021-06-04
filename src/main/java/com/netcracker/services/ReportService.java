package com.netcracker.services;

import com.netcracker.dao.ApartmentSubBillDao;
import com.netcracker.dao.ManagerSubBillDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.DaoAccessExceptionBuilder;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.exception.NotFoundInformationForReportException;
import com.netcracker.models.*;
import com.netcracker.services.PDFBuilders.ApartmentsDebtsPdfBuilder;
import com.netcracker.services.PDFBuilders.ManagerSpendingOperationPdfBuilder;
import com.netcracker.services.PDFBuilders.ManagerSubBillDebtsPdfBuilder;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.IOException;
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
                                                                                      Date start, Date end) throws DaoAccessException, IOException {
        List<ManagerSpendingOperation> managerSpendingOperations = managerOperationSpendingService
                .getAllManagerOperationByDateAndCommunalUtility(start, end, communalUtility);

        if (!managerSpendingOperations.isEmpty()) {
            ManagerSpendingOperationPdfBuilder managerSpendingOperationPdfBuilder
                    = new ManagerSpendingOperationPdfBuilder(start, end, managerSpendingOperations);
            return managerSpendingOperationPdfBuilder.exportToPdf();
        } else {
            NotFoundInformationForReportException reportException = new NotFoundInformationForReportException
                    ("Not found any information for these communal or date", BigInteger.valueOf(8098));

            log.info("IN createManagerOperationSpendingReportByCommNameAndDate: " + reportException.getMessage());

            throw reportException;
        }


    }

    public ByteArrayInputStream createManagerOperationSpendingReportByDate(Date start, Date end) throws DaoAccessException, IOException {
        List<ManagerSpendingOperation> managerSpendingOperations = managerOperationSpendingService
                .getAllManagerOperationByDate(start, end);

        if (!managerSpendingOperations.isEmpty()) {
            ManagerSpendingOperationPdfBuilder managerSpendingOperationPdfBuilder
                    = new ManagerSpendingOperationPdfBuilder(start, end, managerSpendingOperations);
            return managerSpendingOperationPdfBuilder.exportToPdf();
        } else {
            NotFoundInformationForReportException reportException = new NotFoundInformationForReportException
                    ("Not found any information for these date", BigInteger.valueOf(8098));
            log.info("IN createManagerOperationSpendingReportByDate: " + reportException.getMessage());

            throw reportException;
        }


    }

    public ByteArrayInputStream createApartmentDebtReportByCommunalID(BigInteger accountID, Set<BigInteger> communalUtility) throws DaoAccessException, IOException {

        List<ApartmentSubBill> apartmentSubBillList = apartmentSubBillDao.getApartmentSubBillsByCommunalUtilityList(accountID, communalUtility);
        if (!apartmentSubBillList.isEmpty()) {
            Apartment apartment = apartmentInfoService.getApartmentById(accountID);
            ApartmentsDebtsPdfBuilder apartmentsDebtsPdfBuilder
                    = new ApartmentsDebtsPdfBuilder(apartmentSubBillList, apartment);
            return apartmentsDebtsPdfBuilder.exportToPdf();
        } else {
            NotFoundInformationForReportException reportException = new NotFoundInformationForReportException
                    ("Not found any information for these communal utility", BigInteger.valueOf(8098));

            log.info("IN createApartmentDebtReportByCommunalID: " + reportException.getMessage());

            throw reportException;
        }


    }

    public ByteArrayInputStream createManagerSubBillDebtReportByCommunalID(Set<BigInteger> communalUtility) throws IOException {
        Map<ManagerSubBill, Double> managerDebtMap = managerSubBillDao.getManagerSubBillDeptByCommunalUtility(communalUtility);

        if (!managerDebtMap.isEmpty()) {
            ManagerSubBillDebtsPdfBuilder managerSubBillDebtsPdfBuilder = new ManagerSubBillDebtsPdfBuilder(managerDebtMap);
            return managerSubBillDebtsPdfBuilder.exportToPdf();
        } else {
            NotFoundInformationForReportException reportException = new NotFoundInformationForReportException
                    ("Not found any information for these communal utility", BigInteger.valueOf(8098));
            log.info("IN createManagerSubBillDebtReportByCommunalID: " + reportException.getMessage());
            throw reportException;
        }

    }

}
