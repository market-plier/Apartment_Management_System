package com.netcracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigInteger;
@EqualsAndHashCode
@Data
@Deprecated
public class CalculationMethod {
    private BigInteger calculationMethodId;
    @NotNull(message = "name can't be null")
    @NotBlank(message = "name can't be blank")
    private String name;
    @NotNull(message = "coefficient can't be null")
    @Positive(message = "coefficient should be positive")
    private Double coefficient;

    public CalculationMethod(BigInteger calculationMethodId, String name, Double coefficient) {
        this.calculationMethodId = calculationMethodId;
        this.name = name;
        this.coefficient = coefficient;
    }
    public CalculationMethod(BigInteger calculationMethodId, String name) {
        this.calculationMethodId = calculationMethodId;
        this.name = name;
    }

    public CalculationMethod() {
    }
}
