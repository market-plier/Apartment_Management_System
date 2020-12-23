package com.netcracker.controllers;

import com.netcracker.models.Manager;
import com.netcracker.models.ManagerBill;
import com.netcracker.services.ManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ManagerInfoController {
    @Autowired
    ManagerInfoService managerInfoService;

    @RequestMapping("/getManager")
    public Manager getManager() {
        return managerInfoService.getManager();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateManager")
    public Manager updateManager(@RequestBody Manager manager) {
        return managerInfoService.updateManager(manager);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateManagerBill")
    public ManagerBill updateManagerBill (@RequestBody ManagerBill managerBill) {
        return managerInfoService.updateManagerBill(managerBill);
    }

}
