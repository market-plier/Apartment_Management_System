package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class ApartmentRequestToManager {
    private BigInteger apartmentId;
    private String text;
}
