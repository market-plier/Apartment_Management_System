package com.netcracker.dao;

import com.netcracker.models.ApartmentSubBill;
import com.netcracker.models.CommunalUtility;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Set;

public interface ApartmentSubBillDao {
    List<ApartmentSubBill> getAllApartmentSubBills();

    ApartmentSubBill getApartmentSubBillById(BigInteger apartmentSubBillId);

    List<ApartmentSubBill> getAllApartmentSubBillsByAccountId(BigInteger accountId);

    void updateApartmentSubBill(ApartmentSubBill apartmentSubBill);

    void createApartmentSubBill(ApartmentSubBill apartmentSubBill);

    List<ApartmentSubBill> getApartmentSubBillsByCommunalUtilityList(BigInteger accountId, Set<BigInteger> communalList);

    Double getApartmentDebtByCommunalUtilityList(BigInteger accountId, Set<BigInteger> communalList);

    ApartmentSubBill getApartmentSubBillByApartmentIdAndCommunalUtilityName(BigInteger apartmentId, String communalUtilityName);

    String GET_APARTMENT_DEBT_BY_COMMUNAL_UTILS_LIST = "SELECT SUM(DEBT.VALUE) debt\n" +
            "FROM OBJECTS APT_SUB_BILL,\n" +
            "     OBJECTS COMMUNAL_UTILL,\n" +
            "     OBJECTS APT,\n" +
            "     OBJREFERENCE APT_REF,\n" +
            "     ATTRIBUTES DEBT\n" +
            "WHERE APT.OBJECT_ID = (:account_id)\n" +
            "  AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "  AND DEBT.ATTR_ID = 38\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID IN (:communal_list)";

    String GET_APARTMENT_SUB_BILLS_BY_COMMUNAL_UTILS_LIST = "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "                   COMMUNAL_UTILL.OBJECT_ID communal_util_id,\n" +
            "                   CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "                   BALANCE.VALUE            balance,\n" +
            "                   DEBT.VALUE               debt,\n" +
            "                   APT.OBJECT_ID            account_id,\n" +
            "                   COMMUNAL_NAME.VALUE      communal_name,\n" +
            "                   DURATION_LIST.VALUE      duration_type,\n" +
            "                   STATUS_LIST.VALUE        status,\n" +
            "                   DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "                   CALC_NAME.VALUE          calc_name\n" +
            "            FROM OBJECTS APT_SUB_BILL,\n" +
            "                 OBJECTS COMMUNAL_UTILL,\n" +
            "                 OBJREFERENCE CAL_METHOD_REF,\n" +
            "                 OBJECTS APT,\n" +
            "                 OBJREFERENCE APT_REF,\n" +
            "                 LISTS DURATION_LIST,\n" +
            "                 LISTS STATUS_LIST,\n" +
            "                 ATTRIBUTES BALANCE,\n" +
            "                 ATTRIBUTES DEBT,\n" +
            "                 ATTRIBUTES COMMUNAL_NAME,\n" +
            "                 ATTRIBUTES DURATION_TYPE,\n" +
            "                 ATTRIBUTES STATUS,\n" +
            "                 ATTRIBUTES DEAD_LINE,\n" +
            "                 ATTRIBUTES CALC_NAME\n" +
            "            WHERE APT.OBJECT_ID = (:account_id)\n" +
            "              AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "              AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "              AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "              AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND BALANCE.ATTR_ID = 25\n" +
            "              AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND DEBT.ATTR_ID = 38\n" +
            "              AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "              AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "              AND DURATION_TYPE.ATTR_ID = 22\n" +
            "              AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "              AND STATUS.ATTR_ID = 23\n" +
            "              AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.ATTR_ID = 24\n" +
            "              AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "              AND CALC_NAME.ATTR_ID = 20\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID=CAL_METHOD_REF.OBJECT_ID\n" +
            "              AND COMMUNAL_NAME.OBJECT_ID IN (:communal_list)";

