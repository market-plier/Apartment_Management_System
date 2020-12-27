package com.netcracker.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigInteger;

@Data
public class VotingOption {
    @Positive
    private BigInteger votingOptionId;

    @NotNull(message = "HouseVoting cannot be blank")
    private HouseVoting houseVoting;

    @NotBlank(message = "VotingOption name cannot be blank")
    @Size(max = 255, message = "VotingOption name size is not correct. Character length must be between 1 and 255")
    private String name;

    private Integer count;

    public VotingOption(BigInteger votingOptionId, HouseVoting houseVoting, String name, Integer count) {
        this.votingOptionId = votingOptionId;
        this.houseVoting = houseVoting;
        this.name = name;
        this.count = count;
    }
}
