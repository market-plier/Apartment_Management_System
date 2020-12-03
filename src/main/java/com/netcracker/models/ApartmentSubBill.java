package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class ApartmentSubBill extends SubBill {
    private BigInteger subBillId;
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;
    private SubBill subBill;

    public ApartmentSubBill(BigInteger subBillId,Double debt, List<ApartmentOperation> apartmentOperation,
                            List<DebtPaymentOperation> debtPaymentOperation, SubBill subBill) {
      this.subBill=subBill;
        this.debt = debt;
        this.apartmentOperation = apartmentOperation;
        this.debtPaymentOperation = debtPaymentOperation;
        this.subBillId=subBillId;
    }

    public ApartmentSubBill(){}
}
