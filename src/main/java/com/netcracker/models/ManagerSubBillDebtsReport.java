package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSubBillDebtsReport extends ReportPdfBuildInfo{
    private ManagerSubBill managerSubBull;
    private List<ManagerOperationSpending> managerOperationSpendings;
}
