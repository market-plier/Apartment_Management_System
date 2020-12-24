package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.NotBelongToAccountException;
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

@RestController("/apartments")
public class ApartmentInfoController {

    private final ApartmentInfoService apartmentInfoService;

    @Autowired
    public ApartmentInfoController(ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public Apartment createApartment(@RequestBody @Valid Apartment apartment) {
        return apartmentInfoService.createApartment(apartment);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public Apartment updateApartment(@RequestBody @Valid Apartment apartment)
            throws NullPointerException, DaoAccessException, IllegalArgumentException {
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
    @GetMapping("/{id}")
    public Apartment getApartment(@PathVariable @NotNull BigInteger id) throws NullPointerException, DaoAccessException {
        return apartmentInfoService.getApartmentById(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @GetMapping
    public List<Apartment> getAllApartments() throws NullPointerException, DaoAccessException {
        return apartmentInfoService.getAllApartments();
    }

}
