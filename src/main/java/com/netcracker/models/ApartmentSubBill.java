package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private Double debt;
    private Apartment apartment;
    private List<ApartmentOperation> apartmentOperations;
    private List<DebtPaymentOperation> debtPaymentOperations;

    public ApartmentSubBill(BigInteger subBill, Double balance, Double debt, List<ApartmentOperation> apartmentOperation,
                            List<DebtPaymentOperation> debtPaymentOperation, CommunalUtility communalUtility,Apartment apartment) {
        super(subBill, balance,communalUtility);
        this.debt = debt;
        this.apartmentOperations = apartmentOperation;
        this.debtPaymentOperations = debtPaymentOperation;
        this.apartment=apartment;
    }

    public ApartmentSubBill() {
    }
}
