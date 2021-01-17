package com.netcracker.exception;

import java.math.BigInteger;

public interface ErrorCodes {
    BigInteger _FAIL_TO_INSERT_APARTMENT_OPERATION = BigInteger.valueOf(171);
    BigInteger _FAIL_TO_UPDATE_APARTMENT_OPERATION = BigInteger.valueOf(172);
    BigInteger _FAIL_TO_SELECT_APARTMENT_OPERATION = BigInteger.valueOf(173);
    BigInteger _FAIL_TO_DELETE_APARTMENT_OPERATION = BigInteger.valueOf(174);

    BigInteger _FAIL_TO_INSERT_DEBT_PAYMENT_OPERATION = BigInteger.valueOf(161);
    BigInteger _FAIL_TO_UPDATE_DEBT_PAYMENT_OPERATION = BigInteger.valueOf(162);
    BigInteger _FAIL_TO_SELECT_DEBT_PAYMENT_OPERATION = BigInteger.valueOf(163);
    BigInteger _FAIL_TO_DELETE_DEBT_PAYMENT_OPERATION = BigInteger.valueOf(164);

    BigInteger _FAIL_TO_INSERT_ANNOUNCEMENT = BigInteger.valueOf(31);
    BigInteger _FAIL_TO_UPDATE_ANNOUNCEMENT = BigInteger.valueOf(32);
    BigInteger _FAIL_TO_SELECT_ANNOUNCEMENT = BigInteger.valueOf(33);
    BigInteger _FAIL_TO_DELETE_ANNOUNCEMENT = BigInteger.valueOf(34);
    BigInteger _NOT_FOUND_ANNOUNCEMENT = BigInteger.valueOf(35);

    BigInteger _FAIL_TO_INSERT_COMMENT = BigInteger.valueOf(41);
    BigInteger _FAIL_TO_UPDATE_COMMENT = BigInteger.valueOf(42);
    BigInteger _FAIL_TO_SELECT_COMMENT = BigInteger.valueOf(43);
    BigInteger _FAIL_TO_DELETE_COMMENT = BigInteger.valueOf(44);

    BigInteger _FAIL_TO_INSERT_HOUSE_VOTING = BigInteger.valueOf(51);
    BigInteger _FAIL_TO_UPDATE_HOUSE_VOTING = BigInteger.valueOf(52);
    BigInteger _FAIL_TO_SELECT_HOUSE_VOTING = BigInteger.valueOf(53);
    BigInteger _FAIL_TO_DELETE_HOUSE_VOTING = BigInteger.valueOf(54);
    BigInteger _NOT_FOUND_HOUSE_VOTING = BigInteger.valueOf(55);

    BigInteger _FAIL_TO_INSERT_VOTING_OPTION = BigInteger.valueOf(61);
    BigInteger _FAIL_TO_UPDATE_VOTING_OPTION = BigInteger.valueOf(62);
    BigInteger _FAIL_TO_SELECT_VOTING_OPTION = BigInteger.valueOf(63);
    BigInteger _FAIL_TO_DELETE_VOTING_OPTION = BigInteger.valueOf(64);

    BigInteger _FAIL_TO_INSERT_VOTE_REF = BigInteger.valueOf(301);

    BigInteger _FAIL_TO_INSERT_MANAGER_SPENDING_OPERATION = BigInteger.valueOf(181);
    BigInteger _FAIL_TO_UPDATE_MANAGER_SPENDING_OPERATION = BigInteger.valueOf(182);
    BigInteger _FAIL_TO_SELECT_MANAGER_SPENDING_OPERATION = BigInteger.valueOf(183);
    BigInteger _FAIL_TO_DELETE_MANAGER_SPENDING_OPERATION = BigInteger.valueOf(184);

    BigInteger _FAIL_TO_INSERT_MANAGER_BILL = BigInteger.valueOf(91);
    BigInteger _FAIL_TO_UPDATE_MANAGER_BILL = BigInteger.valueOf(92);
    BigInteger _FAIL_TO_SELECT_MANAGER_BILL = BigInteger.valueOf(93);
    BigInteger _FAIL_TO_DELETE_MANAGER_BILL = BigInteger.valueOf(94);

    BigInteger _FAIL_TO_INSERT_APARTMENT_SUB_BILL = BigInteger.valueOf(131);
    BigInteger _FAIL_TO_UPDATE_APARTMENT_SUB_BILL = BigInteger.valueOf(132);
    BigInteger _FAIL_TO_SELECT_APARTMENT_SUB_BILL = BigInteger.valueOf(133);

    BigInteger _FAIL_TO_INSERT_APARTMENT = BigInteger.valueOf(71);
    BigInteger _FAIL_TO_UPDATE_APARTMENT_ = BigInteger.valueOf(72);
    BigInteger _FAIL_TO_SELECT_APARTMENT = BigInteger.valueOf(73);
    BigInteger _FAIL_TO_CREATE_UNIQUE_APARTMENT = BigInteger.valueOf(74);
    BigInteger _FAIL_TO_SET_APARTMENT_DATA = BigInteger.valueOf(75);
    BigInteger _FAIL_TO_GET_APARTMENTS = BigInteger.valueOf(76);

    BigInteger _FAIL_TO_INSERT_COMMUNAL_UTILITY = BigInteger.valueOf(111);
    BigInteger _FAIL_TO_UPDATE_COMMUNAL_UTILITY = BigInteger.valueOf(112);
    BigInteger _FAIL_TO_SELECT_COMMUNAL_UTILITY = BigInteger.valueOf(113);

    BigInteger _FAIL_TO_INSERT_CALCULATION_METHOD = BigInteger.valueOf(91);
    BigInteger _FAIL_TO_UPDATE_CALCULATION_METHOD = BigInteger.valueOf(92);
    BigInteger _FAIL_TO_SELECT_CALCULATION_METHOD = BigInteger.valueOf(93);
    BigInteger _FAIL_TO_DELETE_CALCULATION_METHOD = BigInteger.valueOf(94);

    BigInteger _FAIL_TO_INSERT_MANAGER_SUB_BILL = BigInteger.valueOf(141);
    BigInteger _FAIL_TO_UPDATE_MANAGER_SUB_BILL = BigInteger.valueOf(142);
    BigInteger _FAIL_TO_SELECT_MANAGER_SUB_BILL = BigInteger.valueOf(143);
    BigInteger _FAIL_TO_DELETE_MANAGER_SUB_BILL = BigInteger.valueOf(144);

    BigInteger _FAIL_TO_INSERT_MANAGER = BigInteger.valueOf(81);
    BigInteger _FAIL_TO_UPDATE_MANAGER = BigInteger.valueOf(82);
    BigInteger _FAIL_TO_SELECT_MANAGER = BigInteger.valueOf(83);
    BigInteger _FAIL_TO_DELETE_MANAGER = BigInteger.valueOf(84);

    BigInteger _FAIL_TO_SELECT_ACCOUNT = BigInteger.valueOf(23);

    BigInteger _FAIL_TO_SET_REQUEST_DATA = BigInteger.valueOf(301);

    BigInteger _FAIL_NOT_BELONG_TO_ACCOUNT = BigInteger.valueOf(999);
}
