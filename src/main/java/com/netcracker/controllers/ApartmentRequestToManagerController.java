package com.netcracker.controllers;

import com.netcracker.exception.NotBelongToAccountException;
import com.netcracker.models.ApartmentRequestToManager;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ApartmentRequestToManagerService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@RestController
@Log4j
public class ApartmentRequestToManagerController {
    private final ApartmentRequestToManagerService apartmentRequestToManagerService;

    @Autowired
    public ApartmentRequestToManagerController(ApartmentRequestToManagerService apartmentRequestToManagerService) {
        this.apartmentRequestToManagerService = apartmentRequestToManagerService;
    }

    @PostMapping(value = "/createApartmentRequestToManager")
    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    public ApartmentRequestToManager createApartmentRequestToManager(@RequestBody @Valid ApartmentRequestToManager r) {

        JwtAccount account = (JwtAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ApartmentRequestToManager request = new ApartmentRequestToManager(account.getId(), r.getText());

        if (account.getId().equals(request.getApartmentId())) {
           apartmentRequestToManagerService.generateApartmentRequestToManager(request);
        } else {
            NotBelongToAccountException e = new NotBelongToAccountException("Can not send request from this account");
            log.log(Level.WARN, e.getMessage(), e);
            throw e;
        }
        return r;
    }

}
