package com.netcracker.models;

import lombok.Data;

@Data
public class Apartment {
    private Integer apartmentNumber;
    private Integer squareMetres;
    private Integer floor;
    private Integer peopleCount;
}
