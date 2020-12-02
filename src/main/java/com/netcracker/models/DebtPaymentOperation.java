package com.netcracker.models;

import lombok.Data;

@Data
public class DebtPaymentOperation {
    private ApartmentSubBill apartmentSubBill;
    private ManagerSubBill managerSubBill;
}
