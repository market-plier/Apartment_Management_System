package com.netcracker.controllers;


import com.netcracker.controllers.request.manager_spending.ManagerOperationCreateRequest;
import com.netcracker.controllers.request.manager_spending.ManagerOperationUpdateRequest;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.ManagerSpendingOperation;
import com.netcracker.models.PojoBuilder.ManagerSpendingOperationBuilder;
import com.netcracker.models.PojoBuilder.ManagerSubBillBuilder;
import com.netcracker.services.ManagerOperationSpendingService;
import com.netcracker.util.DateUtil;


import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value ="/manager-operation-spending/")

@Validated
public class ManagerSpendingOperationController {

        private final ManagerOperationSpendingService managerOperationSpendingService;

        public ManagerSpendingOperationController(ManagerOperationSpendingService managerOperationSpendingService) {
            this.managerOperationSpendingService = managerOperationSpendingService;
        }

        @RequestMapping(method = RequestMethod.POST)
        public ResponseEntity createManagerOperationSpending(@RequestBody @Valid ManagerOperationCreateRequest managerSpendingOperation) throws DaoAccessException
        {

            managerOperationSpendingService.createManagerOperationSpending(new ManagerSpendingOperationBuilder()
                    .withManagerSubBill(new ManagerSubBillBuilder()
                            .withSubBillId(managerSpendingOperation.getSubBillId())
                            .build())
                    .withDescription(managerSpendingOperation.getDescription())
                    .withSum(managerSpendingOperation.getSum())
                    .build());
            return ResponseEntity.ok().build();
        }

        @RequestMapping(method = RequestMethod.PUT)
        public ResponseEntity updateManagerOperationSpending(@RequestBody @Valid ManagerOperationUpdateRequest managerSpendingOperation) throws DaoAccessException
        {
            managerOperationSpendingService.updateManagerOperation(new ManagerSpendingOperationBuilder()
                    .withSum(managerSpendingOperation.getSum())
                    .withDescription(managerSpendingOperation.getDescription())
                    .withOperationId(managerSpendingOperation.getOperationId())
                        .withManagerSubBill(new ManagerSubBillBuilder().withSubBillId(managerSpendingOperation.getManagerSubBillId()).build())
                    .build());
            return ResponseEntity.ok().build();
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


    @RequestMapping(value = "/get-by-date-comm-util/",method = RequestMethod.GET)
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingByDateAndCommunalUtility(@RequestParam
                                                                                                 @NotNull(message = "start date cant be null")
                                                                                                 @NotEmpty(message = "start date cant be empty") String start,
                                                                                                 @RequestParam
                                                                                                 @NotNull(message = "end date cant be null")
                                                                                                 @NotEmpty(message = "end date cant be empty") String end,
                                                                                                 @RequestParam(name = "communalUtilityId")
                                                                                                 @NotNull(message = "Communal Utility date cant be null")
                                                                                                 Set<@Min(value = 0, message = "must be positive value")
                                                                                                 @Digits(integer = 10, fraction = 0,message = "Id must be integer value")String> communalUtility) throws ParseException, DaoAccessException {

        return managerOperationSpendingService.getAllManagerOperationByDateAndCommunalUtility(DateUtil.provideDateFormat(start),
                DateUtil.provideDateFormat(end),communalUtility.stream().map(BigInteger::new).collect(Collectors.toSet()));

    }

    @RequestMapping(value = "/get-by-comm-util/",method = RequestMethod.GET)
    public List<ManagerSpendingOperation> getAllManagerOperationSpendingByCommunalUtility(@RequestParam(name = "communalUtilityId")
                                                                                          @NotNull(message = "Communal Utility date cant be null")
                                                                                          Set<@Min(value = 0, message = "must be positive value")
                                                                                          @Digits(integer = 10, fraction = 0,message = "Id must be integer value")String> communalUtility) throws DaoAccessException {

        return managerOperationSpendingService
                .getAllManagerOperationByCommunalUtility(communalUtility.stream().map(BigInteger::new).collect(Collectors.toSet()));
    }



}
