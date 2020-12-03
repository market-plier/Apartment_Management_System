package com.netcracker.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigInteger;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApartmentOperation extends AbstractOperation {
    private ApartmentSubBill apartmentSubBill;

    public ApartmentOperation(){

    }
    public ApartmentOperation(BigInteger operationId, Double sum, Date createdAt, ApartmentSubBill apartmentSubBill){
        super(operationId, sum, createdAt);
        this.apartmentSubBill = apartmentSubBill;
    }
}
