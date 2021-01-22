package com.netcracker.controllers;


import com.netcracker.models.Apartment;
import com.netcracker.models.ApartmentOperation;
import com.netcracker.services.ApartmentInfoService;
import com.netcracker.services.ApartmentOperationService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/apartment-operation")
@Validated
public class ApartmentOperationController {

    private final ApartmentOperationService apartmentOperationService;

    private final ApartmentInfoService apartmentInfoService;


    @Autowired
    public ApartmentOperationController(ApartmentOperationService apartmentOperationService, ApartmentInfoService apartmentInfoService) {
        this.apartmentOperationService = apartmentOperationService;
        this.apartmentInfoService = apartmentInfoService;
    }

    @GetMapping("/{number}")
        public List<ApartmentOperation>  getAllApartmentOperationByApartmentId(@PathVariable("number") @Valid @Positive(message = "Apartment number must be more than 0") Integer number)
    {
        Apartment apartment = apartmentInfoService.getApartmentByApartmentNumber(number);

        return apartmentOperationService.getAllApartmentOperationsByApartmentId(apartment.getAccountId());
    }
}
