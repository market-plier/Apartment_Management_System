package com.netcracker.services;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import com.netcracker.models.CommunalUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
@Slf4j
public class CommunalUtilityService {
    private final CommunalUtilityDao communalUtilityDao;
    private final CalculationMethodDao calculationMethodDao;
    private final NotificationService notificationService;

    @Autowired
    public CommunalUtilityService(CommunalUtilityDao communalUtilityDao, CalculationMethodDao calculationMethodDao, NotificationService notificationService) {
        this.communalUtilityDao = communalUtilityDao;
        this.calculationMethodDao = calculationMethodDao;
        this.notificationService = notificationService;
    }

    public List<CommunalUtility> getAllCommunalUtilities(CommunalUtility.Status status) throws DaoAccessException, NullPointerException {
        try {
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
        } catch (NullPointerException e) {
            log.error("CommunalUtilityService method getAllCommunalUtilities(): " + e.getMessage(), e);
            throw e;
        }
    }

    public List<CommunalUtility> getAllCommunalUtilities() throws DaoAccessException, NullPointerException {
        return getAllCommunalUtilities(null);
    }

    public CommunalUtility getCommunalUtilityById(BigInteger id) {
        try {
            CommunalUtility utility = communalUtilityDao.
                    getCommunalUtilityByIdWithCalculationMethod(id);
            if (utility == null)
                utility = communalUtilityDao.getCommunalUtilityById(id);
            return utility;
        } catch (NullPointerException e) {
            log.error("CommunalUtilityService method getCommunalUtilityById(): " + e.getMessage(), e);
            throw e;
        }
    }

    public CommunalUtility createCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException, NullPointerException, IOException, MessagingException {
        try {
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
                throw new IllegalArgumentException("No such calculation method as requested");

            communalUtilityDao.createCommunalUtilityWithRef(communalUtility);
            comUtil = communalUtilityDao.getUniqueCommunalUtility(communalUtility);

            comUtil.setCalculationMethod(calculationMethodDao
                    .getCalculationMethodByCommunalUtilityId(comUtil.getCommunalUtilityId()));
            if (comUtil.getDurationType() == CommunalUtility.Duration.Temporary) {
                notificationService.sendTempCommunalUtilityNotificationToAllApartments(communalUtility);
            }
            //TODO check for duration, then send if temporary
            return comUtil;
        } catch (NullPointerException | IOException | MessagingException e) {
            log.error("CommunalUtilityService method createCommunalUtility(): " + e.getMessage(), e);
            throw e;
        }
    }

    public CommunalUtility updateCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException, NullPointerException {
        try {
            CommunalUtility comUtil = communalUtilityDao.
                    getCommunalUtilityById(communalUtility.getCommunalUtilityId());
            if (comUtil == null) {
                //TODO
                throw new IllegalArgumentException("Nothing to update with presented ID");
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
        } catch (NullPointerException e) {
            log.error("CommunalUtilityService method updateCommunalUtility(): " + e.getMessage(), e);
            throw e;
        }
    }

    private boolean validateCalculationMethodId(CalculationMethod calculationMethod) throws DaoAccessException, NullPointerException {
        try {
            return calculationMethodDao.
                    getCalculationMethodById(calculationMethod.getCalculationMethodId()) != null;
        } catch (NullPointerException e) {
            log.error("CommunalUtilityService method validateCalculationMethodId(): " + e.getMessage(), e);
            throw e;
        }
    }
}
