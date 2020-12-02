package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentSubBill {
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;

}
