package com.netcracker.services;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.VotingOption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;

@Log4j
@Service
public class VotingOptionService {
    @Autowired
    private VotingOptionDao votingOptionDao;

    public Collection<VotingOption> getAllVotingOptionsByHouseVotingId(BigInteger id) throws DaoAccessException, NullPointerException  {
        try {
            return votingOptionDao.getAllVotingOptionsByHouseVotingId(id);
        } catch (NullPointerException e) {
            log.error("VotingOptionService method getAllVotingOptionsByAnnouncementId: " + e.getMessage(), e);
            throw e;
        }
    }

    public VotingOption createVotingOption(VotingOption votingOption) throws DaoAccessException, NullPointerException {
        try {
            votingOptionDao.createVotingOption(votingOption);
            return votingOption;
        } catch (NullPointerException e) {
            log.error("VotingOptionService method createVotingOption: " + e.getMessage(), e);
            throw e;
        }
    }

    public void addVote(BigInteger houseVotingId, BigInteger votingOptionId, BigInteger accountId) throws DaoAccessException, NullPointerException  {
        try {
            if (hasVote(houseVotingId, accountId)) {
                throw new IllegalArgumentException("This account has already voted");
            }

            votingOptionDao.addVote(votingOptionId, accountId);
        } catch (NullPointerException e) {
            log.error("VotingOptionService method addVote: " + e.getMessage(), e);
            throw e;
        }
    }

    private boolean hasVote(BigInteger houseVotingId, BigInteger accountId) {
        try {
            for (VotingOption votingOption: getAllVotingOptionsByHouseVotingId(houseVotingId)) {
                Collection<BigInteger> apartmentIds = votingOptionDao.getApartmentIdsByVotingOptionId(votingOption.getVotingOptionId());
                if (apartmentIds.contains(accountId)) {
                    return true;
                }
            }
        } catch (DaoAccessException ignored) {}

        return false;
    }
}
