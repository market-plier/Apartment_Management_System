package com.netcracker.dao;

import com.netcracker.models.CalculationMethod;

import java.util.List;

public interface CalculationMethodDao {
    public List<CalculationMethod> getAllCalculationMethods();

    public CalculationMethod getCalculationMethod();

    void updateCalculationMethod(CalculationMethod calculationMethod);

    void createCalculationMethod(CalculationMethod calculationMethod);

    void deleteCalculationMethod(CalculationMethod calculationMethod);
}
