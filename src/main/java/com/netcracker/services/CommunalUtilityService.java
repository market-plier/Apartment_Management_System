package com.netcracker.services;

import com.netcracker.dao.CommunalUtilityDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.exception.ObjectNotUniqueException;
import com.netcracker.jobs.TemporaryDebtNotificationJob;
import com.netcracker.models.CommunalUtility;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Service
@Transactional
@Log4j
public class CommunalUtilityService {
    @Autowired
    private CommunalUtilityDao communalUtilityDao;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ScheduleJobService scheduleJobService;
    @Autowired
    private ApartmentSubBillService apartmentSubBillService;
    @Autowired
    private ManagerSubBillService managerSubBillService;
    @Autowired
    private ApartmentInfoService apartmentInfoService;
    @Autowired
    private ApplicationContext context;

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

//    public void updateCalculationMethod(CalculationMethod calculationMethod){
//        calculationMethodDao.updateCalculationMethod(calculationMethod);
//    }

    public void createCommunalUtility(CommunalUtility communalUtility)
            throws DaoAccessException{
        try {
            if (communalUtilityDao.getUniqueCommunalUtility(communalUtility) != null) {
                ObjectNotUniqueException exception = new ObjectNotUniqueException("Communal utility with such name already exists", ErrorCodes._FAIL_TO_INSERT_COMMUNAL_UTILITY);
                log.error(exception.getMessage(), exception);
                throw exception;
            }
            communalUtilityDao.createCommunalUtility(communalUtility);
            CommunalUtility comUtil = communalUtilityDao.getUniqueCommunalUtility(communalUtility);

            if (comUtil.getDurationType().equals(CommunalUtility.Duration.Temporary)) {
                scheduleJobService.getScheduler().execute(context.getBean(TemporaryDebtNotificationJob.class).getJob(comUtil));
            }

            managerSubBillService.createManagerSubBill(comUtil);
            apartmentSubBillService.createApartmentSubBill(comUtil);


        } catch (DaoAccessException e) {
            log.error("CommunalUtilityService method createCommunalUtility(): " + e.getMessage(), e);
            throw e;
        }
    }

    public void updateCommunalUtility(CommunalUtility communalUtility) throws DaoAccessException {
        try {
            CommunalUtility communalUtility1 = communalUtilityDao.getCommunalUtilityById(communalUtility.getCommunalUtilityId());
            if (communalUtility1.equals(communalUtility) && communalUtility1.getStatus().equals(communalUtility.getStatus())
                    && communalUtility1.getCalculationMethod().equals(communalUtility.getCalculationMethod())
                    && communalUtility1.getDurationType().equals(communalUtility.getDurationType())
                    && communalUtility1.getDeadline().equals(communalUtility.getDeadline())
                    && communalUtility1.getCoefficient().equals(communalUtility.getCoefficient())) {
                ObjectNotUniqueException exception = new ObjectNotUniqueException("update object is the same as existed", ErrorCodes._FAIL_TO_INSERT_COMMUNAL_UTILITY);
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
