package com.netcracker.services;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.exception.ErrorCodes;
import com.netcracker.exception.ObjectNotFoundException;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Log4j
@Service
public class HouseVotingService {
    @Autowired
    private HouseVotingDao houseVotingDao;
    @Autowired
    private VotingOptionDao votingOptionDao;

    public HouseVoting getHouseVotingByAnnouncementId(BigInteger announcementId) throws DaoAccessException {
        HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(announcementId);

        if (houseVoting == null) {
            ObjectNotFoundException exception = new ObjectNotFoundException(
                    "HouseVoting was not found with announcementId: " + announcementId,
                    ErrorCodes._NOT_FOUND_HOUSE_VOTING);
            log.error(exception.getMessage(), exception);
            throw exception;
        }

        Collection<VotingOption> votingOptions = votingOptionDao.getAllVotingOptionsByAnnouncementId(announcementId);
        houseVoting.setVotingOptions(votingOptions);

        return houseVoting;
    }

    public HouseVoting createHouseVoting(HouseVoting houseVoting) throws DaoAccessException, IllegalArgumentException {
        BigInteger announcementId = houseVoting.getAnnouncement().getAnnouncementId();

        if (hasHouseVoting(announcementId)) {
            IllegalArgumentException exception = new IllegalArgumentException("This announcement has already had voting");
            log.error("HouseVotingService method createHouseVoting: " + exception.getMessage(), exception);
            throw exception;
        }

        houseVotingDao.createHouseVoting(houseVoting);
        return houseVotingDao.getHouseVotingByAnnouncementId(announcementId);
    }

    private boolean hasHouseVoting(BigInteger announcementId) {
        HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(announcementId);
        return !(houseVoting == null);
    }

    public void deleteHouseVoting(BigInteger announcementId) throws DaoAccessException {
        houseVotingDao.deleteHouseVoting(announcementId);
    }
}
