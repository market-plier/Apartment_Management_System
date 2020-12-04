package com.netcracker.dao;

import com.netcracker.models.CommunalUtility;

import java.math.BigInteger;
import java.util.List;

public interface CommunalUtilityDao {
    public List<CommunalUtility> getAllCommunalUtilities();

    public CommunalUtility getCommunalUtilityById(BigInteger id);

    public void updateCommunalUtility(CommunalUtility communalUtility);

    public void createCommunalUtility(CommunalUtility communalUtility);
}
