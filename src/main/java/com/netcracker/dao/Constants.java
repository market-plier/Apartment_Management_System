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
    BigInteger OPERATION_OBJECT_TYPE_ID = BigInteger.valueOf(15);
    BigInteger APARTMENT_OPERATION_OBJECT_TYPE_ID = BigInteger.valueOf(17);
    BigInteger DEBT_PAYMENT_OPERATION_OBJECT_TYPE_ID = BigInteger.valueOf(16);

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
    BigInteger BALANCE_ATTR_ID = BigInteger.valueOf(25);
    //ApartmentSubBill attributes
    BigInteger DEBT_ATTR_ID = BigInteger.valueOf(38);
    //Comment_TYPE
    BigInteger COMMENT_TYPE_ID = BigInteger.valueOf(4);
    //Comment attributtes
    BigInteger COMMENT_ATTR_BODY_ID = BigInteger.valueOf(11);
    BigInteger COMMENT_ATTR_CREATED_AT_ID = BigInteger.valueOf(12);
    BigInteger COMMENT_ATTR_REF_TO_APARTMENT = BigInteger.valueOf(29);
    // ManagerBill
    BigInteger MANAGER_BILL_TYPE = BigInteger.valueOf(9);
    //MANAGER attributes
    BigInteger MANAGER_BILL_ATTR_CARD_NUMBER = BigInteger.valueOf(19);
    BigInteger MANAGER_BILL_ATTR_OWNER_REF = BigInteger.valueOf(31);


    //Operation attributes
    BigInteger OPERATION_SUM = BigInteger.valueOf(26);
    BigInteger OPERATION_CREATED_AT = BigInteger.valueOf(27);

    //ApartmentOperation attributes
    BigInteger APARTMENT_OPERATION_TRANSFERS = BigInteger.valueOf(34);

    //DebtPaymentOperation attributes
    BigInteger DEBT_PAYMENT_OPERATION_Receives = BigInteger.valueOf(37);
    BigInteger DEBT_PAYMENT_OPERATION_PAYS = BigInteger.valueOf(35);
}
