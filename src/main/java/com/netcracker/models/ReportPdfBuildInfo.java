package com.netcracker.models;

import lombok.Data;

import java.util.Date;

@Data
public class ReportPdfBuildInfo {
    protected Date startDate;
    protected Date endDate;
    protected CommunalUtility communalUtility;
}
