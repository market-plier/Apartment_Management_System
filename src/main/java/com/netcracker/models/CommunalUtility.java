package com.netcracker.models;

import lombok.Data;
import java.math.BigInteger;
import java.util.Date;

@Data
public class CommunalUtility {
    public enum Status {ENABLE,DISABLE}
    public enum Duration {TEMPORARY,CONSTANT}
    private BigInteger communalUtilityId;
    private CalculationMethod calculationMethod;
    private String name;
    private Duration durationType;
    private Status status;
    private Date deadline;

    public CommunalUtility(BigInteger communalUtilityId, CalculationMethod calculationMethod,
                           String name, Duration durationType, Status status, Date deadline) {
        this.communalUtilityId = communalUtilityId;
        this.calculationMethod = calculationMethod;
        this.name = name;
        this.durationType = durationType;
        this.status = status;
        this.deadline = deadline;
    }
}
