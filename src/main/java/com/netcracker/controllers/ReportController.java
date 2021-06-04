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



import javax.validation.constraints.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Log4j
@RestController
@Validated
@RequestMapping("/make-report/")
public class ReportController {

    private final ReportService reportService;
    private final static String MANAGER_SPENDING_REPORT = "Manager_spending_report";
    private final static String APARTMENT_DEPT_REPORT = "Apartment_dept_report";
    private final static String MANAGER_DEPT_REPORT = "Manager_bill_dept_report";

    @Autowired
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }


    @RequestMapping(value = "manager-spending/by-date-comm-name", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
        public ResponseEntity makeReportByDateAndCommunalUtility(@RequestParam
                                                                 @NotNull(message = "start date cant be null")
                                                                 @NotEmpty(message = "start date cant be empty") String start,
                                                                 @RequestParam
                                                                 @NotNull(message = "end date cant be null")
                                                                 @NotEmpty(message = "end date cant be empty") String end,
                                                                 @RequestParam(name = "communalUtility")
                                                                 @NotNull(message = "Communal Utility date cant be null")
                                                                 Set<@Min(value = 0, message = "must be positive value")
                                                                 @Digits(integer = 10, fraction = 0,message = "Id must be integer value") String> communalUtility) throws ParseException, IOException {

        ByteArrayInputStream arrayInputStream = reportService
                .createManagerOperationSpendingReportByCommNameAndDate(communalUtility.stream().map(BigInteger::new).collect(Collectors.toSet()),
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

    public ResponseEntity makeManagerOperationSpendingReportByDate(@RequestParam
                                                                   @NotNull(message = "start date cant be null")
                                                                   @NotEmpty(message = "start date cant be empty") String start,
                                                                   @RequestParam
                                                                   @NotNull(message = "end date cant be null")
                                                                   @NotEmpty(message = "end date cant be empty") String end) throws ParseException, IOException {

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
    public ResponseEntity makeApartmentDeptReport(@RequestParam @NotNull(message = "id cant be null")
                                                  @Positive(message = "id must be positive value") BigInteger accountId,
                                                  @RequestParam @NotNull(message = "communal utility cant be null ")
                                                  Set<@Min(value = 0, message = "must be positive value")
                                                  @Digits(integer = 10, fraction = 0,message = "Id must be integer value") String> communalUtility) throws IOException {

        ByteArrayInputStream arrayInputStream = reportService
                .createApartmentDebtReportByCommunalID(accountId,communalUtility.stream().map(BigInteger::new).collect(Collectors.toSet()));

        HttpHeaders headers = new HttpHeaders();
        String fileName = new Date().getTime() + APARTMENT_DEPT_REPORT + ".pdf";
        headers.add("Content-Disposition", "inline; filename="+fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(arrayInputStream));
    }

    @RequestMapping(value = "manager/dept-manager-bill",method = RequestMethod.GET,produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity makeManagerBillDeptReport(@RequestParam
                                                    @NotNull(message = "communal utility cant be null")
                                                    Set<@Min(value = 0, message = "must be positive value")
                                                    @Digits(integer = 10, fraction = 0,message = "Id must be integer value") String> communalUtility) throws IOException {

        ByteArrayInputStream arrayInputStream = reportService
                .createManagerSubBillDebtReportByCommunalID(communalUtility.stream().map(BigInteger::new).collect(Collectors.toSet()));

        HttpHeaders headers = new HttpHeaders();
        String fileName = new Date().getTime() + MANAGER_DEPT_REPORT+ ".pdf";
        headers.add("Content-Disposition", "inline; filename="+fileName);

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(arrayInputStream));
    }

}
