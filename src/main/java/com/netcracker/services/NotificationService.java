package com.netcracker.services;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.Announcement;
import com.netcracker.models.CommunalUtility;
import com.netcracker.models.NotificationBuildInfo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j
@Service
public class NotificationService {
    @Autowired
    private MailService mailService;
    @Autowired
    private ReportService reportService;
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private CommunalUtilityService communalUtilityService;

    private NotificationBuildInfo announcementNotification;
    private NotificationBuildInfo debtNotification;

    public NotificationService() {
        announcementNotification = new NotificationBuildInfo(
                "New announcement by ",
                "A new announcement was published. Title: "
        );

        debtNotification = new NotificationBuildInfo(
                "Debts by ",
                "Please, review debts report of your apartment to date. "
        );
    }

    public void sendAnnouncementNotificationToAllApartments(Announcement announcement)
            throws MailException, DaoAccessException, NullPointerException {
        try {
            for (Account account : apartmentInfoService.getAllApartments()) {
                mailService.sendMessage(
                        account.getEmail(),
                        announcementNotification.getTitle() + LocalDate.now(),
                        announcementNotification.getBody() + announcement.getTitle()
                );
            }
        } catch (NullPointerException e) {
            log.error("NotificationService method sendAnnouncementNotificationToAllApartments: " + e.getMessage(), e);
            throw e;
        }
    }

    public void sendDebtNotificationToAllApartments()
            throws IOException, MessagingException, DaoAccessException, NullPointerException {
        try {
            for (Account account : apartmentInfoService.getAllApartments()) {
                Set<BigInteger> communalUtilityIds = communalUtilityService
                        .getAllCommunalUtilities(CommunalUtility.Status.Enabled)
                        .stream()
                        .map(CommunalUtility::getCommunalUtilityId)
                        .collect(Collectors.toSet());

                ByteArrayInputStream arrayInputStream = reportService
                        .createApartmentDebtReportByCommunalID(account.getAccountId(), communalUtilityIds);

                mailService.sendMessageWithAttachment(
                        account.getEmail(),
                        debtNotification.getTitle() + LocalDate.now(),
                        debtNotification.getBody(),
                        "ApartmentDebtsBy" + LocalDate.now(),
                        arrayInputStream);
            }
        } catch (NullPointerException e) {
            log.error("NotificationService method sendDebtNotificationToAllApartments: " + e.getMessage(), e);
            throw e;
        }
    }
}
