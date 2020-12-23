package com.netcracker.controllers;


import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.services.ManagerOperationSpendingService;
import com.netcracker.util.DateUtil;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping(value ="/manager-operation-spending/")
@Validated
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

        @RequestMapping(value = "/get-by-date/",method = RequestMethod.GET)
        public List<ManagerSpendingOperation> getAllManagerOperationSpending(@RequestParam @NotNull @NotBlank(message = "start date cant be empty") String start,
                                                                             @RequestParam @NotNull @NotBlank(message = "end date cant be empty") String end) throws ParseException, DaoAccessException {

            return managerOperationSpendingService.getAllManagerOperationByDate(DateUtil.provideDateFormat(start)
                    ,DateUtil.provideDateFormat(end));
        }

        @RequestMapping(value = "/get-by-id/{operationId}",method = RequestMethod.GET)
        public ManagerSpendingOperation getManagerOperationSpending(@PathVariable @Valid  @NotNull BigInteger operationId)
        {
            return managerOperationSpendingService.getManagerSpendingOperation(operationId);
        }
}
