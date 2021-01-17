package com.netcracker.models;

public class CommunalUtilityWrapper {
    private final CommunalUtility communalUtility;

    public CommunalUtilityWrapper(CommunalUtility calculationMethod) {
        this.communalUtility = calculationMethod;
    }

    public String getName(){
        return communalUtility.getName();
    }

    public Double evaluate(Double base){
        return base * communalUtility.getCoefficient();
    }

}
