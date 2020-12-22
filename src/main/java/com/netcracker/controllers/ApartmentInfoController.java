package com.netcracker.controllers;

import com.netcracker.models.Apartment;
import com.netcracker.services.ApartmentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class ApartmentInfoController {
    @Autowired
    private ApartmentInfoService apartmentInfoService;

    @RequestMapping(method = RequestMethod.POST, value = "/createApartment")
    public Apartment createApartment(@RequestBody Apartment apartment) {
        return apartmentInfoService.createApartment(apartment);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updateApartment")
    public Apartment updateApartment(@RequestBody Apartment apartment) {
        return apartmentInfoService.updateApartment(apartment);
    }

    @RequestMapping("/getApartment/{id}")
    public Apartment getApartment(@PathVariable BigInteger id) {
        return apartmentInfoService.getApartmentById(id);
    }

    @RequestMapping("/getAllApartments")
    public List<Apartment> getAllApartments() {
        return apartmentInfoService.getAllApartments();
    }

}
