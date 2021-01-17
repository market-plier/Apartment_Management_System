package com.netcracker.controllers;

import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import com.netcracker.models.PojoBuilder.ManagerBuilder;
import com.netcracker.secutity.jwt.JwtAccount;
import com.netcracker.services.ManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

@RestController
@RequestMapping("/manager-info")
public class ManagerInfoController {
    @Autowired
    ManagerInfoService managerInfoService;

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping("/manager")
    public Manager getManager() throws DaoAccessException {
        Manager managerDTO = managerInfoService.getManager();
        return getManagerDTO(managerDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/update-manager")
    public Manager updateManager(@RequestBody @Valid Manager manager)
            throws DaoAccessException, IllegalArgumentException {
        Manager managerDTO = managerInfoService.updateManager(manager);
        return getManagerDTO(managerDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/update-manager-password")
    public Manager updateManagerPassword(@AuthenticationPrincipal JwtAccount account, @RequestBody @Valid Manager manager)
            throws DaoAccessException {
        if (manager.getPassword() == null || manager.getPassword().length() > 3900 || manager.getPassword().length() < 8) {
            throw new ValidationException("Password length is not correct");
        }
        Manager managerDTO = managerInfoService.updateManagerPassword(account, manager);
        return getManagerDTO(managerDTO);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/update-manager-bill")
    public ManagerBill updateManagerBill(@RequestBody @Valid ManagerBill managerBill)
            throws DaoAccessException {
        return managerInfoService.updateManagerBill(managerBill);
    }

    private Manager getManagerDTO(Manager manager) {
        return new ManagerBuilder()
                .withAccountId(manager.getAccountId())
                .withFirstName(manager.getFirstName())
                .withLastName(manager.getLastName())
                .withPhoneNumber(manager.getPhoneNumber())
                .withManagerBill(manager.getManagerBill())
                .withEmail(manager.getEmail())
                .withRole(manager.getRole())
                .build();
    }
}