    String GET_APARTMENT_SUB_BILL_BY_APARTMENT_ID_AND_COMMUNAL_UTILITY_NAME = "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "                   COMMUNAL_UTILL.OBJECT_ID communal_util_id,\n" +
            "                   CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "                   BALANCE.VALUE            balance,\n" +
            "                   DEBT.VALUE               debt,\n" +
            "                   APT.OBJECT_ID            account_id,\n" +
            "                   COMMUNAL_NAME.VALUE      communal_name,\n" +
            "                   DURATION_LIST.VALUE      duration_type,\n" +
            "                   STATUS_LIST.VALUE        status,\n" +
            "                   DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "                   CALC_NAME.VALUE          calc_name\n" +
            "            FROM OBJECTS APT_SUB_BILL,\n" +
            "                 OBJECTS COMMUNAL_UTILL,\n" +
            "                 OBJREFERENCE CAL_METHOD_REF,\n" +
            "                 OBJECTS APT,\n" +
            "                 OBJREFERENCE APT_REF,\n" +
            "                 LISTS DURATION_LIST,\n" +
            "                 LISTS STATUS_LIST,\n" +
            "                 ATTRIBUTES BALANCE,\n" +
            "                 ATTRIBUTES DEBT,\n" +
            "                 ATTRIBUTES COMMUNAL_NAME,\n" +
            "                 ATTRIBUTES DURATION_TYPE,\n" +
            "                 ATTRIBUTES STATUS,\n" +
            "                 ATTRIBUTES DEAD_LINE,\n" +
            "                 ATTRIBUTES CALC_NAME\n" +
            "            WHERE APT.OBJECT_ID = ?\n" +
            "              AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "              AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "              AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "              AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND BALANCE.ATTR_ID = 25\n" +
            "              AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND DEBT.ATTR_ID = 38\n" +
            "              AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "              AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "              AND DURATION_TYPE.ATTR_ID = 22\n" +
            "              AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "              AND STATUS.ATTR_ID = 23\n" +
            "              AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.ATTR_ID = 24\n" +
            "              AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "              AND CALC_NAME.ATTR_ID = 20\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID=CAL_METHOD_REF.OBJECT_ID\n" +
            "              AND COMMUNAL_NAME.VALUE = ?";

    String GET_ALL_APARTMENT_SUB_BILLS = "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "       COMMUNAL_UTILL.OBJECT_ID   communal_util_id,\n" +
            "       CALC_NAME.OBJECT_ID calc_method_id,\n" +
            "       BALANCE.VALUE            balance,\n" +
            "       DEBT.VALUE               debt,\n" +
            "       APT.OBJECT_ID            account_id,\n" +
            "       COMMUNAL_NAME.VALUE      communal_name,\n" +
            "       DURATION_LIST.VALUE      duration_type,\n" +
            "       STATUS_LIST.VALUE        status,\n" +
            "       DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "       CALC_NAME.VALUE     calc_name\n" +
            "FROM OBJECTS APT_SUB_BILL,\n" +
            "     OBJECTS COMMUNAL_UTILL,\n" +
            "     OBJREFERENCE CAL_METHOD_REF,\n" +
            "     OBJECTS APT,\n" +
            "     OBJREFERENCE APT_REF,\n" +
            "     LISTS DURATION_LIST,\n" +
            "     LISTS STATUS_LIST,\n" +
            "     ATTRIBUTES BALANCE,\n" +
            "     ATTRIBUTES DEBT,\n" +
            "     ATTRIBUTES COMMUNAL_NAME,\n" +
            "     ATTRIBUTES DURATION_TYPE,\n" +
            "     ATTRIBUTES STATUS,\n" +
            "     ATTRIBUTES DEAD_LINE,\n" +
            "     ATTRIBUTES CALC_NAME\n" +
            "WHERE APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "  AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "  AND BALANCE.ATTR_ID = 25\n" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "  AND DEBT.ATTR_ID = 38\n" +
            "  AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "  AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "  AND DURATION_TYPE.ATTR_ID = 22\n" +
            "  AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "  AND STATUS.ATTR_ID = 23\n" +
            "  AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND DEAD_LINE.ATTR_ID = 24\n" +
            "  AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID=CAL_METHOD_REF.OBJECT_ID";

