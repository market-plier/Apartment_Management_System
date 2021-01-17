package com.netcracker.jobs;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.services.NotificationService;
import com.netcracker.services.ScheduleJobService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Log4j
@Component
public class DebtNotificationJob implements Job {
    private final Trigger trigger;

    private final NotificationService notificationService;


    @Autowired
    public DebtNotificationJob(NotificationService notificationService,
                               ScheduleJobService scheduledJobService) {
        this.notificationService = notificationService;

        trigger = new CronTrigger("0 0 9 1 1/1 *");   //Every first day of month
        scheduledJobService.addJobToScheduler(2, this.getJob(), this.getTrigger());
    }

    @Override
    public Runnable getJob() {
        return () -> {
            try {
                notificationService.sendDebtNotificationToAllApartments();
            } catch (IOException | MessagingException | DaoAccessException e) {
                log.error("DebtNotificationJob method getJob: " + e.getMessage(), e);
            }

            log.log(Level.INFO, new Date() + " DebtNotificationJob with "
                    + " on thread " + Thread.currentThread().getName());
        };
    }

    @Override
    public Trigger getTrigger() {
        return trigger;
    }
}