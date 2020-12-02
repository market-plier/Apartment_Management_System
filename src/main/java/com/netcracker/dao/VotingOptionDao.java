package com.netcracker.dao;

import com.netcracker.models.Account;
import com.netcracker.models.VotingOption;

import java.math.BigInteger;
import java.util.Collection;

public interface VotingOptionDao {
    Collection<VotingOption> getAllVotingOptionsByAnnouncementId(BigInteger id);

    void createVotingOption(VotingOption task);

    void addVotedAccount(VotingOption votingOption, Account account);
}
