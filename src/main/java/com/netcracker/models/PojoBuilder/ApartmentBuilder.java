package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Account;
import com.netcracker.models.Apartment;

public class ApartmentBuilder {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
    private Account account;

    public ApartmentBuilder withApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
        return this;
    }

    public ApartmentBuilder withSquareMeters(Integer squareMetres) {
        this.squareMetres = squareMetres;
        return this;
    }

    public ApartmentBuilder withFloor(Integer floor) {
        this.floor = floor;
        return this;
    }

    public ApartmentBuilder withPeopleCount(Integer peopleCount) {
        this.peopleCount = peopleCount;
        return this;
    }

    public ApartmentBuilder withAccount(Account account) {
        this.account = account;
        return this;
    }

    public Apartment build() {
        return new Apartment(apartmentNumber, squareMetres, floor, peopleCount,account);
    }

}