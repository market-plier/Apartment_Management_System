package com.netcracker.dao;

import com.netcracker.models.ManagerSpendingOperation;

import java.math.BigInteger;
import java.util.List;

public interface ManagerSpendingOperationDao {
    String CREATE_MANAGER_OPERATION_SPENDING ="INSERT ALL\n" +
            "INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)\n" +
            "VALUES (OBJ_ID_SEQ.nextval,NULL,18,'ManagerOperationSpending_'||OBJ_ID_SEQ.currval,NULL)\n" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)\n" +
            "VALUES (28,OBJ_ID_SEQ.currval,?,null,null)\n" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)\n" +
            "VALUES (26,OBJ_ID_SEQ.currval,?,NULL,NULL)\n" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)\n" +
            "VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)\n" +
            "INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)\n" +
            "VALUES (36,OBJ_ID_SEQ.currval,?)\n"+
            "SELECT * FROM DUAL";

    String GET_ALL_MANAGER_OPERATION = "SELECT ATTR_OPER_DESC.OBJECT_ID manager_spending_id, ATTR_OPER_DESC.VALUE manager_spending_description,\n" +
            "ATTR_OPER_SUM.VALUE manager_spending_sum, OBJ_MANSUBBIL.REFERENCE manager_sub_bill,MANAGER_SUB_BILL.PARENT_ID communal_utility_id,\n" +
            "COMM_LIST_STATUS.VALUE status,  COMM_UTILITY_NAME.VALUE name_communal, COMM_LIST.VALUE duration_type, MANAGER_BILL_BALANCE.VALUE MANAGER_SUBBIL_BALANCE,\n" +
            "ATTR_OPER_CREATED_AT.DATE_VALUE operation_created_at\n"+
            "FROM OBJREFERENCE OBJ_MANSUBBIL, ATTRIBUTES ATTR_OPER_SUM,\n" +
            "ATTRIBUTES ATTR_OPER_DESC, ATTRIBUTES ATTR_OPER_CREATED_AT,OBJECTS MANAGER_SUB_BILL, LISTS COMM_LIST,\n" +
            "ATTRIBUTES COMM_UTILITY_NAME, ATTRIBUTES COMM_UTILITY_DURTAPE, ATTRIBUTES COMM_UTILITY_STATUS, ATTRIBUTES MANAGER_BILL_BALANCE,\n" +
            "LISTS COMM_LIST_STATUS\n"+
            "WHERE OBJ_MANSUBBIL.ATTR_ID = 36\n" +
            "AND OBJ_MANSUBBIL.OBJECT_ID = ATTR_OPER_SUM.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_DESC.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_CREATED_AT.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.ATTR_ID = 26\n" +
            "AND ATTR_OPER_CREATED_AT.ATTR_ID = 27\n" +
            "AND ATTR_OPER_DESC.ATTR_ID = 28\n" +
            "AND MANAGER_SUB_BILL.OBJECT_ID = MANAGER_BILL_BALANCE.OBJECT_ID\n" +
            "AND MANAGER_BILL_BALANCE.ATTR_ID = 25\n" +
            "AND MANAGER_SUB_BILL.OBJECT_ID = OBJ_MANSUBBIL.REFERENCE\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_NAME.OBJECT_ID\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_DURTAPE.OBJECT_ID\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID =COMM_UTILITY_STATUS.OBJECT_ID\n" +
            "AND COMM_UTILITY_NAME.ATTR_ID = 21\n" +
            "AND COMM_UTILITY_STATUS.ATTR_ID = 23\n" +
            "AND COMM_UTILITY_STATUS.LIST_VALUE_ID = COMM_LIST_STATUS.LIST_VALUE_ID\n" +
            "AND COMM_LIST_STATUS.ATTR_ID = 23\n"+
            "AND COMM_UTILITY_DURTAPE.ATTR_ID = 22\n" +
            "AND COMM_UTILITY_DURTAPE.LIST_VALUE_ID = COMM_LIST.LIST_VALUE_ID\n" +
            "AND COMM_LIST.ATTR_ID = 22";

    String GET_ALL_MANAGER_OPERATION_BY_SUBBILL_ID = "SELECT ATTR_OPER_DESC.OBJECT_ID manager_spending_id, ATTR_OPER_DESC.VALUE manager_spending_description,\n" +
            "ATTR_OPER_SUM.VALUE manager_spending_sum, OBJ_MANSUBBIL.REFERENCE manager_sub_bill,MANAGER_SUB_BILL.PARENT_ID communal_utility_id,\n" +
            "COMM_LIST_STATUS.VALUE status,  COMM_UTILITY_NAME.VALUE name_communal, COMM_LIST.VALUE duration_type, MANAGER_BILL_BALANCE.VALUE MANAGER_SUBBIL_BALANCE,\n" +
            "ATTR_OPER_CREATED_AT.DATE_VALUE operation_created_at\n" +
            "FROM OBJREFERENCE OBJ_MANSUBBIL, ATTRIBUTES ATTR_OPER_SUM,\n" +
            "ATTRIBUTES ATTR_OPER_DESC, ATTRIBUTES ATTR_OPER_CREATED_AT,OBJECTS MANAGER_SUB_BILL, LISTS COMM_LIST,\n" +
            "ATTRIBUTES COMM_UTILITY_NAME, ATTRIBUTES COMM_UTILITY_DURTAPE, ATTRIBUTES COMM_UTILITY_STATUS, ATTRIBUTES MANAGER_BILL_BALANCE,\n" +
            "LISTS COMM_LIST_STATUS\n" +
            "WHERE OBJ_MANSUBBIL.REFERENCE = ? \n" +
            "AND OBJ_MANSUBBIL.ATTR_ID = 36\n" +
            "AND OBJ_MANSUBBIL.OBJECT_ID = ATTR_OPER_SUM.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_DESC.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_CREATED_AT.OBJECT_ID\n" +
            "AND ATTR_OPER_SUM.ATTR_ID = 26\n" +
            "AND ATTR_OPER_CREATED_AT.ATTR_ID = 27\n" +
            "AND ATTR_OPER_DESC.ATTR_ID = 28\n" +
            "AND MANAGER_SUB_BILL.OBJECT_ID = MANAGER_BILL_BALANCE.OBJECT_ID\n" +
            "AND MANAGER_BILL_BALANCE.ATTR_ID = 25\n" +
            "AND MANAGER_SUB_BILL.OBJECT_ID = OBJ_MANSUBBIL.REFERENCE\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_NAME.OBJECT_ID\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_DURTAPE.OBJECT_ID\n" +
            "AND MANAGER_SUB_BILL.PARENT_ID =COMM_UTILITY_STATUS.OBJECT_ID\n" +
            "AND COMM_UTILITY_NAME.ATTR_ID = 21\n" +
            "AND COMM_UTILITY_STATUS.ATTR_ID = 23\n" +
            "AND COMM_UTILITY_STATUS.LIST_VALUE_ID = COMM_LIST_STATUS.LIST_VALUE_ID\n" +
            "AND COMM_LIST_STATUS.ATTR_ID = 23\n" +
            "AND COMM_UTILITY_DURTAPE.ATTR_ID = 22\n" +
            "AND COMM_UTILITY_DURTAPE.LIST_VALUE_ID = COMM_LIST.LIST_VALUE_ID\n" +
            "AND COMM_LIST.ATTR_ID = 22";

   String UPDATE_MANAGER_OPERATION = "MERGE INTO ATTRIBUTES ATTR_OPERATION\n" +
            "    USING (SELECT 28 ATTR_ID,? VALUE FROM DUAL\n" +
            "        UNION ALL\n" +
            "        SELECT 26,? FROM DUAL) UPD\n" +
            "    ON(ATTR_OPERATION.OBJECT_ID = ? AND UPD.ATTR_ID = ATTR_OPERATION.ATTR_ID)\n" +
            "    WHEN MATCHED THEN\n" +
            "        UPDATE SET ATTR_OPERATION.VALUE = UPD.VALUE\n" +
            "    WHERE ATTR_OPERATION.VALUE <> UPD.VALUE";

   String GET_MANAGER_OPERATION_SPENDING_BY_ID = "SELECT ATTR_OPER_DESC.OBJECT_ID manager_spending_id, ATTR_OPER_DESC.VALUE manager_spending_description,\n" +
           "           ATTR_OPER_SUM.VALUE manager_spending_sum, OBJ_MANSUBBIL.REFERENCE manager_sub_bill,MANAGER_SUB_BILL.PARENT_ID communal_utility_id,\n" +
           "           COMM_LIST_STATUS.VALUE status,  COMM_UTILITY_NAME.VALUE name_communal, COMM_LIST.VALUE duration_type, MANAGER_BILL_BALANCE.VALUE MANAGER_SUBBIL_BALANCE,\n" +
           "           ATTR_OPER_CREATED_AT.DATE_VALUE operation_created_at\n" +
           "           FROM OBJREFERENCE OBJ_MANSUBBIL, ATTRIBUTES ATTR_OPER_SUM,\n" +
           "           ATTRIBUTES ATTR_OPER_DESC, ATTRIBUTES ATTR_OPER_CREATED_AT,OBJECTS MANAGER_SUB_BILL, LISTS COMM_LIST,\n" +
           "           ATTRIBUTES COMM_UTILITY_NAME, ATTRIBUTES COMM_UTILITY_DURTAPE, ATTRIBUTES COMM_UTILITY_STATUS, ATTRIBUTES MANAGER_BILL_BALANCE,\n" +
           "           LISTS COMM_LIST_STATUS\n" +
           "            WHERE OBJ_MANSUBBIL.OBJECT_ID = ?\n" +
           "            AND OBJ_MANSUBBIL.ATTR_ID = 36\n" +
           "            AND OBJ_MANSUBBIL.OBJECT_ID = ATTR_OPER_SUM.OBJECT_ID\n" +
           "            AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_DESC.OBJECT_ID\n" +
           "            AND ATTR_OPER_SUM.OBJECT_ID = ATTR_OPER_CREATED_AT.OBJECT_ID\n" +
           "            AND ATTR_OPER_SUM.ATTR_ID = 26\n" +
           "            AND ATTR_OPER_CREATED_AT.ATTR_ID = 27\n" +
           "            AND ATTR_OPER_DESC.ATTR_ID = 28\n" +
           "            AND MANAGER_SUB_BILL.OBJECT_ID = MANAGER_BILL_BALANCE.OBJECT_ID\n" +
           "            AND MANAGER_BILL_BALANCE.ATTR_ID = 25\n" +
           "            AND MANAGER_SUB_BILL.OBJECT_ID = OBJ_MANSUBBIL.REFERENCE\n" +
           "            AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_NAME.OBJECT_ID\n" +
           "            AND MANAGER_SUB_BILL.PARENT_ID = COMM_UTILITY_DURTAPE.OBJECT_ID\n" +
           "            AND MANAGER_SUB_BILL.PARENT_ID =COMM_UTILITY_STATUS.OBJECT_ID\n" +
           "            AND COMM_UTILITY_NAME.ATTR_ID = 21\n" +
           "            AND COMM_UTILITY_STATUS.ATTR_ID = 23\n" +
           "            AND COMM_UTILITY_STATUS.LIST_VALUE_ID = COMM_LIST_STATUS.LIST_VALUE_ID\n" +
           "            AND COMM_LIST_STATUS.ATTR_ID = 23\n" +
           "            AND COMM_UTILITY_DURTAPE.ATTR_ID = 22\n" +
           "            AND COMM_UTILITY_DURTAPE.LIST_VALUE_ID = COMM_LIST.LIST_VALUE_ID\n" +
           "            AND COMM_LIST.ATTR_ID = 22";

    List<ManagerSpendingOperation> getAllManagerOperationSpendingByManagerSubBill(BigInteger id);
    List<ManagerSpendingOperation> getAllManagerOperationSpending();
    ManagerSpendingOperation getManagerOperationSpendingById(BigInteger id);
    void updateManagerOperationSpending(ManagerSpendingOperation managerOperationSpending);
    void createManagerOperationSpending(ManagerSpendingOperation managerOperationSpending);

}
