package com.netcracker.dao;

import com.netcracker.models.CalculationMethod;

import java.math.BigInteger;
import java.util.List;

public interface CalculationMethodDao {
    public List<CalculationMethod> getAllCalculationMethods();

    public CalculationMethod getCalculationMethodById(BigInteger id);

    void updateCalculationMethod(CalculationMethod calculationMethod);

    void createCalculationMethod(CalculationMethod calculationMethod);

    void deleteCalculationMethod(BigInteger id);
}
