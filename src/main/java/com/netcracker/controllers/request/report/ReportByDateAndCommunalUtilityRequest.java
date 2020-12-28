package com.netcracker.controllers.request.report;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
import java.util.Set;

@Data
public class ReportByDateAndCommunalUtilityRequest {
    @NotNull(message = "start date cant be null")
    @NotBlank(message = "start date cant be empty")
    private String start;


    @NotNull(message = "end date cant be null")
    @NotBlank(message = "end date cant be empty")
    private String end;

    @NotNull
    private Set<@Positive(message = "must be positive value") BigInteger> communalUtility;




}
