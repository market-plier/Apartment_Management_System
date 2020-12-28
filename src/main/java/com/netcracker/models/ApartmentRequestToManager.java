package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class ApartmentRequestToManager {
    @Positive
    private BigInteger apartmentId;
    private String text;

    public ApartmentRequestToManager(BigInteger apartmentId, String text) {
        this.apartmentId = apartmentId;
        this.text = text;
    }

    public ApartmentRequestToManager() {
    }
}
