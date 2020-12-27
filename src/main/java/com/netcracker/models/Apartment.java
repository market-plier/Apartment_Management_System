package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class Apartment extends Account {
    @NotNull(message = "Apartment`s number can not be empty")
    @Min(value = 1, message = "Apartment`s number is not correct. Value is too low")
    @Max(value = 2147483, message = "Apartment`s number is not correct. Value is too high")
    private Integer apartmentNumber;

    @NotNull(message = "Apartment`s square metres can not be empty")
    @Min(value = 15, message = "Apartment`s square metres is not correct. Value is too low")
    @Max(value = 2147483, message = "Apartment`s square metres is not correct. Value is too high")
    private Integer squareMetres;

    @NotNull(message = "Apartment`s floor can not be empty")
    @Min(value = 0, message = "Apartment`s floor is not correct. Value is too low")
    @Max(value = 150, message = "Apartment`s floor is not correct.Value is too high")
    private Integer floor;

    @Min(value = 0, message = "People count is not correct. Value is too low")
    @Max(value = 2147483, message = "People count is not correct. Value is too high")
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
