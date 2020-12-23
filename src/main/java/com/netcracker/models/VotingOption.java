package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
public class VotingOption {
    private BigInteger votingOptionId;

    @NotNull(message = "HouseVoting cannot be null")
    private HouseVoting houseVoting;

    @NotBlank(message = "VotingOption name cannot be empty")
    private String name;

    public VotingOption(BigInteger votingOptionId, HouseVoting houseVoting, String name) {
        this.votingOptionId = votingOptionId;
        this.houseVoting = houseVoting;
        this.name = name;
    }
}
