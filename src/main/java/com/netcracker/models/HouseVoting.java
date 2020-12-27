package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Collection;

@Data
public class HouseVoting {
    @Positive
    private BigInteger houseVotingId;

    @NotNull(message = "Announcement cannot be null")
    private Announcement announcement;

    @NotBlank(message = "HouseVoting title cannot be blank")
    @Size(max = 255, message = "HouseVoting title size is not correct. Character length must be between 1 and 255")
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
