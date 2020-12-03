package com.netcracker.models.PojoBuilder;

import com.netcracker.models.ApartmentOperation;
import com.netcracker.models.ApartmentSubBill;

import java.math.BigInteger;
import java.util.Date;

public class ApartmentOperationBuilder {
    private BigInteger operationId;
    private Double sum;
    private Date createdAt;
    private ApartmentSubBill apartmentSubBill;

    public ApartmentOperationBuilder withOperationId(BigInteger operationId) {
        this.operationId = operationId;
        return this;
    }

    public ApartmentOperationBuilder withSum(Double sum) {
        this.sum = sum;
        return this;
    }

    public ApartmentOperationBuilder withCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public ApartmentOperationBuilder withApartmentSubBill(ApartmentSubBill apartmentSubBill) {
        this.apartmentSubBill = apartmentSubBill;
        return this;
    }

    public ApartmentOperation build() {
        return new ApartmentOperation(operationId, sum, createdAt, apartmentSubBill);
    }
}
