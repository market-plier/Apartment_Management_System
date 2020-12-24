package com.netcracker.models;

import java.math.BigDecimal;

public class CalculationMethodWrapper {
    private final CalculationMethod calculationMethod;

    public CalculationMethodWrapper(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getName(){
        return calculationMethod.getName();
    }

    public Double evaluate(Double base){
        return base * calculationMethod.getCoefficient();
    }

}
