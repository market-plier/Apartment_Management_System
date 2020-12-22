package com.netcracker.controllers;


import com.netcracker.services.ReportService;
import com.netcracker.util.DateUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;


@Slf4j
@RestController
@RequestMapping("/make-report/")
public class ReportController {

    private final ReportService reportService;
    private final static String MANAGER_SPENDING_REPORT = "Manager_spending_report";
    private final static String APARTMENT_DEPT_REPORT = "Apartment_dept_report";
    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @RequestMapping(value = "manager-spending/by-date-comm-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity makeReportByDateAndCommunalUtility(@RequestParam (value = "start") String start,
                                                             @RequestParam(value = "end") String end,
                                                             @RequestParam(value ="communalUtility") Set<BigInteger> communalUtility) throws ParseException {

        ByteArrayInputStream arrayInputStream = reportService
                .createManagerOperationSpendingReportByCommNameAndDate(communalUtility,
                        DateUtil.provideDateFormat(start), DateUtil.provideDateFormat(end));

        HttpHeaders headers = new HttpHeaders();
        String fileName = new Date().getTime() + MANAGER_SPENDING_REPORT + ".pdf";
        headers.add("Content-Disposition", "inline; filename="+fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(arrayInputStream));
    }

    @RequestMapping(value = "manager-spending/by-date",method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity makeManagerOperationSpendingReportByDate(@RequestParam String start,
                                                                   @RequestParam String end) throws ParseException {

        ByteArrayInputStream arrayInputStream = reportService
                .createManagerOperationSpendingReportByDate(DateUtil.provideDateFormat(start), DateUtil.provideDateFormat(end));

        HttpHeaders headers = new HttpHeaders();
        String fileName = new Date().getTime() + MANAGER_SPENDING_REPORT + ".pdf";
        headers.add("Content-Disposition", "inline; filename="+fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(arrayInputStream));
    }

    @RequestMapping(value = "apartment/dept-report",method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity makeApartmentDeptReport(@RequestParam BigInteger accountId,
                                                                   @RequestParam Set<BigInteger> communalUtility){

        ByteArrayInputStream arrayInputStream = reportService
                .createApartmentDebtReportByCommunalID(accountId,communalUtility);

        HttpHeaders headers = new HttpHeaders();
        String fileName = new Date().getTime() + APARTMENT_DEPT_REPORT + ".pdf";
        headers.add("Content-Disposition", "inline; filename="+fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(arrayInputStream));
    }

}
