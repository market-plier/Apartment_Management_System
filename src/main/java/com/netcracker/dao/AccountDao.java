package com.netcracker.dao;

import com.netcracker.models.Account;

import java.math.BigInteger;

public interface AccountDao {
    String GET_ACCOUNT_BY_ID = "SELECT ATTR_FIRST_NAME.VALUE first_name, ATTR_LAST_NAME.VALUE last_name, ATTR_EMAIL.VALUE email, ATTR_PASSWORD.VALUE password,\n" +
            "ATTR_PHONE.VALUE phone, ATTR_ROLE_NAME.VALUE role_name,ACC_OBJ.OBJECT_ID account_id\n" +
            "FROM ATTRIBUTES ATTR_FIRST_NAME, ATTRIBUTES ATTR_LAST_NAME,\n" +
            "ATTRIBUTES ATTR_PASSWORD, ATTRIBUTES ATTR_EMAIL, ATTRIBUTES ATTR_PHONE, ATTRIBUTES ATTR_ROLE_NAME, OBJECTS ACC_OBJ\n" +
            "WHERE ACC_OBJ.OBJECT_ID = ?\n" +
            "AND ACC_OBJ.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PHONE.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_EMAIL.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PASSWORD.OBJECT_ID\n" +
            "AND ACC_OBJ.PARENT_ID = ATTR_ROLE_NAME.OBJECT_ID\n" +
            "AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "AND ATTR_PASSWORD.ATTR_ID = 3\n" +
            "AND ATTR_EMAIL.ATTR_ID = 2\n" +
            "AND ATTR_PHONE.ATTR_ID = 6\n" +
            "AND ATTR_ROLE_NAME.ATTR_ID = 1\n";

    String GET_ACCOUNT_BY_EMAIL = "SELECT ATTR_FIRST_NAME.VALUE first_name, ATTR_LAST_NAME.VALUE last_name, ATTR_EMAIL.VALUE email, ATTR_PASSWORD.VALUE password,\n" +
            "ATTR_PHONE.VALUE phone, ATTR_ROLE_NAME.VALUE role_name, ACC_OBJ.OBJECT_ID account_id\n" +
            "FROM ATTRIBUTES ATTR_FIRST_NAME, ATTRIBUTES ATTR_LAST_NAME,\n" +
            "ATTRIBUTES ATTR_PASSWORD, ATTRIBUTES ATTR_EMAIL, ATTRIBUTES ATTR_PHONE, ATTRIBUTES ATTR_ROLE_NAME, OBJECTS ACC_OBJ\n" +
            "WHERE ATTR_EMAIL.VALUE = ?\n" +
            "AND ACC_OBJ.OBJECT_ID = ATTR_EMAIL.OBJECT_ID\n" +
            "AND ACC_OBJ.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PHONE.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PASSWORD.OBJECT_ID\n" +
            "AND ACC_OBJ.PARENT_ID = ATTR_ROLE_NAME.OBJECT_ID\n" +
            "AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "AND ATTR_PASSWORD.ATTR_ID = 3\n" +
            "AND ATTR_EMAIL.ATTR_ID = 2\n" +
            "AND ATTR_PHONE.ATTR_ID = 6\n" +
            "AND ATTR_ROLE_NAME.ATTR_ID = 1";

    String EXCEPTION_GET_ACCOUNT_BY_ID = "Cant find account with id ";
    String EXCEPTION_GET_ACCOUNT_BY_EMAIL = "Cant find account with email: ";

    Account getAccount(BigInteger id);
    Account getAccountByEmail(String email);
}
