package com.netcracker.dao;

import com.netcracker.models.Manager;

public interface ManagerDao {

    String GET_MANAGER =
            "SELECT MANAGERBILL.OBJECT_ID manager_bill_id,\n" +
            "MNGR_BILL_CARD.VALUE card_number,\n" +
            "ACCOUNT.OBJECT_ID account_id,\n" +
            "ACC_EMAIL.VALUE email,\n" +
            "ACC_PASSWORD.VALUE password,\n" +
            "ACC_FIRSTNAME.VALUE first_name,\n" +
            "ACC_LASTNAME.VALUE last_name,\n" +
            "ACC_PHONE.VALUE phone,\n" +
            "ROLE.OBJECT_ID role_id,\n" +
            "ROLE_NAME.VALUE role_name\n" +
            "FROM OBJECTS MANAGER, OBJECTS ACCOUNT, OBJECTS MANAGERBILL, OBJECTS ROLE\n," +
            "ATTRIBUTES ACC_FIRSTNAME, ATTRIBUTES ACC_LASTNAME, \n" +
            "ATTRIBUTES ACC_EMAIL, ATTRIBUTES ACC_PASSWORD, ATTRIBUTES ACC_PHONE,\n" +
            "ATTRIBUTES ROLE_NAME, ATTRIBUTES MNGR_BILL_CARD,\n" +
            "OBJREFERENCE REFMANAGER\n" +
            "WHERE MANAGER.OBJECT_TYPE_ID = 8\n" +
            "AND REFMANAGER.ATTR_ID = 31\n" +
            "AND REFMANAGER.OBJECT_ID = MANAGER.OBJECT_ID \n" +
            "AND REFMANAGER.REFERENCE = MANAGERBILL.OBJECT_ID\n" +
            "AND MANAGER.PARENT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACCOUNT.PARENT_ID = ROLE.OBJECT_ID \n" +
            "AND ROLE_NAME.OBJECTS_ID = ROLE.OBJECT_ID\n" +
            "AND ROLE_NAME.ATTR_ID = 1\n" +
            "AND ACC_EMAIL.OBJECT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACC_EMAIL.ATTR_ID = 2\n" +
            "AND ACC_PASSWORD.OBJECT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACC_PASSWORD.ATTR_ID = 3\n" +
            "AND ACC_FIRSTNAME.OBJECT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACC_FIRSTNAME.ATTR_ID = 4\n" +
            "AND ACC_LASTNAME.OBJECT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACC_LASTNAME.ATTR_ID = 5\n" +
            "AND ACC_PHONE.OBJECT_ID = ACCOUNT.OBJECT_ID \n" +
            "AND ACC_PHONE.ATTR_ID = 6\n" +
            "AND MNGR_BILL_CARD.OBJECT_ID = MANAGERBILL.OBJECT_ID\n" +
            "AND MNGR_BILL_CARD.ATTR_ID = 19 ";;

    String UPDATE_MANAGER =
            "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 19 ATTR_ID, ? VALUE FROM DUAL) new \n" +
            "ON (old.OBJECT_ID = ? AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "        UPDATE SET old.ATTR_ID = new.ATTR_ID,\n" +
            "                   old.VALUE = new.VALUE,\n" +
            "WHERE old.VALUE <> nel.VALUE;";



    String EXCEPTION_UPDATE_MANAGER = "Cant update Manager";
    String EXCEPTION_GET_MANAGER = "Couldn't find Manager";


    Manager getManager();

    void updateManager(Manager manager);
}
