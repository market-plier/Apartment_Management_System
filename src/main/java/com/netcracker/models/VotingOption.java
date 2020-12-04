package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class VotingOption {
    private BigInteger votingOptionId;
    private HouseVoting houseVoting;
    private String name;

    public VotingOption(BigInteger votingOptionId, HouseVoting houseVoting, String name) {
        this.votingOptionId = votingOptionId;
        this.houseVoting = houseVoting;
        this.name = name;
    }
}
