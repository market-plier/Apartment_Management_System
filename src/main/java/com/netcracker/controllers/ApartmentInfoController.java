package com.netcracker.controllers;

import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.PojoBuilder.AccountBuilder;
import com.netcracker.models.Role;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ApartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.List;

@RestController
public class ApartmentInfoController {

    private final ApartmentInfoService apartmentInfoService;

    @Autowired
    public ApartmentInfoController(ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @PostMapping("/createApartment")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Apartment createApartment(@RequestBody @Valid Apartment apartment) {
        return apartmentInfoService.createApartment(apartment);
    }

    @PutMapping("/updateApartment")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Apartment updateApartment(@RequestBody @Valid Apartment apartment) {
        JwtAccount account = (JwtAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Role role = null;
        if (account.getAuthorities().toString().equals("[ROLE_OWNER]")) {
            role = Role.OWNER;
        }
        if (account.getAuthorities().toString().equals("[ROLE_MANAGER]")) {
            role = Role.MANAGER;
        }
        Account updater = new AccountBuilder()
                .withAccountId(account.getId())
                .withRole(role)
                .build();
        return apartmentInfoService.updateApartment(apartment, updater);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    @GetMapping("/getApartment/{id}")
    public Apartment getApartment(@PathVariable @NotNull BigInteger id) {
        return apartmentInfoService.getApartmentById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping("/getAllApartments")
    public List<Apartment> getAllApartments() {
        return apartmentInfoService.getAllApartments();
    }

}
