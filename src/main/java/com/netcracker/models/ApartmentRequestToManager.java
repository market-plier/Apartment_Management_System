package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigInteger;

@Data
public class ApartmentRequestToManager {
    @Min(value = 0)
    private BigInteger apartmentId;
    @NotBlank(message = "Request text can not be empty")
    private String text;

    public ApartmentRequestToManager(BigInteger apartmentId, String text) {
        this.apartmentId = apartmentId;
        this.text = text;
    }

    public ApartmentRequestToManager() {
    }
}
