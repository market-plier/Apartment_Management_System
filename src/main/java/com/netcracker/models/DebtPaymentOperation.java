package com.netcracker.models;

import lombok.Data;

@Data
public class DebtPaymentOperation extends AbstractOperation {
    private ApartmentSubBill apartmentSubBill;
    private ManagerSubBill managerSubBill;
}
