package com.netcracker.services;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.VotingOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Service
public class VotingOptionService {
    @Autowired
    private VotingOptionDao votingOptionDao;

    public Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger id) throws DaoAccessException  {
        return votingOptionDao.getAllVotingOptionsByHouseVotingId(id);
    }

    public VotingOption createVotingOption(VotingOption votingOption) throws DaoAccessException {
        votingOptionDao.createVotingOption(votingOption);
        return votingOption;
    }

    public void addVote(BigInteger votingOptionId, BigInteger accountId) throws DaoAccessException  {
        votingOptionDao.addVote(votingOptionId, accountId);
    }
}
