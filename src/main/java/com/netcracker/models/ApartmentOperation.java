package com.netcracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApartmentOperation extends AbstractOperation {
    private ApartmentSubBill apartmentSubBill;

    public ApartmentOperation(BigInteger apartmentOperationId, Double sum, Date createdAt, ApartmentSubBill apartmentSubBill){
        super(apartmentOperationId, sum, createdAt);
        this.apartmentSubBill = apartmentSubBill;
    }
}
