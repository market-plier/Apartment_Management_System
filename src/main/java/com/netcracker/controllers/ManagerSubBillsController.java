package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.netcracker.models.ManagerSubBill;
import com.netcracker.services.ManagerSubBillService;
import com.netcracker.util.JsonFormatMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;

@RestController
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
@RequestMapping("/manager-sub-bill")
public class ManagerSubBillsController {

    @Autowired
    ManagerSubBillService managerSubBillService;

    @GetMapping("/get/{managerSubBillId}")
    public ResponseEntity<ManagerSubBill> getManagerSubBill(@PathVariable @NotNull BigInteger managerSubBillId) throws DaoAccessException {
        ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBill(managerSubBillId);
        return new ResponseEntity<>(managerSubBill, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<Collection<ManagerSubBill>> getAllManagerSubBills() {
        Collection<ManagerSubBill> managerSubBills = managerSubBillService.getAllManagerSubBills();
        return new ResponseEntity<>(managerSubBills, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/get-by-communal/{communalUtilityId}", method = RequestMethod.GET)
    public ResponseEntity<ManagerSubBill> getManagerSubBillByCommunalUtilityId(@PathVariable @NotNull BigInteger communalUtilityId) {
        ManagerSubBill managerSubBill = managerSubBillService.getManagerSubBillByCommunalUtilityId(communalUtilityId);
        return new ResponseEntity<>(managerSubBill, HttpStatus.FOUND);
    }

    @RequestMapping(value = "/get-manager-sub-bill-info", method = RequestMethod.GET)
    public Collection<ManagerSubBill> getAllManagerSubBillsInfo() {
        return managerSubBillService.getAllManagerSubBillsWithoutOperations();
    }


    @RequestMapping(value = "/get-manager-sub-bill-debt", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Double> getAllManagerSubBillsDebt() throws JsonProcessingException {
        return JsonFormatMap.transformMapToJson(managerSubBillService.getAllSubBillDebt());
    }

}
