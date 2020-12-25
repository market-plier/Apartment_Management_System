package com.netcracker.services;

import com.netcracker.models.Account;
import com.netcracker.models.ApartmentRequestToManager;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service("ApartmentRequestToManagerService")
public class ApartmentRequestToManagerService {
    private final MailService mailService;
    private final ManagerInfoService managerInfoService;
    private final AccountService accountService;

    @Autowired
    public ApartmentRequestToManagerService(AccountService accountService, MailService mailService, ManagerInfoService managerInfoService) {
        this.accountService = accountService;
        this.mailService = mailService;
        this.managerInfoService = managerInfoService;
    }

    public void generateApartmentRequestToManager(ApartmentRequestToManager request) {
        try {
            Account account = accountService.getAccountById(request.getApartmentId());
            if (account != null) {
                String subject = "Request from " + account.getEmail();
                String text = request.getText() + '\n' +
                        "-- \n" +
                        "Call me: \n" +
                        account.getFirstName() + " " + account.getLastName() + '\n' +
                        "Phone: " + account.getPhoneNumber();
                mailService.sendMessage(managerInfoService.getManager().getEmail(), subject, text);
            }
        } catch (NullPointerException e) {
            log.error("ApartmentRequestToManagerService method generateApartmentRequestToManager: " + e.getMessage(), e);
            throw e;
        }
    }
}
