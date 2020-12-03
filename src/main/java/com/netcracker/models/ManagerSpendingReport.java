package com.netcracker.models;

import lombok.Data;

import java.util.List;

@Data
public class ManagerSpendingReport extends ReportPdfBuildInfo{
    private ManagerSubBill managerSubBill;
    private List<ManagerSpendingOperation> managerSpendingOperations;
}
