package com.netcracker.model;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentDebtsReport {
    private ApartmentSubBill apartmentSubBill;
    private List<DebtPaymentOperation> debtPaymentOperations;
}
