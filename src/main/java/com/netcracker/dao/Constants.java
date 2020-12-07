package com.netcracker.dao;

import java.math.BigInteger;

public interface Constants {
    //Object types
    BigInteger ANNOUNCEMENT_OBJECT_TYPE_ID = BigInteger.valueOf(3);
    BigInteger COMMENT_OBJECT_TYPE_ID = BigInteger.valueOf(4);
    BigInteger HOUSE_VOTING_OBJECT_TYPE_ID = BigInteger.valueOf(5);
    BigInteger VOTING_OPTION_OBJECT_TYPE_ID = BigInteger.valueOf(6);
    BigInteger APARTMENT_OBJECT_TYPE_ID = BigInteger.valueOf(7);
    BigInteger APARTMENT_SUB_BILL_OBJECT_TYPE_ID = BigInteger.valueOf(13);

    //Announcement attributes
    BigInteger ANNOUNCEMENT_TITLE_ATTR_ID = BigInteger.valueOf(7);
    BigInteger ANNOUNCEMENT_BODY_ATTR_ID = BigInteger.valueOf(8);
    BigInteger ANNOUNCEMENT_IS_OPENED_ATTR_ID = BigInteger.valueOf(9);
    BigInteger ANNOUNCEMENT_CREATED_AT_ATTR_ID = BigInteger.valueOf(10);

    //HouseVoting attributes
    BigInteger HOUSE_VOTING_TITLE_ATTR_ID = BigInteger.valueOf(13);

    //VotingOption attributes
    BigInteger VOTING_OPTION_NAME_ATTR_ID = BigInteger.valueOf(14);
    BigInteger VOTING_OPTION_ACCOUNT_REF_ID = BigInteger.valueOf(30);

    //Apartment attributes
    BigInteger APARTMENT_NUMBER_ATTR_ID = BigInteger.valueOf(15);
    BigInteger SQUARE_ATTR_ID = BigInteger.valueOf(16);
    BigInteger FLOOR_ATTR_ID = BigInteger.valueOf(17);
    BigInteger PEOPLE_COUNT_ATTR_ID = BigInteger.valueOf(18);

    //SubBill attributes
    BigInteger BALANCE_ATTR_ID=BigInteger.valueOf(25);
    //ApartmentSubBill attributes
    BigInteger DEBT_ATTR_ID = BigInteger.valueOf(38);

}
