package com.netcracker.controllers;


import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.services.ManagerOperationSpendingService;
import com.netcracker.util.DateUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.sql.Date;
import java.text.ParseException;
import java.util.List;

@Validated
@RestController(value = "manager-operation-spending")
@PreAuthorize("hasAnyRole('ROLE_MANAGER')")
public class ManagerSpendingOperationController {

        private final ManagerOperationSpendingService managerOperationSpendingService;

        public ManagerSpendingOperationController(ManagerOperationSpendingService managerOperationSpendingService) {
            this.managerOperationSpendingService = managerOperationSpendingService;
        }

        @RequestMapping(method = RequestMethod.POST)
        public void createManagerOperationSpending(@RequestBody @Valid ManagerSpendingOperation managerSpendingOperation)
        {
            managerOperationSpendingService.createManagerOperationSpending(managerSpendingOperation);
        }

        @RequestMapping(method = RequestMethod.PUT)
        public void updateManagerOperationSpending(@RequestBody @Valid ManagerSpendingOperation managerSpendingOperation)
        {
            managerOperationSpendingService.updateManagerOperation(managerSpendingOperation);
        }

        @RequestMapping(method = RequestMethod.GET)
        public List<ManagerSpendingOperation> getAllManagerOperationSpending(@RequestParam String start,@RequestParam String end) throws ParseException {

            return managerOperationSpendingService.getAllManagerOperationByDate(DateUtil.provideDateFormat(start)
                    ,DateUtil.provideDateFormat(end));
        }

        @RequestMapping(value = "{operationId}", method = RequestMethod.GET)
        public ManagerSpendingOperation getManagerOperationSpending(@PathVariable BigInteger operationId)
        {
            return managerOperationSpendingService.getManagerSpendingOperation(operationId);
        }
}
