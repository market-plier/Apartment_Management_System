package com.netcracker.services;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import com.netcracker.models.CommunalUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CommunalUtilityService {
    private final CommunalUtilityDao communalUtilityDao;
    private final CalculationMethodDao calculationMethodDao;

    @Autowired
    public CommunalUtilityService(CommunalUtilityDao communalUtilityDao, CalculationMethodDao calculationMethodDao) {
        this.communalUtilityDao = communalUtilityDao;
        this.calculationMethodDao = calculationMethodDao;
    }

    public List<CommunalUtility> getAllCommunalUtilities(CommunalUtility.Status status) {
        List<CommunalUtility> utilities;
        if (status != null) {
            utilities = communalUtilityDao.getAllCommunalUtilitiesFilterByStatus(status);
        } else {
            utilities = communalUtilityDao.getAllCommunalUtilities();
        }
        for (CommunalUtility utility : utilities
        ) {
            CalculationMethod calc = calculationMethodDao.
                    getCalculationMethodByCommunalUtilityId(utility.getCommunalUtilityId());
            if (calc != null)
                utility.setCalculationMethod(calc);
        }
        return utilities;
    }

    public CommunalUtility getCommunalUtilityById(BigInteger id) {
        CommunalUtility utility = communalUtilityDao.
                getCommunalUtilityByIdWithCalculationMethod(id);
        if (utility == null)
            utility = communalUtilityDao.getCommunalUtilityById(id);
        return utility;
    }

    public CommunalUtility createCommunalUtility(CommunalUtility communalUtility) {
        CommunalUtility comUtil = communalUtilityDao.
                getUniqueCommunalUtility(communalUtility);
        if (comUtil != null)
            return comUtil;
        //TODO
        //add exception when already created

        if (communalUtility.getCalculationMethod() == null) {
            communalUtility.setStatus(CommunalUtility.Status.Disabled);
            communalUtilityDao.createCommunalUtility(communalUtility);
            return communalUtilityDao.getUniqueCommunalUtility(communalUtility);
        } else if (!validateCalculationMethodId(communalUtility.getCalculationMethod()))
            throw new DaoAccessException("No such calculation method as requested");

        communalUtilityDao.createCommunalUtilityWithRef(communalUtility);
        comUtil = communalUtilityDao.getUniqueCommunalUtility(communalUtility);
        comUtil.setCalculationMethod(calculationMethodDao
                .getCalculationMethodByCommunalUtilityId(comUtil.getCommunalUtilityId()));
        return comUtil;
    }

    public CommunalUtility updateCommunalUtility(CommunalUtility communalUtility) {
        CommunalUtility comUtil = communalUtilityDao.
                getCommunalUtilityById(communalUtility.getCommunalUtilityId());
        if (comUtil == null) {
            //TODO
            throw new DaoAccessException("Nothing to update");
        }
        if (comUtil.getCalculationMethod() == null && communalUtility.
                getCalculationMethod() == null) {
            communalUtility.setStatus(CommunalUtility.Status.Disabled);
            communalUtilityDao.updateCommunalUtility(communalUtility);
            return communalUtilityDao.
                    getCommunalUtilityById(communalUtility.getCommunalUtilityId());

        }
        communalUtilityDao.updateCommunalUtility(communalUtility);
        return communalUtilityDao.getCommunalUtilityByIdWithCalculationMethod(communalUtility.getCommunalUtilityId());
    }

    private boolean validateCalculationMethodId(CalculationMethod calculationMethod) {
        return calculationMethodDao.
                getCalculationMethodById(calculationMethod.getCalculationMethodId()) != null;
    }

}
