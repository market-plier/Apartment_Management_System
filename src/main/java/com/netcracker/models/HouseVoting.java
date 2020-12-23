package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;
import java.util.Collection;

@Data
public class HouseVoting {
    private BigInteger houseVotingId;

    @NotNull(message = "Announcement cannot be null")
    private Announcement announcement;

    @NotBlank(message = "HouseVoting title cannot be empty")
    private String title;

    private Collection<VotingOption> votingOptions;

    public HouseVoting(BigInteger houseVotingId, Announcement announcement,
                       String title, Collection<VotingOption> votingOptions) {
        this.houseVotingId = houseVotingId;
        this.announcement = announcement;
        this.title = title;
        this.votingOptions = votingOptions;
    }
}
