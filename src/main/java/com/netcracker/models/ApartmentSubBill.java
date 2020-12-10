package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private Double debt;
    private Apartment apartment;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;

    public ApartmentSubBill(BigInteger subBill, Double balance, Double debt, List<ApartmentOperation> apartmentOperation,
                            List<DebtPaymentOperation> debtPaymentOperation, CommunalUtility communalUtility,Apartment apartment) {
        super(subBill, balance,communalUtility);
        this.debt = debt;
        this.apartmentOperation = apartmentOperation;
        this.debtPaymentOperation = debtPaymentOperation;
        this.apartment=apartment;
    }

    public ApartmentSubBill() {
    }
}
