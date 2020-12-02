package com.netcracker.dao;

import com.netcracker.models.HouseVoting;

import java.math.BigInteger;

public interface HouseVotingDao {
    HouseVoting getHouseVotingByAnnouncementId(BigInteger id);

    void createHouseVoting(HouseVoting houseVoting);

    void deleteHouseVoting(BigInteger id);
}
