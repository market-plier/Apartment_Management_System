package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;
    private SubBill subBill;

    public ApartmentSubBill(Double debt, List<ApartmentOperation> apartmentOperation,
                            List<DebtPaymentOperation> debtPaymentOperation, SubBill subBill) {
        this.debt = debt;
        this.apartmentOperation = apartmentOperation;
        this.debtPaymentOperation = debtPaymentOperation;
        this.subBill=subBill;
    }

    public ApartmentSubBill(){}
}
