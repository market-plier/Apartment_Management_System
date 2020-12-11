package com.netcracker.dao;

import com.netcracker.models.ApartmentSubBill;

import java.math.BigInteger;
import java.util.List;

public interface ApartmentSubBillDao {
    List<ApartmentSubBill> getAllApartmentSubBills();

    ApartmentSubBill getApartmentSubBillById(BigInteger apartmentSubBillId);

    List<ApartmentSubBill> getAllApartmentSubBillsByAccountId(BigInteger accountId);

    void updateApartmentSubBill(ApartmentSubBill apartmentSubBill);

    void createApartmentSubBill(ApartmentSubBill apartmentSubBill);

    String GET_ALL_APARTMENT_SUB_BILLS = "" +
            "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id," +
            "       APT_SUB_BILL.PARENT_ID   communal_util_id," +
            "       COMMUNAL_UTILL.PARENT_ID calc_method_id," +
            "       BALANCE.VALUE            balance," +
            "       DEBT.VALUE               debt," +
            "       APT.OBJECT_ID            account_id," +
            "       COMMUNAL_NAME.VALUE      communal_name," +
            "       DURATION_LIST.VALUE      duration_type," +
            "       STATUS_LIST.VALUE        status," +
            "       DEAD_LINE.DATE_VALUE     dead_line," +
            "       CALC_NAME.VALUE          calc_name" +
            "FROM OBJECTS APT_SUB_BILL," +
            "     OBJECTS COMMUNAL_UTILL," +
            "     OBJECTS APT," +
            "     OBJREFERENCE APT_REF," +
            "     LISTS DURATION_LIST," +
            "     LISTS STATUS_LIST," +
            "     ATTRIBUTES BALANCE," +
            "     ATTRIBUTES DEBT," +
            "     ATTRIBUTES COMMUNAL_NAME," +
            "     ATTRIBUTES DURATION_TYPE," +
            "     ATTRIBUTES STATUS," +
            "     ATTRIBUTES DEAD_LINE," +
            "     ATTRIBUTES CALC_NAME" +
            "WHERE APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11" +
            "  AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND BALANCE.ATTR_ID = 25" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND DEBT.ATTR_ID = 38" +
            "  AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND COMMUNAL_NAME.ATTR_ID = 21" +
            "  AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID" +
            "  AND DURATION_TYPE.ATTR_ID = 22" +
            "  AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID" +
            "  AND STATUS.ATTR_ID = 23" +
            "  AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.ATTR_ID = 24" +
            "  AND CALC_NAME.OBJECT_ID = COMMUNAL_UTILL.PARENT_ID" +
            "  AND CALC_NAME.ATTR_ID = 20;";

    String GET_APARTMENT_SUB_BILL_BY_ID = "" +
            "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id," +
            "       APT_SUB_BILL.PARENT_ID   communal_util_id," +
            "       COMMUNAL_UTILL.PARENT_ID calc_method_id," +
            "       BALANCE.VALUE            balance," +
            "       DEBT.VALUE               debt," +
            "       APT.OBJECT_ID            account_id," +
            "       COMMUNAL_NAME.VALUE      communal_name," +
            "       DURATION_LIST.VALUE      duration_type," +
            "       STATUS_LIST.VALUE        status," +
            "       DEAD_LINE.DATE_VALUE     dead_line," +
            "       CALC_NAME.VALUE          calc_name" +
            "FROM OBJECTS APT_SUB_BILL," +
            "     OBJECTS COMMUNAL_UTILL," +
            "     OBJECTS APT," +
            "     OBJREFERENCE APT_REF," +
            "     LISTS DURATION_LIST," +
            "     LISTS STATUS_LIST," +
            "     ATTRIBUTES BALANCE," +
            "     ATTRIBUTES DEBT," +
            "     ATTRIBUTES COMMUNAL_NAME," +
            "     ATTRIBUTES DURATION_TYPE," +
            "     ATTRIBUTES STATUS," +
            "     ATTRIBUTES DEAD_LINE," +
            "     ATTRIBUTES CALC_NAME" +
            "WHERE APT_SUB_BILL.OBJECT_ID = ?" +
            "  AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11" +
            "  AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND BALANCE.ATTR_ID = 25" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND DEBT.ATTR_ID = 38" +
            "  AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND COMMUNAL_NAME.ATTR_ID = 21" +
            "  AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID" +
            "  AND DURATION_TYPE.ATTR_ID = 22" +
            "  AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID" +
            "  AND STATUS.ATTR_ID = 23" +
            "  AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.ATTR_ID = 24" +
            "  AND CALC_NAME.OBJECT_ID = COMMUNAL_UTILL.PARENT_ID" +
            "  AND CALC_NAME.ATTR_ID = 20;";

