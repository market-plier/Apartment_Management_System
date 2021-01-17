package com.netcracker.models;

public class CommunalUtilityWrapper {
    private final CommunalUtility communalUtility;

    public CommunalUtilityWrapper(CommunalUtility communalUtility) {
        this.communalUtility = communalUtility;
    }

    public String getName(){
        return communalUtility.getName();
    }

    public Double evaluate(Double base){
        return base * communalUtility.getCoefficient();
    }

}
