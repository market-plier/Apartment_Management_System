package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ApartmentRequestToManager {
    private BigInteger apartmentId;
    private String text;

    public ApartmentRequestToManager(BigInteger apartmentId, String text) {
        this.apartmentId = apartmentId;
        this.text = text;
    }

    public ApartmentRequestToManager() {
    }
}
