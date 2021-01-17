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
import java.util.HashSet;
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
    private NotificationBuildInfo tempCommunalUtilityNotification;

    public NotificationService() {
        announcementNotification = new NotificationBuildInfo(
                "New announcement by ",
                "A new announcement was published. Title: "
        );

        debtNotification = new NotificationBuildInfo(
                "Debts by ",
                "Please, review debts report of your apartment to date. "
        );

        tempCommunalUtilityNotification = new NotificationBuildInfo(
                "New Temporary Communal Utility - ",
                "Please, review new debts of your apartment to date. "
        );
    }

    public synchronized void sendAnnouncementNotificationToAllApartments(Announcement announcement)
            throws MailException, DaoAccessException {

            for (Account account : apartmentInfoService.getAllApartments()) {
                mailService.sendMessage(
                        account.getEmail(),
                        announcementNotification.getTitle() + LocalDate.now(),
                        announcementNotification.getBody() + announcement.getTitle()
                );
            }
    }

    public synchronized void sendDebtNotificationToAllApartments()
            throws IOException, MessagingException, DaoAccessException {

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
                        debtNotification.getBody() + '\n' + LocalDate.now(),
                        "ApartmentDebtsBy" + LocalDate.now(),
                        arrayInputStream);
            }

    }

    public synchronized void sendTempCommunalUtilityNotificationToAllApartments(CommunalUtility communalUtility)
            throws IOException, MessagingException, DaoAccessException{

            for (Account account : apartmentInfoService.getAllApartments()) {
                ByteArrayInputStream arrayInputStream = reportService
                        .createApartmentDebtReportByCommunalID(
                                account.getAccountId(),
                                new HashSet<BigInteger>() {{ add(communalUtility.getCommunalUtilityId()); }}
                                );

                mailService.sendMessageWithAttachment(
                        account.getEmail(),
                        tempCommunalUtilityNotification.getTitle() + communalUtility.getName(),
                        tempCommunalUtilityNotification.getBody() + '\n' + LocalDate.now(),
                        communalUtility.getName() + "ApartmentDebtsBy" + LocalDate.now(),
                        arrayInputStream);
            }
    }
}
