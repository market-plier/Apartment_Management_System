package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import com.netcracker.services.ManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/managerInfo")
public class ManagerInfoController {
    @Autowired
    ManagerInfoService managerInfoService;

    @RequestMapping("/Manager")
    public Manager getManager() throws NullPointerException, DaoAccessException {
        return managerInfoService.getManager();
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/updateManager")
    public Manager updateManager(@RequestBody @Valid Manager manager)
            throws NullPointerException, DaoAccessException, IllegalArgumentException {
        return managerInfoService.updateManager(manager);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/updateManagerPassword")
    public Manager updateManagerPassword(@RequestBody @Valid Manager manager)
            throws NullPointerException, DaoAccessException {
        return managerInfoService.updateManagerPassword(manager);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/updateManagerBill")
    public ManagerBill updateManagerBill (@RequestBody @Valid ManagerBill managerBill)
            throws NullPointerException, DaoAccessException {
        return managerInfoService.updateManagerBill(managerBill);
    }

}
