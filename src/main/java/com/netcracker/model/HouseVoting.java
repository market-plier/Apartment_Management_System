package com.netcracker.model;

import lombok.Data;

import java.util.Collection;

@Data
public class HouseVoting {
    private long houseVotingId;
    private Announcement announcement;
    private String title;
    private Collection<VotingOption> votingoptions;
}
