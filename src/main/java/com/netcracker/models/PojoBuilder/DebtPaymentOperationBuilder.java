package com.netcracker.models.PojoBuilder;

import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.DebtPaymentOperation;
import com.netcracker.models.ManagerSubBill;

import java.math.BigInteger;
import java.util.Date;

public class DebtPaymentOperationBuilder {
    private BigInteger debtPaymentOperationId;
    private Double sum;
    private Date createdAt;
    private ApartmentSubBill apartmentSubBill;
    private ManagerSubBill managerSubBill;

    public DebtPaymentOperationBuilder withOperationId(BigInteger debtPaymentOperationId) {
        this.debtPaymentOperationId = debtPaymentOperationId;
        return this;
    }

    public DebtPaymentOperationBuilder withSum(Double sum) {
        this.sum = sum;
        return this;
    }

    public DebtPaymentOperationBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public DebtPaymentOperationBuilder withApartmentSubBill(ApartmentSubBill apartmentSubBill) {
        this.apartmentSubBill = apartmentSubBill;
        return this;
    }

    public DebtPaymentOperationBuilder withManagerSubBill(ManagerSubBill managerSubBill) {
        this.managerSubBill = managerSubBill;
        return this;
    }

    public DebtPaymentOperation build() {
        return new DebtPaymentOperation(debtPaymentOperationId, sum, createdAt, apartmentSubBill, managerSubBill);
    }
}
