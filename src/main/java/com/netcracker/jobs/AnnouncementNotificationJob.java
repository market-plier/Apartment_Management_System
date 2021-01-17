package com.netcracker.jobs;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Announcement;
import com.netcracker.services.NotificationService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Log4j
@Component
public class AnnouncementNotificationJob  {
    @Autowired
    private  NotificationService notificationService;

    public Runnable getJob(Announcement announcement) {
        return () -> {
            try {
                notificationService.sendAnnouncementNotificationToAllApartments(announcement);
            } catch (MailException | DaoAccessException e) {
                log.error("AnnouncementNotificationJob method getJob: " + e.getMessage(), e);
            }

            log.log(Level.INFO, new Date() + " AnnouncementNotificationJob with "
                    + " on thread " + Thread.currentThread().getName());
        };
    }
}
