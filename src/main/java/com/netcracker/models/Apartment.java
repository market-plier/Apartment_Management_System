package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class Apartment extends Account {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;

    public Apartment(BigInteger accountId, String firstName,
                     String lastName, String email, String password, String phoneNumber, Role role, Integer apartmentNumber, Integer squareMetres,
                     Integer floor, Integer peopleCount) {
        super(accountId, firstName, lastName, email, password, phoneNumber, role);
        this.apartmentNumber = apartmentNumber;
        this.squareMetres = squareMetres;
        this.floor = floor;
        this.peopleCount = peopleCount;
    }

    public Apartment() {
    }
}
