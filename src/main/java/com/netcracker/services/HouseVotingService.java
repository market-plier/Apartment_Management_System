package com.netcracker.services;

import com.netcracker.dao.HouseVotingDao;
import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class HouseVotingService  {
    @Autowired
    private HouseVotingDao houseVotingDao;
    private VotingOptionDao votingOptionDao;

    public HouseVoting getHouseVotingByAnnouncementId(BigInteger id) throws DaoAccessException {
        HouseVoting houseVoting = houseVotingDao.getHouseVotingByAnnouncementId(id);

        Collection<VotingOption> votingOptions = votingOptionDao.getAllVotingOptionsByHouseVotingId(houseVoting.getHouseVotingId());
        houseVoting.setVotingOptions(votingOptions);

        return houseVoting;
    }

    public HouseVoting createHouseVoting(HouseVoting houseVoting) throws DaoAccessException {
        houseVotingDao.createHouseVoting(houseVoting);
        return houseVoting;
    }

    public void deleteHouseVoting(BigInteger id) throws DaoAccessException {
        houseVotingDao.deleteHouseVoting(id);
    }
}
