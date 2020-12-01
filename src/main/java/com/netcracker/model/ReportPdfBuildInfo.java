package com.netcracker.model;

import lombok.Data;

import java.util.Date;

@Data
public class ReportPdfBuildInfo {
    protected Date startDate;
    protected Date endDate;
    protected CommunalUtility communalUtility;
}
