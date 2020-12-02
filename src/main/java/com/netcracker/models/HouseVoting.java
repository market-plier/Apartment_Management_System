package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;
import java.util.Collection;

@Data
public class HouseVoting {
    private BigInteger houseVotingId;
    private Announcement announcement;
    private String title;
    private Collection<VotingOption> votingoptions;
}
