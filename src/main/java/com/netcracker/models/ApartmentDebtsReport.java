package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ApartmentDebtsReport extends ReportPdfBuildInfo{
    private ApartmentSubBill apartmentSubBill;
    private List<DebtPaymentOperation> debtPaymentOperations;
}
