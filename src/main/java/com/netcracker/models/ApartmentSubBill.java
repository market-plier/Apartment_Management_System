package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;

}
