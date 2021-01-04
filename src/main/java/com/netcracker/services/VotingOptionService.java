package com.netcracker.services;

import com.netcracker.dao.VotingOptionDao;
import com.netcracker.exception.DaoAccessException;
import com.netcracker.models.Account;
import com.netcracker.models.Apartment;
import com.netcracker.models.VotingOption;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

@Log4j
@Service
public class VotingOptionService {
    @Autowired
    private VotingOptionDao votingOptionDao;

    public Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger announcementId) throws DaoAccessException {
        return votingOptionDao.getAllVotingOptionsByAnnouncementId(announcementId);
    }

    public VotingOption createVotingOption(VotingOption votingOption) throws DaoAccessException {
        votingOptionDao.createVotingOption(votingOption);
        return votingOption;
    }

    public List<Apartment> getApartmentsByVotingOptionId(BigInteger votingOptionId) throws DaoAccessException {
        return votingOptionDao.getApartmentsByVotingOptionId(votingOptionId);
    }

    public void addVote(BigInteger announcementId, BigInteger votingOptionId, BigInteger accountId)
            throws IllegalArgumentException, DaoAccessException {
        if (hasVote(announcementId, accountId)) {
            IllegalArgumentException exception = new IllegalArgumentException("This account has already voted");
            log.error("VotingOptionService method addVote: " + exception.getMessage(), exception);
            throw exception;
        }

        votingOptionDao.addVote(votingOptionId, accountId);
    }

    public VotingOption getVotingOption(BigInteger announcementId, BigInteger accountId) throws DaoAccessException {
        if (hasVote(announcementId, accountId)) {
            return votingOptionDao.getVotingOption(announcementId, accountId);
        }

        return null;
    }

    private boolean hasVote(BigInteger announcementId, BigInteger accountId) {
        if (announcementId == null) {
            return false;
        }

        for (VotingOption votingOption : getAllVotingOptionsByAnnouncementId(announcementId)) {
            for (Account account : getApartmentsByVotingOptionId(votingOption.getVotingOptionId())) {
                if (accountId != null && accountId.equals(account.getAccountId())) {
                    return true;
                }
            }
        }

        return false;
    }
}
