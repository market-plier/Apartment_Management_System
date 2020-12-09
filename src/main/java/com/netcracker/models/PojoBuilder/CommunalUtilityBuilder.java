package com.netcracker.models.PojoBuilder;

import com.netcracker.models.CalculationMethod;
import com.netcracker.models.CommunalUtility;

import java.math.BigInteger;
import java.util.Date;

public class CommunalUtilityBuilder {
    private BigInteger communalUtilityId;
    private CalculationMethod calculationMethod;
    private String name;
    private CommunalUtility.Duration durationType;
    private CommunalUtility.Status status;
    private Date deadline;

    public CommunalUtilityBuilder withCommunalUtilityId(BigInteger communalUtilityId){
        this.communalUtilityId = communalUtilityId;
        return this;
    }

    public CommunalUtilityBuilder withCalculationMethod(CalculationMethod calculationMethod){
        this.calculationMethod = calculationMethod;
        return this;
    }

    public CommunalUtilityBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public CommunalUtilityBuilder withDurationType(CommunalUtility.Duration duration){
        durationType=duration;
        return this;
    }

    public CommunalUtilityBuilder withStatus(CommunalUtility.Status status){
        this.status=status;
        return this;
    }

    public CommunalUtilityBuilder withDeadline(Date date){
        deadline=date;
        return this;
    }

    public CommunalUtility build(){
        return new CommunalUtility(communalUtilityId,calculationMethod,name,durationType,status,deadline);
    }
}
