package com.netcracker.controllers;


import com.netcracker.models.Apartment;
import com.netcracker.models.ApartmentOperation;
import com.netcracker.services.ApartmentInfoService;
import com.netcracker.services.ApartmentOperationService;
import com.netcracker.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.text.ParseException;
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

    @GetMapping("/by-date-and-apart-number/")
    public List<ApartmentOperation> getAllApartmentOperationByApartmentNumberAndDate(@Valid
                                                                                     @RequestParam
                                                                                     @NotNull(message = "start date cant be null")
                                                                                     @NotEmpty(message = "start date cant be empty") String start,
                                                                                     @RequestParam
                                                                                     @NotNull(message = "end date cant be null")
                                                                                     @NotEmpty(message = "end date cant be empty") String end,
                                                                                     @Positive(message = "Apartment number must be more than 0")
                                                                                     Integer number) throws ParseException {

        return apartmentOperationService.getApartmentOperationsByDateRangeAndApartmentNumber(number, DateUtil.provideDateFormat(start), DateUtil.provideDateFormat(end));
    }

    @GetMapping("/apartment-sub-bill/{apartmentSubBillId}")
    public List<ApartmentOperation> getAllApartmentOperationByApartmentSubBillId(@PathVariable BigInteger apartmentSubBillId){
        return apartmentOperationService.getAllApartmentOperationsBySubBillId(apartmentSubBillId);
    }
}
