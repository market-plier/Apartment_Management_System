package com.netcracker.dao;

import java.math.BigInteger;

public interface Constants {
    //Object types
    BigInteger ANNOUNCEMENT_OBJECT_TYPE_ID = BigInteger.valueOf(3);
    BigInteger HOUSE_VOTING_OBJECT_TYPE_ID = BigInteger.valueOf(5);
    BigInteger VOTING_OPTION_OBJECT_TYPE_ID = BigInteger.valueOf(6);

    //Announcement attributes
    BigInteger ANNOUNCEMENT_TITLE_ATTR_ID = BigInteger.valueOf(7);
    BigInteger ANNOUNCEMENT_BODY_ATTR_ID = BigInteger.valueOf(8);
    BigInteger ANNOUNCEMENT_IS_OPENED_ATTR_ID = BigInteger.valueOf(9);
    BigInteger ANNOUNCEMENT_CREATED_AT_ATTR_ID = BigInteger.valueOf(10);

    //HouseVoting attributes
    BigInteger HOUSE_VOTING_TITLE_ATTR_ID = BigInteger.valueOf(13);

    //VotingOption attributes
    BigInteger VOTING_OPTION_NAME_ATTR_ID = BigInteger.valueOf(14);
}
