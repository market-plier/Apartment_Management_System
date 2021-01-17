package com.netcracker.controllers;

import com.netcracker.jobs.DebtPaymentsJob;
import com.netcracker.jobs.DebtNotificationJob;
import com.netcracker.services.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trigger")
public class JobTriggerController {

    ScheduleJobService scheduleJobService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    public JobTriggerController(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    @GetMapping("/debtNotificationJobTrigger")
    public boolean debtNotificationTrigger() {
        scheduleJobService.getScheduler().execute(context.getBean(DebtNotificationJob.class).getJob());
        return true;
    }

    @GetMapping("/deptPaymentJobTrigger")
    public boolean deptPaymentTrigger() {
        scheduleJobService.getScheduler().execute(context.getBean(DebtPaymentsJob.class).getJob());
        return true;
    }

}
