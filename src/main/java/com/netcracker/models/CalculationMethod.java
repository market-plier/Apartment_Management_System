package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class CalculationMethod {
    private BigInteger calculationMethodId;
    private String name;
}