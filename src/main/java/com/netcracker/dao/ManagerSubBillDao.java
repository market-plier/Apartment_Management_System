package com.netcracker.dao;

import com.netcracker.models.ManagerSubBill;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface ManagerSubBillDao {

    String GET_ALL_MANAGER_SUB_BILL =
            "SELECT MNG_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "      COMMUNAL_UTILL.OBJECT_ID   communal_util_id,\n" +
            "      CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "      BALANCE.VALUE            balance,\n" +
            "      MANAGER.OBJECT_ID        account_id,\n" +
            "      COMMUNAL_NAME.VALUE      communal_name,\n" +
            "      DURATION_LIST.VALUE      duration_type,\n" +
            "      STATUS_LIST.VALUE        status,\n" +
            "      DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "      CALC_NAME.VALUE          calc_name\n" +
            "FROM OBJECTS MNG_SUB_BILL,\n" +
            "    OBJECTS COMMUNAL_UTILL,\n" +
            "    OBJECTS MANAGER,\n" +
            "    OBJREFERENCE REFCALMETHOD,\n" +
            "    OBJREFERENCE REFMANAGER,\n" +
            "    LISTS DURATION_LIST,\n" +
            "    LISTS STATUS_LIST,\n" +
            "    ATTRIBUTES BALANCE,\n" +
            "    ATTRIBUTES COMMUNAL_NAME,\n" +
            "    ATTRIBUTES DURATION_TYPE,\n" +
            "    ATTRIBUTES STATUS,\n" +
            "    ATTRIBUTES DEAD_LINE,\n" +
            "    ATTRIBUTES CALC_NAME\n" +
            "WHERE MNG_SUB_BILL.OBJECT_ID = REFMANAGER.OBJECT_ID\n" +
            "  AND MANAGER.OBJECT_ID = REFMANAGER.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = MNG_SUB_BILL.PARENT_ID\n" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "  AND BALANCE.OBJECT_ID = MNG_SUB_BILL.OBJECT_ID\n" +
            "  AND BALANCE.ATTR_ID = 25\n" +
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
            "  AND CALC_NAME.OBJECT_ID = REFCALMETHOD.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID=REFCALMETHOD.OBJECT_ID";

    String GET_MANAGER_SUB_BILL_BY_ID =
            "SELECT MNG_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
            "      COMMUNAL_UTILL.OBJECT_ID   communal_util_id,\n" +
            "      CALC_NAME.OBJECT_ID      calc_method_id,\n" +
            "      BALANCE.VALUE            balance,\n" +
            "      MANAGER.OBJECT_ID        account_id,\n" +
            "      COMMUNAL_NAME.VALUE      communal_name,\n" +
            "      DURATION_LIST.VALUE      duration_type,\n" +
            "      STATUS_LIST.VALUE        status,\n" +
            "      DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "      CALC_NAME.VALUE          calc_name\n" +
            "FROM OBJECTS MNG_SUB_BILL,\n" +
            "    OBJECTS COMMUNAL_UTILL,\n" +
            "    OBJECTS MANAGER,\n" +
            "    OBJREFERENCE REFCALMETHOD,\n" +
            "    OBJREFERENCE REFMANAGER,\n" +
            "    LISTS DURATION_LIST,\n" +
            "    LISTS STATUS_LIST,\n" +
            "    ATTRIBUTES BALANCE,\n" +
            "    ATTRIBUTES COMMUNAL_NAME,\n" +
            "    ATTRIBUTES DURATION_TYPE,\n" +
            "    ATTRIBUTES STATUS,\n" +
            "    ATTRIBUTES DEAD_LINE,\n" +
            "    ATTRIBUTES CALC_NAME\n" +
            "WHERE MNG_SUB_BILL.OBJECT_ID = ?\n" +
            "  AND MNG_SUB_BILL.OBJECT_ID = REFMANAGER.OBJECT_ID\n" +
            "  AND MANAGER.OBJECT_ID = REFMANAGER.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID = MNG_SUB_BILL.PARENT_ID\n" +
            "  AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "  AND BALANCE.OBJECT_ID = MNG_SUB_BILL.OBJECT_ID\n" +
            "  AND BALANCE.ATTR_ID = 25\n" +
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
            "  AND CALC_NAME.OBJECT_ID = REFCALMETHOD.REFERENCE\n" +
            "  AND COMMUNAL_UTILL.OBJECT_ID=REFCALMETHOD.OBJECT_ID";

    String GET_MANAGER_SUB_BILL_BY_COMMUNAL_UTILL_ID =
            "SELECT APT_SUB_BILL.OBJECT_ID   sub_bill_id,\n" +
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
                    "WHERE COMMUNAL_UTILL.OBJECT_ID = ?\n" +
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
                    "  AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
                    "  AND COMMUNAL_UTILL.OBJECT_ID = CAL_METHOD_REF.OBJECT_ID";



    String GET_GROUPED_MANAGER_SUB_BILL_BY_COMM_UTILL ="SELECT    sum(DEBT.VALUE)  debt,\n" +
            "COMMUNAL_NAME.VALUE      communal_name,\n" +
            "DURATION_LIST.VALUE      duration_type,\n" +
            "STATUS_LIST.VALUE        status,\n" +
            "DEAD_LINE.DATE_VALUE     dead_line,\n" +
            "CALC_NAME.VALUE          calc_name\n" +
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
            "WHERE COMMUNAL_UTILL.OBJECT_ID in (:communal_list)\n" +
            "AND APT_SUB_BILL.OBJECT_ID = APT_REF.OBJECT_ID\n" +
            "AND APT.OBJECT_ID = APT_REF.REFERENCE\n" +
            "AND COMMUNAL_UTILL.OBJECT_ID = APT_SUB_BILL.PARENT_ID\n" +
            "AND COMMUNAL_UTILL.OBJECT_TYPE_ID = 11\n" +
            "AND BALANCE.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "AND BALANCE.ATTR_ID = 25\n" +
            "AND DEBT.OBJECT_ID = APT_SUB_BILL.OBJECT_ID\n" +
            "AND DEBT.ATTR_ID = 38\n" +
            "AND COMMUNAL_NAME.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "AND COMMUNAL_NAME.ATTR_ID = 21\n" +
            "AND DURATION_LIST.LIST_VALUE_ID = DURATION_TYPE.LIST_VALUE_ID\n" +
            "AND DURATION_TYPE.ATTR_ID = 22\n" +
            "AND DURATION_TYPE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "AND STATUS_LIST.LIST_VALUE_ID = STATUS.LIST_VALUE_ID\n" +
            "AND STATUS.ATTR_ID = 23\n" +
            "AND STATUS.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "AND DEAD_LINE.OBJECT_ID = COMMUNAL_UTILL.OBJECT_ID\n" +
            "AND DEAD_LINE.ATTR_ID = 24\n" +
            "AND CALC_NAME.OBJECT_ID = CAL_METHOD_REF.REFERENCE\n" +
            "AND COMMUNAL_UTILL.OBJECT_ID = CAL_METHOD_REF.OBJECT_ID\n" +
            "group by COMMUNAL_NAME.VALUE,DURATION_LIST.VALUE,\n" +
            "STATUS_LIST.VALUE,DEAD_LINE.DATE_VALUE, CALC_NAME.VALUE";

    String CREATE_MANAGER_SUB_BILL_OBJECTS =
            "MERGE INTO OBJECTS old\n" +
                    "USING (SELECT seq_obj_next() OBJECT_ID, ? PARENT_ID, 14 OBJECT_TYPE_ID, \n" +
                    "  'ManagerSubBill_' || seq_obj_curr() NAME, NULL DESCRIPTION FROM DUAL) new\n" +
                    "ON (old.OBJECT_ID = new.OBJECT_ID)\n" +
                    "WHEN MATCHED THEN\n" +
                    "    UPDATE SET old.PARENT_ID = new.PARENT_ID, old.OBJECT_TYPE_ID = new.OBJECT_TYPE_ID, \n" +
                    "    old.NAME = new.NAME, old.DESCRIPTION = new.DESCRIPTION\n" +
                    "    WHERE old.PARENT_ID <> new.PARENT_ID OR old.OBJECT_TYPE_ID <> new.OBJECT_TYPE_ID \n" +
                    "    OR old.NAME <> new.NAME OR old.DESCRIPTION <> new.DESCRIPTION\n" +
                    "WHEN NOT MATCHED THEN\n" +
                    "    INSERT (old.OBJECT_ID, old.PARENT_ID, old.OBJECT_TYPE_ID, old.NAME, old.DESCRIPTION)\n" +
                    "    VALUES (seq_obj_curr(), new.PARENT_ID, new.OBJECT_TYPE_ID, new.NAME, new.DESCRIPTION)";

    String CREATE_MANAGER_SUB_BILL_ATTRIBUTES =
            "MERGE INTO ATTRIBUTES old\n" +
                    "USING (SELECT 25 ATTR_ID, seq_obj_curr() OBJECT_ID, ? VALUE FROM DUAL) new\n" +
                    "ON (old.OBJECT_ID = new.OBJECT_ID AND old.ATTR_ID = new.ATTR_ID)\n" +
                    "WHEN MATCHED THEN\n" +
                    "    UPDATE SET old.VALUE = new.VALUE\n" +
                    "    WHERE old.VALUE <> new.VALUE\n" +
                    "WHEN NOT MATCHED THEN\n" +
                    "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.VALUE)\n" +
                    "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.VALUE);";

    String CREATE_MANAGER_SUB_BILL_REFERENCE =
            "MERGE INTO OBJREFERENCE old\n" +
                    "USING (SELECT 32 ATTR_ID, SEQ_OBJ_CURR() OBJECT_ID, ? REFERENCE FROM DUAL) new\n" +
                    "ON (old.OBJECT_ID = new.OBJECT_ID AND old.REFERENCE = new.REFERENCE AND old.ATTR_ID = new.ATTR_ID)\n" +
                    "WHEN NOT MATCHED THEN\n" +
                    "    INSERT (old.ATTR_ID, old.OBJECT_ID, old.REFERENCE)\n" +
                    "    VALUES (new.ATTR_ID, new.OBJECT_ID, new.REFERENCE)";

    String UPDATE_MANAGER_SUB_BILL =
            "MERGE INTO ATTRIBUTES old\n" +
                    "USING (SELECT 25 ATTR_ID, ? VALUE FROM DUAL) new\n" +
                    "ON (old.OBJECT_ID = ? AND old.ATTR_ID = new.ATTR_ID)\n" +
                    "WHEN MATCHED THEN\n" +
                    "        UPDATE SET old.VALUE = new.VALUE\n" +
                    "WHERE old.VALUE <> new.VALUE";


    String EXCEPTION_INSERT_MANAGER_SUB_BILL = "Cant insert Manager Sub Bill";
    String EXCEPTION_UPDATE_MANAGER_SUB_BILL = "Cant update Manager Sub Bill";
    String EXCEPTION_GET_MANAGER_SUB_BILL_BY_ID = "Couldn't find Manager Sub Bill with id";
    String EXCEPTION_GET_MANAGER_SUB_BILL_BY_COMMUNAL_UTILL_ID = "Couldn't find Manager Sub Bill with this Communal Utility id";
    String EXCEPTION_GET_ALL_MANAGER_SUB_BILL = "Failed to get any Manager Sub Bill";
    String EXCEPTION_GET_MANAGER_SUB_BILLS_BY_COMMUNAL_UTILS_LIST = "Couldn't get Manager Sub Bill debt by these ids";

    Collection<ManagerSubBill> getAllManagerSubBills();

    ManagerSubBill getManagerSubBillById(BigInteger id);

    void updateManagerSubBill(ManagerSubBill managerSubBill);

    void createManagerSubBill(ManagerSubBill managerSubBill);

    ManagerSubBill getManagerSubBillByCommunalUtilityId(BigInteger id);

    Map<ManagerSubBill, Double> getManagerSubBillDeptByCommunalUtility(Set<BigInteger> CommunalUtilityId);


}