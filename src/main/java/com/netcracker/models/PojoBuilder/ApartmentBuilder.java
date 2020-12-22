package com.netcracker.models.PojoBuilder;

import com.netcracker.models.Apartment;
import com.netcracker.models.Role;

import java.math.BigInteger;

public class ApartmentBuilder {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
    private BigInteger accountId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;


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

    public ApartmentBuilder withAccountId(BigInteger accountId) {
        this.accountId = accountId;
        return this;
    }

    public ApartmentBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ApartmentBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ApartmentBuilder withRole(Role role) {
        this.role = role;
        return this;
    }

    public ApartmentBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ApartmentBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public ApartmentBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Apartment build() {
        return new Apartment(accountId, firstName, lastName, email, password,
                phoneNumber, role, apartmentNumber, squareMetres, floor, peopleCount);
    }

}