package com.netcracker.models;

public class CalculationMethodWrapper {
    private final CalculationMethod calculationMethod;

    public CalculationMethodWrapper(CalculationMethod calculationMethod) {
        this.calculationMethod = calculationMethod;
    }

    public String getName(){
        return calculationMethod.getName();
    }

    public int evaluate(int base,int coefficient){
        return base*coefficient;
    }

}
