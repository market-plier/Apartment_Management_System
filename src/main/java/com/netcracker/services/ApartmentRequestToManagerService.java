package com.netcracker.services;

import com.netcracker.models.Apartment;
import com.netcracker.models.ApartmentRequestToManager;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j
@Service("ApartmentRequestToManagerService")
public class ApartmentRequestToManagerService {
    private final MailService mailService;
    private final ManagerInfoService managerInfoService;
    private final ApartmentInfoService apartmentInfoService;

    @Autowired
    public ApartmentRequestToManagerService(MailService mailService, ManagerInfoService managerInfoService, ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
        this.mailService = mailService;
        this.managerInfoService = managerInfoService;
    }

    public void generateApartmentRequestToManager(ApartmentRequestToManager request) {
            Apartment apartment = apartmentInfoService.getApartmentById(request.getApartmentId());
            String subject = "Request from Apartment № " + apartment.getApartmentNumber();
            String text = request.getText() + '\n' +
                    "-- \n" +
                    "Contact me: \n" +
                    apartment.getFirstName() + " " + apartment.getLastName() + '\n' +
                    "Phone: " + apartment.getPhoneNumber() + '\n' +
                    "Email: " + apartment.getEmail();
            mailService.sendMessage(managerInfoService.getManager().getEmail(), subject, text);
    }
}
