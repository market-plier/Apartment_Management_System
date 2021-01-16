package com.netcracker.services;

import com.netcracker.dao.CalculationMethodDao;
import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.CalculationMethod;
import com.netcracker.models.CommunalUtility;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
@Log4j
public class CommunalUtilityService {
    @Autowired
    private CommunalUtilityDao communalUtilityDao;
    @Autowired
    private  CalculationMethodDao calculationMethodDao;
    @Autowired
    private  NotificationService notificationService;

//    @Autowired
//    public CommunalUtilityService(CommunalUtilityDao communalUtilityDao, CalculationMethodDao calculationMethodDao, NotificationService notificationService) {
//        this.communalUtilityDao = communalUtilityDao;
//        this.calculationMethodDao = calculationMethodDao;
//        this.notificationService = notificationService;
//    }

    public List<CommunalUtility> getAllCommunalUtilities(CommunalUtility.Status status) throws DaoAccessException {
        try {
            List<CommunalUtility> utilities;
            if (status != null) {
                utilities = communalUtilityDao.getAllCommunalUtilitiesFilterByStatus(status);
            } else {
                utilities = communalUtilityDao.getAllCommunalUtilities();
            }
            return utilities;
        } catch (DaoAccessException e) {
            log.error("CommunalUtilityService method getAllCommunalUtilities(): " + e.getMessage(), e);
            throw e;
        }
    }

    public List<CommunalUtility> getAllCommunalUtilities() throws DaoAccessException {
        return getAllCommunalUtilities(null);
    }

    public CommunalUtility getCommunalUtilityById(BigInteger id) {
        try {
            return communalUtilityDao.getCommunalUtilityById(id);
        } catch (DaoAccessException e) {
            log.error("CommunalUtilityService method getCommunalUtilityById(): " + e.getMessage(), e);
            throw e;
        }
    }

    public void updateCalculationMethod(CalculationMethod calculationMethod){
        calculationMethodDao.updateCalculationMethod(calculationMethod);
    }

    public void createCommunalUtility(CommunalUtility communalUtility)
            throws DaoAccessException, IOException, MessagingException {
        try {
            if (communalUtilityDao.getUniqueCommunalUtility(communalUtility) != null) {
                IllegalArgumentException exception = new IllegalArgumentException("Communal utility with such name already exists");
                log.error(exception.getMessage(), exception);
                throw exception;
            }
            communalUtilityDao.createCommunalUtility(communalUtility);
            CommunalUtility comUtil = communalUtilityDao.getUniqueCommunalUtility(communalUtility);
            if (comUtil.getDurationType().equals(CommunalUtility.Duration.Temporary)) {
                notificationService.sendTempCommunalUtilityNotificationToAllApartments(communalUtility);
            }
        } catch (IOException | MessagingException e) {
            log.error("CommunalUtilityService method createCommunalUtility(): " + e.getMessage(), e);
            throw e;
        }
    }

    public void updateCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            CommunalUtility communalUtility1 = communalUtilityDao.getCommunalUtilityById(communalUtility.getCommunalUtilityId());
            if (communalUtility1.equals(communalUtility)) {
                IllegalArgumentException exception = new IllegalArgumentException("update is the same as existed communal utility");
                log.error(exception.getMessage(), exception);
                throw exception;
            }
            communalUtilityDao.updateCommunalUtility(communalUtility);
        } catch (DaoAccessException e) {
            log.error("CommunalUtilityService method updateCommunalUtility(): " + e.getMessage(), e);
            throw e;
        }
    }
}