    String GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID = "" +
            "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id," +
            "       APT_SUB_BILL.PARENT_ID   communal_util_id," +
            "       COMMUNAL_UTILL.PARENT_ID calc_method_id," +
            "       BALANCE.VALUE            balance," +
            "       DEBT.VALUE               debt," +
            "       APT.OBJECT_ID            account_id," +
            "       COMMUNAL_NAME.VALUE      communal_name," +
            "       DURATION_LIST.VALUE      duration_type," +
            "       STATUS_LIST.VALUE        status," +
            "       DEAD_LINE.DATE_VALUE     dead_line," +
            "       CALC_NAME.VALUE          calc_name" +
            "FROM OBJECTS APT_SUB_BILL," +
            "     OBJECTS COMMUNAL_UTILL," +
            "     OBJECTS APT," +
            "     OBJREFERENCE APT_REF," +
            "     LISTS DURATION_LIST," +
            "     LISTS STATUS_LIST," +
            "     ATTRIBUTES BALANCE," +
            "     ATTRIBUTES DEBT," +
            "     ATTRIBUTES COMMUNAL_NAME," +
            "     ATTRIBUTES DURATION_TYPE," +
            "     ATTRIBUTES STATUS," +
            "     ATTRIBUTES DEAD_LINE," +
            "     ATTRIBUTES CALC_NAME" +
            "WHERE APT.OBJECT_ID = ?" +
            "  AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11" +
            "  AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND BALANCE.ATTR_ID = 25" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID" +
            "  AND DEBT.ATTR_ID = 38" +
            "  AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND COMMUNAL_NAME.ATTR_ID = 21" +
            "  AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID" +
            "  AND DURATION_TYPE.ATTR_ID = 22" +
            "  AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID" +
            "  AND STATUS.ATTR_ID = 23" +
            "  AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID" +
            "  AND DEAD_LINE.ATTR_ID = 24" +
            "  AND CALC_NAME.OBJECT_ID = COMMUNAL_UTILL.PARENT_ID" +
            "  AND CALC_NAME.ATTR_ID = 20;";

    String CREATE_APARTMENT_SUB_BILL_OBJ = "" +
            "MERGE INTO OBJECTS old" +
            "USING (SELECT seq_obj_next()                        OBJECT_ID," +
            "              ?                                     PARENT_ID," +
            "              13                                    OBJECT_TYPE_ID," +
            "              'ApartmentSubBill_' || seq_obj_curr() NAME," +
            "              NULL                                  DESCRIPTION" +
            "              FROM DUAL) new" +
            "ON (old.OBJECT_ID = new.OBJECT_ID)" +
            "WHEN MATCHED THEN" +
            "    UPDATE" +
            "    SET old.PARENT_ID      = new.PARENT_ID," +
            "        old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID," +
            "        old.NAME           = new.NAME," +
            "        old.DESCRIPTION    = new.DESCRIPTION" +
            "    WHERE old.PARENT_ID <> new.PARENT_ID" +
            "       OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID" +
            "       OR old.NAME <> new.NAME" +
            "       OR old.DESCRIPTION <> new.DESCRIPTION" +
            "WHEN NOT MATCHED THEN" +
            "    INSERT (old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION)" +
            "    VALUES (seq_obj_curr(), new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION);";
    String CREATE_APARTMENT_SUB_BILL_ATTRIBUTES = "" +
            "MERGE INTO ATTRIBUTES old" +
            "USING (SELECT 25 ATTR_ID, seq_obj_curr() OBJECT_ID, ? VALUE, NULL DATE_VALUE" +
            "       FROM DUAL" +
            "       UNION ALL" +
            "       SELECT 38, seq_obj_curr(), ?, NULL" +
            "       FROM DUAL" +
            "       ) new" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)" +
            "WHEN MATCHED THEN" +
            "    UPDATE" +
            "    SET old.VALUE      = new.VALUE," +
            "        old.DATE_VALUE = new.DATE_VALUE" +
            "    WHERE old.VALUE <> new.VALUE" +
            "       OR old.DATE_VALUE <> new.DATE_VALUE" +
            "WHEN NOT MATCHED THEN" +
            "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.VALUE, old.DATE_VALUE)" +
            "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.VALUE, new.DATE_VALUE);";

    String CREATE_APARTMENT_SUB_BILL_REFERENCE = "" +
            "MERGE INTO OBJREFERENCE old" +
            "USING (SELECT 33 ATTR_ID, SEQ_OBJ_CURR() OBJECT_ID, ? REFERENCE" +
            "       FROM DUAL) new" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.REFERENCE = new.REFERENCE AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN NOT MATCHED THEN" +
            "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.REFERENCE)" +
            "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.REFERENCE);";

    String UPDATE_APARTMENT_SUB_BILL = "" +
            "MERGE INTO ATTRIBUTES old" +
            "USING (" +
            "    SELECT 25 ATTR_ID, ? VALUE" +
            "    FROM DUAL" +
            "    UNION ALL" +
            "    SELECT 38, ?" +
            "    FROM DUAL" +
            ") new" +
            "ON (old.OBJECT_ID = ? AND old.ATTR_ID = new.ATTR_ID)" +
            "WHEN MATCHED THEN" +
            "    UPDATE" +
            "    SET old.VALUE = new.VALUE" +
            "    WHERE old.VALUE <> new.VALUE;";

    String EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS = "Can't get apartment's subbills";
    String EXCEPTION_GET_APARTMENT_SUB_BILL_BY_ID = "Can't get apartment's subbill with this id";
    String EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID = "Can't get apartment's subbill with this account id";
    String EXCEPTION_UPDATE_APARTMENT_SUB_BILL = "Cant update apartment's subbill with id";
    String EXCEPTION_CREATE_APARTMENT_SUB_BILL = "Cant create apartment's subbill";
}
