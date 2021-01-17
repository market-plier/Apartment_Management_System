package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ApartmentOperation;
import com.netcracker.models.ApartmentSubBill;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ApartmentSubBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@PreAuthorize("hasAnyRole('ROLE_OWNER')")
public class ApartmentSubBillController {

    private final ApartmentSubBillService apartmentSubBillService;

    @Autowired
    public ApartmentSubBillController(ApartmentSubBillService apartmentSubBillService) {
        this.apartmentSubBillService = apartmentSubBillService;
    }

    @GetMapping("/apartment-sub-bills/{apartmentSubBillId}")
    public ApartmentSubBill getApartmentSubBill(@PathVariable BigInteger apartmentSubBillId) throws DaoAccessException, NullPointerException {
        return apartmentSubBillService.getApartmentSubBill(apartmentSubBillId);
    }

    @PostMapping("/apartment-sub-bill-transfer")
    public void createApartmentSubBillTransfer(@RequestBody List<String> params) throws DaoAccessException, NullPointerException {
        JwtAccount account = (JwtAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        apartmentSubBillService.createApartmentSubBillTransfer(account.getId(), params.get(0), params.get(1), Double.valueOf(params.get(2)));
    }

    @PostMapping("/apartment-sub-bill-payment")
    public void updateApartmentSubBillsByApartmentOperation(@RequestBody List<ApartmentOperation> apartmentOperations) throws DaoAccessException, NullPointerException {
        for (ApartmentOperation apartmentOperation : apartmentOperations) {
            if(apartmentOperation.getSum() > 0) {
                apartmentSubBillService.updateApartmentSubBillByApartmentOperation(apartmentOperation);
            }
        }
    }

    @GetMapping("/apartment-sub-bills")
    public List<ApartmentSubBill> getAllApartmentSubBillsOfCurrentAuthorizedUser() throws DaoAccessException, NullPointerException {
        JwtAccount account = (JwtAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return apartmentSubBillService.getAllApartmentSubBillsByAccountId(account.getId());
    }

}
