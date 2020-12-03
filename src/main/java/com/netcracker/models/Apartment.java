package com.netcracker.models;

import lombok.Data;

@Data
public class Apartment extends Account {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
}
