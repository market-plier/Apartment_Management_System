package com.netcracker.models.PojoBuilder;

import com.netcracker.models.ApartmentOperation;
import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.SubBill;

import java.math.BigInteger;
import java.util.List;

public class ApartmentSubBillBuilder {
    private BigInteger subBillId;
    private Double debt;
    private List<ApartmentOperation> apartmentOperation;
    private List<DebtPaymentOperation> debtPaymentOperation;
    private SubBill subBill;

    public ApartmentSubBillBuilder withSubBillId(BigInteger subBillId) {
        this.subBillId=subBillId;
        return this;
    }

    public ApartmentSubBillBuilder withDept(Double debt) {
        this.debt = debt;
        return this;
    }

    public ApartmentSubBillBuilder withApartmentOperation(List<ApartmentOperation> apartmentOperation) {
        this.apartmentOperation = apartmentOperation;
        return this;
    }

    public ApartmentSubBillBuilder withDebtPaymentOperation(List<DebtPaymentOperation> debtPaymentOperation) {
        this.debtPaymentOperation = debtPaymentOperation;
        return this;
    }

    public ApartmentSubBillBuilder withSubBill(SubBill subBill) {
        this.subBill = subBill;
        return this;
    }

    public ApartmentSubBill build() {
        return new ApartmentSubBill(subBillId,debt, apartmentOperation, debtPaymentOperation, subBill);
    }
}