    String GET_APARTMENT_SUB_BILL_BY_ID = "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "       COMMUNAL_UTILL.OBJECT_ID communal_util_id,\n" +
            "       CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "       BALANCE.VALUE            balance,\n" +
            "       DEBT.VALUE               debt,\n" +
            "       APT.OBJECT_ID            account_id,\n" +
            "       COMMUNAL_NAME.VALUE      communal_name,\n" +
            "       DURATION_LIST.VALUE      duration_type,\n" +
            "       STATUS_LIST.VALUE        status,\n" +
            "       DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "       CALC_NAME.VALUE          calc_name\n" +
            "FROM OBJECTS APT_SUB_BILL,\n" +
            "     OBJECTS COMMUNAL_UTILL,\n" +
            "     OBJREFERENCE CAL_METHOD_REF,\n" +
            "     OBJECTS APT,\n" +
            "     OBJREFERENCE APT_REF,\n" +
            "     LISTS DURATION_LIST,\n" +
            "     LISTS STATUS_LIST,\n" +
            "     ATTRIBUTES BALANCE,\n" +
            "     ATTRIBUTES DEBT,\n" +
            "     ATTRIBUTES COMMUNAL_NAME,\n" +
            "     ATTRIBUTES DURATION_TYPE,\n" +
            "     ATTRIBUTES STATUS,\n" +
            "     ATTRIBUTES DEAD_LINE,\n" +
            "     ATTRIBUTES CALC_NAME\n" +
            "WHERE APT_SUB_BILL.OBJECT_ID = ?\n" +
            "  AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "  AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "  AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "  AND BALANCE.ATTR_ID = 25\n" +
            "  AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "  AND DEBT.ATTR_ID = 38\n" +
            "  AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "  AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "  AND DURATION_TYPE.ATTR_ID = 22\n" +
            "  AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "  AND STATUS.ATTR_ID = 23\n" +
            "  AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "  AND DEAD_LINE.ATTR_ID = 24\n" +
            "  AND CALC_NAME.ATTR_ID = 20\n" +
            "  AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = CAL_METHOD_REF.OBJECT_ID";

    String GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID = "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "                   COMMUNAL_UTILL.OBJECT_ID communal_util_id,\n" +
            "                   CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "                   BALANCE.VALUE            balance,\n" +
            "                   DEBT.VALUE               debt,\n" +
            "                   APT.OBJECT_ID            account_id,\n" +
            "                   COMMUNAL_NAME.VALUE      communal_name,\n" +
            "                   DURATION_LIST.VALUE      duration_type,\n" +
            "                   STATUS_LIST.VALUE        status,\n" +
            "                   DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "                   CALC_NAME.VALUE          calc_name\n" +
            "            FROM OBJECTS APT_SUB_BILL,\n" +
            "                 OBJECTS COMMUNAL_UTILL,\n" +
            "                 OBJREFERENCE CAL_METHOD_REF,\n" +
            "                 OBJECTS APT,\n" +
            "                 OBJREFERENCE APT_REF,\n" +
            "                 LISTS DURATION_LIST,\n" +
            "                 LISTS STATUS_LIST,\n" +
            "                 ATTRIBUTES BALANCE,\n" +
            "                 ATTRIBUTES DEBT,\n" +
            "                 ATTRIBUTES COMMUNAL_NAME,\n" +
            "                 ATTRIBUTES DURATION_TYPE,\n" +
            "                 ATTRIBUTES STATUS,\n" +
            "                 ATTRIBUTES DEAD_LINE,\n" +
            "                 ATTRIBUTES CALC_NAME\n" +
            "            WHERE APT.OBJECT_ID = ?\n" +
            "              AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "              AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "              AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "              AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND BALANCE.ATTR_ID = 25\n" +
            "              AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "              AND DEBT.ATTR_ID = 38\n" +
            "              AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "              AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "              AND DURATION_TYPE.ATTR_ID = 22\n" +
            "              AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "              AND STATUS.ATTR_ID = 23\n" +
            "              AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "              AND DEAD_LINE.ATTR_ID = 24\n" +
            "              AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "              AND CALC_NAME.ATTR_ID = 20\n" +
            "              AND COMMUNAL_UTILL.OBJECT_ID = CAL_METHOD_REF.OBJECT_ID";

