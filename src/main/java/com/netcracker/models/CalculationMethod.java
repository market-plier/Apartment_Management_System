package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class CalculationMethod {
    private BigInteger calculationMethodId;
    @NotNull(message = "name can't be null")
    private String name;

    public CalculationMethod(BigInteger calculationMethodId, String name) {
        this.calculationMethodId = calculationMethodId;
        this.name = name;
    }

    public CalculationMethod() {
    }
}
