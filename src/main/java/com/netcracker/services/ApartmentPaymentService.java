package com.netcracker.services;

import org.springframework.stereotype.Service;

import java.sql.Date;


@Service
public class ApartmentPaymentService {
    public boolean isCardValid(String cardNumber, Date expDate, String cvv) {
        return cardNumber.matches("^[0-9]{16}$") &&
                expDate.after(new Date(System.currentTimeMillis())) &&
                cvv.matches("^[0-9]{3,4}$");
    }
}