    String CREATE_APARTMENT_SUB_BILL_OBJ = "MERGE INTO OBJECTS old\n" +
            "USING (SELECT seq_obj_next()                        OBJECT_ID,\n" +
            "              ?                                     PARENT_ID,\n" +
            "              13                                    OBJECT_TYPE_ID,\n" +
            "              'ApartmentSubBill_' || seq_obj_curr() NAME,\n" +
            "              NULL                                  DESCRIPTION\n" +
            "              FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE\n" +
            "    SET old.PARENT_ID      = new.PARENT_ID,\n" +
            "        old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID,\n" +
            "        old.NAME           = new.NAME,\n" +
            "        old.DESCRIPTION    = new.DESCRIPTION\n" +
            "    WHERE old.PARENT_ID <> new.PARENT_ID\n" +
            "       OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID\n" +
            "       OR old.NAME <> new.NAME\n" +
            "       OR old.DESCRIPTION <> new.DESCRIPTION\n" +
            "WHEN NOT MATCHED THEN\n" +
            "    INSERT (old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION)\n" +
            "    VALUES (seq_obj_curr(), new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION)";
    String CREATE_APARTMENT_SUB_BILL_ATTRIBUTES = "MERGE INTO ATTRIBUTES old\n" +
            "USING (SELECT 25 ATTR_ID, seq_obj_curr() OBJECT_ID, ? VALUE\n" +
            "       FROM DUAL\n" +
            "       UNION ALL\n" +
            "       SELECT 38, seq_obj_curr(), ?\n" +
            "       FROM DUAL\n" +
            "       ) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE\n" +
            "    SET old.VALUE      = new.VALUE\n" +
            "    WHERE old.VALUE <> new.VALUE\n" +
            "WHEN NOT MATCHED THEN\n" +
            "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.VALUE)\n" +
            "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.VALUE)";

    String CREATE_APARTMENT_SUB_BILL_REFERENCE = "MERGE INTO OBJREFERENCE old\n" +
            "USING (SELECT 33 ATTR_ID, SEQ_OBJ_CURR() OBJECT_ID, ? REFERENCE\n" +
            "       FROM DUAL) new\n" +
            "ON (old.OBJECT_ID = new.OBJECT_ID AND old.REFERENCE = new.REFERENCE\n" +
            "    AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN NOT MATCHED THEN\n" +
            "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.REFERENCE)\n" +
            "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.REFERENCE)";

    String UPDATE_APARTMENT_SUB_BILL = "MERGE INTO ATTRIBUTES old\n" +
            "USING (\n" +
            "    SELECT 25 ATTR_ID, TO_CHAR(?) VALUE FROM DUAL\n" +
            "    UNION ALL\n" +
            "    SELECT 38, TO_CHAR(?) FROM DUAL\n" +
            ") new\n" +
            "ON (old.OBJECT_ID = ? AND old.ATTR_ID = new.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "    UPDATE\n" +
            "    SET old.VALUE = new.VALUE\n" +
            "    WHERE old.VALUE <> new.VALUE";

    String EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS = "Can't get apartment's subbills";
    String EXCEPTION_GET_APARTMENT_SUB_BILL_BY_ID = "Can't get apartment's subbill with this id";
    String EXCEPTION_GET_ALL_APARTMENT_SUB_BILLS_BY_ACCOUNT_ID = "Can't get apartment's subbill with this account id";
    String EXCEPTION_UPDATE_APARTMENT_SUB_BILL = "Cant update apartment's subbill with id";
    String EXCEPTION_CREATE_APARTMENT_SUB_BILL = "Cant create apartment's subbill";
    String EXCEPTION_GET_APARTMENT_DEBT_BY_COMMUNAL_UTILS_LIST = "Cant get apartment's subbills debt";
    String EXCEPTION_GET_APARTMENT_SUB_BILLS_BY_COMMUNAL_UTILS_LIST="Cant get apartment's subbills by this list";
    String EXCEPTION_GET_APARTMENT_SUB_BILL_BY_APARTMENT_ID_AND_COMMUNAL_UTILITY_NAME="Cant get apartment subbill by this communal utility name";
}
