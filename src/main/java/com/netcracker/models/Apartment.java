package com.netcracker.models;

import lombok.Data;

@Data
public class Apartment extends Account {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
    private Account account;

    public Apartment(Integer apartmentNumber, Integer squareMetres,
                     Integer floor, Integer peopleCount, Account account) {
        this.apartmentNumber = apartmentNumber;
        this.squareMetres = squareMetres;
        this.floor = floor;
        this.peopleCount = peopleCount;
        this.account = account;
    }

    public Apartment() {
    }
}
