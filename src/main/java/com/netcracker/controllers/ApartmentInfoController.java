package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.PojoBuilder.AccountBuilder;
import com.netcracker.models.PojoBuilder.ApartmentBuilder;
import com.netcracker.models.Role;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ApartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/apartments")
public class ApartmentInfoController {

    private final ApartmentInfoService apartmentInfoService;

    @Autowired
    public ApartmentInfoController(ApartmentInfoService apartmentInfoService) {
        this.apartmentInfoService = apartmentInfoService;
    }

    @PostMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<Apartment> createApartment(@Valid @RequestBody Apartment apartment)
            throws DaoAccessException, ValidationException {
        if (apartment.getPassword() == null || apartment.getPassword().length() > 3900 || apartment.getPassword().length() < 8) {
            throw new ValidationException("Password length is not correct");
        }
        Apartment apart = apartmentInfoService.createApartment(apartment);

        return new ResponseEntity<>(getApartmentDTO(apart), HttpStatus.CREATED);
    }

    @PutMapping("/updatePassword")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public ResponseEntity<Apartment> updateApartmentPassword(@AuthenticationPrincipal JwtAccount account, @RequestBody @Valid Apartment apartment)
            throws DaoAccessException, ValidationException {

        if (apartment.getPassword() == null || apartment.getPassword().length() > 3900 || apartment.getPassword().length() < 8) {
            throw new ValidationException("Password length is not correct");
        }
        Apartment apart = apartmentInfoService.updateApartmentPassword(account, apartment);
        return new ResponseEntity<>(getApartmentDTO(apart), HttpStatus.ACCEPTED);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public ResponseEntity<Apartment> updateApartment(@AuthenticationPrincipal JwtAccount account, @RequestBody @Valid Apartment apartment)
            throws DaoAccessException, IllegalArgumentException {
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
        Apartment apart = apartmentInfoService.updateApartment(apartment, updater);
        return new ResponseEntity<>(getApartmentDTO(apart), HttpStatus.ACCEPTED);
    }

    @GetMapping("{number}")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER','ROLE_OWNER')")
    public ResponseEntity<Apartment> getApartment(@PathVariable @NotNull Integer number) throws DaoAccessException {
        Apartment apart = apartmentInfoService.getApartmentByApartmentNumber(number);
        return new ResponseEntity<>(getApartmentDTO(apart), HttpStatus.FOUND);
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<List<Apartment>> getAllApartments() throws DaoAccessException {
        List<Apartment> apartments = new ArrayList<>();
        for (Apartment a : apartmentInfoService.getAllApartments()) {
            apartments.add(getApartmentDTO(a));
        }
        return new ResponseEntity<>(apartments, HttpStatus.FOUND);
    }

    @GetMapping("apartments-on-floor")
    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    public ResponseEntity<List<Apartment>> getAllApartmentsByFloor(@RequestParam @NotNull List<Integer> floor) throws DaoAccessException {

        List<Apartment> apartments = new ArrayList<>();
        for (Apartment a : apartmentInfoService.getAllApartmentsByFloor(floor)) {
            apartments.add(getApartmentDTO(a));
        }
        return new ResponseEntity<>(apartments, HttpStatus.FOUND);
    }


    private Apartment getApartmentDTO(Apartment apart) {
        return new ApartmentBuilder()
                .withApartmentNumber(apart.getApartmentNumber())
                .withEmail(apart.getEmail())
                .withFirstName(apart.getFirstName())
                .withLastName(apart.getLastName())
                .withPhoneNumber(apart.getPhoneNumber())
                .withRole(apart.getRole())
                .withPeopleCount(apart.getPeopleCount())
                .withFloor(apart.getFloor())
                .withSquareMeters(apart.getSquareMetres())
                .build();
    }
}
