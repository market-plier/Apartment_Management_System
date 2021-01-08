package com.netcracker.controllers;

import com.netcracker.models.ManagerSubBill;
import com.netcracker.services.ManagerSubBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Collection;

@RestController
public class ManagerSubBillsController {
    @Autowired
    ManagerSubBillService managerSubBillService;

    @RequestMapping("/getManagerSubBill")
    public ManagerSubBill getManagerSubBill(@PathVariable BigInteger id) {
        return managerSubBillService.getManagerSubBill(id);
    }

    @RequestMapping("/getAllManagerSubBills")
    public Collection<ManagerSubBill> getAllManagerSubBills() {
        return managerSubBillService.getAllManagerSubBills();
    }

    @RequestMapping(value = "/get-manager-sub-bill-info", method = RequestMethod.GET)
    public Collection<ManagerSubBill> getAllManagerSubBillsInfo()
    {
        return managerSubBillService.getAllManagerSubBillsWithoutOperations();
    }

}
