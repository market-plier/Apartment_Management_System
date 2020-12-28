package com.netcracker.controllers;


import com.netcracker.controllers.request.manager_spending.ManagerOperationCreateRequest;
import com.netcracker.controllers.request.manager_spending.ManagerOperationUpdateRequest;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.PojoBuilder.ManagerSpendingOperationBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import com.netcracker.services.ManagerOperationSpendingService;
import com.netcracker.util.DateUtil;


import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
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
        public void createManagerOperationSpending(@RequestBody @Valid ManagerOperationCreateRequest managerSpendingOperation) throws DaoAccessException
        {
            managerOperationSpendingService.createManagerOperationSpending(new ManagerSpendingOperationBuilder()
                    .withManagerSubBill(new ManagerSubBillBuilder()
                            .withSubBillId(managerSpendingOperation.getSubBillId())
                            .build())
                    .withDescription(managerSpendingOperation.getDescription())
                    .withSum(managerSpendingOperation.getSum())
                    .build());
        }

        @RequestMapping(method = RequestMethod.PUT)
        public void updateManagerOperationSpending(@RequestBody @Valid ManagerOperationUpdateRequest managerSpendingOperation) throws DaoAccessException
        {
            managerOperationSpendingService.updateManagerOperation(new ManagerSpendingOperationBuilder()
                    .withSum(managerSpendingOperation.getSum())
                    .withDescription(managerSpendingOperation.getDescription())
                    .withOperationId(new BigInteger(managerSpendingOperation.getManagerOperationId()))
                    .build());
        }

        @RequestMapping(value = "/get-by-date/",method = RequestMethod.GET)
        public List<ManagerSpendingOperation> getAllManagerOperationSpending(@RequestParam
                                                                             @NotNull
                                                                             @NotBlank(message = "start date cant be empty")
                                                                             String start,
                                                                             @RequestParam
                                                                             @NotNull
                                                                             @NotBlank(message = "end date cant be empty")
                                                                             String end) throws ParseException, DaoAccessException {

            return managerOperationSpendingService.getAllManagerOperationByDate(DateUtil.provideDateFormat(start)
                    ,DateUtil.provideDateFormat(end));
        }

        @RequestMapping(value = "/get-by-id/{operationId}",method = RequestMethod.GET)
        public ManagerSpendingOperation getManagerOperationSpending(@PathVariable @Valid
                                                                    @NotNull(message = "cant be null")
                                                                    @Positive(message = "must be positive value")
                                                                    BigInteger operationId)
        {
            return managerOperationSpendingService.getManagerSpendingOperation(operationId);
        }
}
