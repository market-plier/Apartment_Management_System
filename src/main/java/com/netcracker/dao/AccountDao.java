package com.netcracker.dao;

import com.netcracker.models.Account;

import java.math.BigInteger;

public interface AccountDao {
    String UPDATE_ACCOUNT ="MERGE INTO ATTRIBUTES x\n" +
            "USING (\n" +
            "    SELECT 2 ATTR_ID, ? VALUE FROM DUAL\n" +
            "    UNION ALL\n" +
            "    SELECT 5, ? FROM DUAL\n" +
            "    UNION ALL\n" +
            "    SELECT 4, ? FROM DUAL\n" +
            "    UNION ALL\n" +
            "    SELECT 6, ? FROM DUAL\n" +
            ") y\n" +
            "ON (x.OBJECT_ID = ? AND x.ATTR_ID = y.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE\n" +
            "    SET x.VALUE = y.VALUE\n" +
            "    WHERE x.VALUE <> y.VALUE";

    String UPDATE_ACCOUNT_PASSWORD ="MERGE INTO ATTRIBUTES x\n" +
            "USING (\n" +
            "    SELECT 3 ATTR_ID, ? VALUE FROM DUAL\n" +
            ") y\n" +
            "ON (x.OBJECT_ID = ? AND x.ATTR_ID = y.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE\n" +
            "    SET x.VALUE = y.VALUE\n" +
            "    WHERE x.VALUE <> y.VALUE";

            String GET_ACCOUNT_BY_ID = "SELECT ATTR_FIRST_NAME.VALUE first_name, ATTR_LAST_NAME.VALUE last_name, ATTR_EMAIL.VALUE email, ATTR_PASSWORD.VALUE password,\n" +
            "ATTR_PHONE.VALUE phone, ROLE_VAL.VALUE role_name,ACC_OBJ.OBJECT_ID account_id\n" +
            "FROM ATTRIBUTES ATTR_FIRST_NAME, ATTRIBUTES ATTR_LAST_NAME,\n" +
            "ATTRIBUTES ATTR_PASSWORD, ATTRIBUTES ATTR_EMAIL, ATTRIBUTES ATTR_PHONE, ATTRIBUTES ATTR_ROLE_NAME, OBJECTS ACC_OBJ,LISTS ROLE_VAL\n" +
            "WHERE ACC_OBJ.OBJECT_ID = ?\n" +
            "AND ACC_OBJ.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PHONE.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_EMAIL.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PASSWORD.OBJECT_ID\n" +
            "AND ATTR_FIRST_NAME.OBJECT_ID= ATTR_ROLE_NAME.OBJECT_ID\n" +
            "AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "AND ATTR_PASSWORD.ATTR_ID = 3\n" +
            "AND ATTR_EMAIL.ATTR_ID = 2\n" +
            "AND ATTR_PHONE.ATTR_ID = 6\n" +
            "AND ATTR_ROLE_NAME.ATTR_ID = 1\n" +
            "AND ROLE_VAL.ATTR_ID = ATTR_ROLE_NAME.ATTR_ID\n" +
            "AND ATTR_ROLE_NAME.LIST_VALUE_ID = ROLE_VAL.LIST_VALUE_ID\n"+
            "AND ATTR_ROLE_NAME.OBJECT_ID=ACC_OBJ.OBJECT_ID";

    String GET_ACCOUNT_BY_EMAIL = "SELECT ATTR_FIRST_NAME.VALUE first_name, ATTR_LAST_NAME.VALUE last_name, ATTR_EMAIL.VALUE email, ATTR_PASSWORD.VALUE password,\n" +
            "       ATTR_PHONE.VALUE phone, ROLE_VAL.VALUE role_name, ACC_OBJ.OBJECT_ID account_id\n" +
            "FROM ATTRIBUTES ATTR_FIRST_NAME, ATTRIBUTES ATTR_LAST_NAME,\n" +
            "     ATTRIBUTES ATTR_PASSWORD, ATTRIBUTES ATTR_EMAIL, ATTRIBUTES ATTR_PHONE, ATTRIBUTES ATTR_ROLE_NAME, OBJECTS ACC_OBJ,LISTS ROLE_VAL\n" +
            "WHERE ATTR_EMAIL.VALUE = ?\n" +
            "  AND ACC_OBJ.OBJECT_ID = ATTR_EMAIL.OBJECT_ID\n" +
            "  AND ACC_OBJ.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "  AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_LAST_NAME.OBJECT_ID\n" +
            "  AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PHONE.OBJECT_ID\n" +
            "  AND ATTR_FIRST_NAME.OBJECT_ID = ATTR_PASSWORD.OBJECT_ID\n" +
            "  AND ATTR_LAST_NAME.ATTR_ID = 5\n" +
            "  AND ATTR_FIRST_NAME.ATTR_ID = 4\n" +
            "  AND ATTR_PASSWORD.ATTR_ID = 3\n" +
            "  AND ATTR_EMAIL.ATTR_ID = 2\n" +
            "  AND ATTR_PHONE.ATTR_ID = 6\n" +
            "  AND ATTR_ROLE_NAME.ATTR_ID = 1\n" +
            "  AND ROLE_VAL.ATTR_ID = ATTR_ROLE_NAME.ATTR_ID\n" +
            "  AND ATTR_ROLE_NAME.LIST_VALUE_ID = ROLE_VAL.LIST_VALUE_ID\n" +
            "  AND ATTR_ROLE_NAME.OBJECT_ID=ACC_OBJ.OBJECT_ID";

    String EXCEPTION_GET_ACCOUNT_BY_ID = "Cant find account with id ";
    String EXCEPTION_GET_ACCOUNT_BY_EMAIL = "Cant find account with email: ";
    String EXCEPTION_UPDATE_ACCOUNT = "Cant update account";

    Account getAccount(BigInteger id);
    Account getAccountByEmail(String email);
    Account updateAccount(Account account);
    Account updateAccountPassword(Account account);
}
