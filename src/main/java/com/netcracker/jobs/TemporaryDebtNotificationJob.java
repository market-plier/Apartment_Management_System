package com.netcracker.jobs;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CommunalUtility;
import com.netcracker.services.NotificationService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Log4j
@Component
public class TemporaryDebtNotificationJob {
    @Autowired
    private NotificationService notificationService;

    public Runnable getJob(CommunalUtility communalUtility) {
        return () -> {
            try {
                notificationService.sendTempCommunalUtilityNotificationToAllApartments(communalUtility);
            } catch (IOException | MessagingException | DaoAccessException e) {
                log.error("TemporaryDebtNotification method getJob: " + e.getMessage(), e);
            }

            log.log(Level.INFO, new Date() + " TemporaryDebtNotification with "
                    + " on thread " + Thread.currentThread().getName());
        };
    }
}
