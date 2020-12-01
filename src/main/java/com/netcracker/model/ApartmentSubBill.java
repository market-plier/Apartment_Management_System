package com.netcracker.model;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentSubBill {
    private double debt;
    private List<ApartmentOperation> apartmnetOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;

}
