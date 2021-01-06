package com.netcracker.controllers.request.manager_spending;

import lombok.Data;

import javax.validation.constraints.*;
import java.math.BigInteger;

@Data
public class ManagerOperationUpdateRequest {

    @NotNull(message = "Sum cant be null")
    @Positive(message = "Sum must be positive value")
    private Double sum;

    @NotNull(message = "Id cant be null")
    @Positive(message = "Id must be positive value")
    private BigInteger operationId;

    @NotNull(message = "description cant be null")
    @NotBlank(message = "description cant be blank")
    @Size(min = 1, max = 1000, message = "size must be between 1 and 1000")
    private String description;



}
