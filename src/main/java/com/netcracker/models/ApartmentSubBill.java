package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;

    public ApartmentSubBill(BigInteger subBill, Double balance, Double debt, List<ApartmentOperation> apartmentOperation,
                            List<DebtPaymentOperation> debtPaymentOperation) {
        super(subBill, balance);
        this.debt = debt;
        this.apartmentOperation = apartmentOperation;
        this.debtPaymentOperation = debtPaymentOperation;
    }

    public ApartmentSubBill() {
    }
}
