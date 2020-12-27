package com.netcracker.services;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Log4j
@Service
public class HouseVotingService  {
    @Autowired
    private HouseVotingDao houseVotingDao;
    @Autowired
    private VotingOptionDao votingOptionDao;

    public HouseVoting getHouseVotingByAnnouncementId(BigInteger announcementId) throws DaoAccessException, NullPointerException {
        try {
            HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(announcementId);

            Collection<VotingOption> votingOptions = votingOptionDao.getAllVotingOptionsByAnnouncementId(announcementId);
            houseVoting.setVotingOptions(votingOptions);

            return houseVoting;
        } catch (NullPointerException e) {
            log.error("HouseVotingService method getHouseVotingByAnnouncementId: " + e.getMessage(), e);
            throw e;
        }
    }

    public HouseVoting createHouseVoting(HouseVoting houseVoting) throws DaoAccessException, NullPointerException, IllegalArgumentException {
        try {
            BigInteger announcementId = houseVoting.getAnnouncement().getAnnouncementId();
            if (hasHouseVoting(announcementId)) {
                throw new IllegalArgumentException("This announcement has already had voting");
            }

            houseVotingDao.createHouseVoting(houseVoting);
            return houseVotingDao.getHouseVotingByAnnouncementId(announcementId);
        } catch (NullPointerException e) {
            log.error("HouseVotingService method createHouseVoting: " + e.getMessage(), e);
            throw e;
        }
    }

    private boolean hasHouseVoting(BigInteger announcementId) {
        try {
            houseVotingDao.getHouseVotingByAnnouncementId(announcementId);
            return true;
        } catch (DaoAccessException ignored) {}

        return false;
    }

    public void deleteHouseVoting(BigInteger announcementId) throws DaoAccessException, NullPointerException {
        try {
            houseVotingDao.deleteHouseVoting(announcementId);
        } catch (NullPointerException e) {
            log.error("HouseVotingService method deleteHouseVoting: " + e.getMessage(), e);
            throw e;
        }
    }
}
