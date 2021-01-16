package com.netcracker.controllers;

import com.netcracker.jobs.DebtPaymentsJob;
import com.netcracker.jobs.DebtNotificationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trigger")
public class JobTriggerController {
    @Autowired
    private DebtNotificationJob debtNotificationJob;
    @Autowired
    private DebtPaymentsJob debtPaymentsJob;

    @GetMapping("/debtNotificationJobTrigger")
    public boolean debtNotificationTrigger() {
        debtNotificationJob.getJob().run();
        return true;
    }

    @GetMapping("/debtPaymentJobTrigger")
    public boolean debtPaymentTrigger() {
        debtPaymentsJob.getJob().run();
        return true;
    }
}
