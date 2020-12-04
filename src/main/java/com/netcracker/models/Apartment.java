package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Apartment extends Account {
    private BigInteger apartmentId;
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
    Account account;

    public Apartment(BigInteger apartmentId, Integer apartmentNumber, Integer squareMetres, Integer floor, Integer peopleCount, Account account) {
        this.apartmentId = apartmentId;
        this.apartmentNumber = apartmentNumber;
        this.squareMetres = squareMetres;
        this.floor = floor;
        this.peopleCount = peopleCount;
        this.account = account;
    }

    public Apartment() {
    }
}
