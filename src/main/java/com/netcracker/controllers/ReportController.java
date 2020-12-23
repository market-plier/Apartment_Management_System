package com.netcracker.controllers;


import com.netcracker.services.ReportService;
import com.netcracker.util.DateUtil;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;


@Log4j
@RestController
@Validated
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
    public ResponseEntity makeReportByDateAndCommunalUtility(@RequestParam @Valid @NotNull @NotEmpty String start,
                                                             @RequestParam @Valid @NotNull @NotEmpty String end,
                                                             @RequestParam @NotNull Set<BigInteger> communalUtility) throws ParseException {

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
    public ResponseEntity makeManagerOperationSpendingReportByDate(@RequestParam @Valid @NotNull @NotEmpty String start,
                                                                   @RequestParam @Valid @NotNull @NotEmpty String end) throws ParseException {

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
    public ResponseEntity makeApartmentDeptReport(@RequestParam @NotNull BigInteger accountId,
                                                  @RequestParam @NotNull Set<BigInteger> communalUtility){

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
