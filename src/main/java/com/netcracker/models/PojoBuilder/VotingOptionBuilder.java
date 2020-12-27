package com.netcracker.models.PojoBuilder;

import com.netcracker.models.HouseVoting;
import com.netcracker.models.VotingOption;

import java.math.BigInteger;

public class VotingOptionBuilder {
    private BigInteger votingOptionId;
    private HouseVoting houseVoting;
    private String name;
    private Integer count;

    public VotingOptionBuilder withVotingOptionId(BigInteger votingOptionId) {
        this.votingOptionId = votingOptionId;
        return this;
    }

    public VotingOptionBuilder withHouseVoting(HouseVoting houseVoting) {
        this.houseVoting = houseVoting;
        return this;
    }

    public VotingOptionBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public VotingOptionBuilder withCount(Integer count) {
        this.count = count;
        return this;
    }

    public VotingOption build() {
        return new VotingOption(votingOptionId, houseVoting, name, count);
    }
}
