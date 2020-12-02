package com.netcracker.models;

import lombok.Data;

import java.math.BigInteger;

@Data
public class VotingOption {
    private BigInteger votingOptionId;
    private HouseVoting housevoting;
    private String name;
}
