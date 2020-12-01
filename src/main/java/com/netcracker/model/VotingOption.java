package com.netcracker.model;

import lombok.Data;

@Data
public class VotingOption {
    private long votingOptionId;
    private HouseVoting housevoting;
    private String name;
}
