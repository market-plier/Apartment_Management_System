package com.netcracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class DebtPaymentOperation extends AbstractOperation {
    private ApartmentSubBill apartmentSubBill;
    private ManagerSubBill managerSubBill;

    public DebtPaymentOperation(BigInteger debtPaymentOperation, Double sum, Date createdAt, ApartmentSubBill apartmentSubBill, ManagerSubBill managerSubBill){
        super(debtPaymentOperation, sum, createdAt);
        this.apartmentSubBill = apartmentSubBill;
        this.managerSubBill = managerSubBill;
    }
}
