package com.netcracker.controllers;

import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.models.ApartmentRequestToManager;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ApartmentRequestToManagerService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j
public class ApartmentRequestToManagerController {
    private final ApartmentRequestToManagerService apartmentRequestToManagerService;

    @Autowired
    public ApartmentRequestToManagerController(ApartmentRequestToManagerService apartmentRequestToManagerService) {
        this.apartmentRequestToManagerService = apartmentRequestToManagerService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createApartmentRequestToManager")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ApartmentRequestToManager createApartmentRequestToManager(@RequestBody ApartmentRequestToManager request) {
        JwtAccount account = (JwtAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (account.getId().equals(request.getApartmentId())) {
            apartmentRequestToManagerService.generateApartmentRequestToManager(request);
            return request;
        } else {
            NotBelongToAccountException e = new NotBelongToAccountException("Can not send request from this account");
            log.log(Level.WARN, e.getMessage(), e);
            throw e;
        }
    }

}
