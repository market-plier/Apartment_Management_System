package com.netcracker.services;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.math.BigInteger;
import java.util.Collection;

@Log4j
@Service
public class HouseVotingService  {
    @Autowired
    private HouseVotingDao houseVotingDao;
    @Autowired
    private VotingOptionDao votingOptionDao;

    public HouseVoting getHouseVotingByAnnouncementId(BigInteger id) throws DaoAccessException, NullPointerException {
        try {
            HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(id);

            Collection<VotingOption> votingOptions = votingOptionDao.getAllVotingOptionsByHouseVotingId(houseVoting.getHouseVotingId());
            houseVoting.setVotingOptions(votingOptions);

            return houseVoting;
        } catch (NullPointerException e) {
            log.error("HouseVotingService method getHouseVotingByAnnouncementId: " + e.getMessage(), e);
            throw e;
        }
    }

    public HouseVoting createHouseVoting(HouseVoting houseVoting) throws DaoAccessException, NullPointerException {
        try {
            houseVotingDao.createHouseVoting(houseVoting);
            return houseVoting;
        } catch (NullPointerException e) {
            log.error("HouseVotingService method createHouseVoting: " + e.getMessage(), e);
            throw e;
        }
    }

    public void deleteHouseVoting(BigInteger id) throws DaoAccessException, NullPointerException {
        try {
            houseVotingDao.deleteHouseVoting(id);
        } catch (NullPointerException e) {
            log.error("HouseVotingService method deleteHouseVoting: " + e.getMessage(), e);
            throw e;
        }
    }
}
