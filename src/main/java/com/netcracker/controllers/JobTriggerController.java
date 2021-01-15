package com.netcracker.controllers;

import com.netcracker.jobs.DebtPaymentsJob;
import com.netcracker.jobs.NotificationJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trigger")
public class JobTriggerController {

    private final NotificationJob notificationJob;
    private final DebtPaymentsJob debtPaymentsJob;

    @Autowired
    public JobTriggerController(NotificationJob notificationJob, DebtPaymentsJob debtPaymentsJob) {
        this.notificationJob = notificationJob;
        this.debtPaymentsJob = debtPaymentsJob;
    }

    @GetMapping("/deptPaymentJobTrigger")
    public boolean deptPaymentTrigger() {
        debtPaymentsJob.getJob();
        return true;
    }

    @GetMapping("/announcementJobTrigger")
    public boolean announcementJobTrigger() {
        notificationJob.getJob();
        return true;
    }

}
