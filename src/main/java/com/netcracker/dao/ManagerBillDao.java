package com.netcracker.dao;

import com.netcracker.models.ManagerBill;

import java.math.BigInteger;

public interface ManagerBillDao {


    String GET_MANAGER_BILL_BY_ID = "SELECT ATTRSUBBILL.VALUE sub_bill_number,\n" +
            "ATTRSUBBILL.OBJECT_ID sub_bill_id, REF.OBJECT_ID manager_id\n" +
            "FROM ATTRIBUTES ATTRSUBBILL,OBJREFERENCE REF\n" +
            "WHERE ATTRSUBBILL.OBJECT_ID = ?\n" +
            "AND REF.REFERENCE = ATTRSUBBILL.OBJECT_ID\n" +
            "AND REF.ATTR_ID = 31\n" +
            "AND ATTRSUBBILL.ATTR_ID = 19";

    String CREATE_MANAGER_BILL = "INSERT ALL\n" +
            "INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)\n" +
            "VALUES (obj_id_seq.nextval,NULL,?,'ManagerBill_'|| obj_id_seq.currval,null)\n" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)\n" +
            "VALUES (?,obj_id_seq.currval,?,SYSDATE,null)\n" +
            "INTO OBJREFERENCE(attr_id, object_id, reference)\n" +
            "VALUES (?,?,obj_id_seq.currval)\n" +
            "SELECT * FROM dual";


    String UPDATE_MANAGER_BILL = "MERGE INTO ATTRIBUTES ATTR_BILL\n" +
            "USING (SELECT 19 ATTR_ID,? VALUE FROM DUAL) UPD\n" +
            "ON(ATTR_BILL.OBJECT_ID = ? AND UPD.ATTR_ID = ATTR_BILL.ATTR_ID)\n" +
            "WHEN MATCHED THEN\n" +
            "UPDATE SET ATTR_BILL.VALUE = UPD.VALUE\n" +
            "WHERE ATTR_BILL.VALUE <> UPD.VALUE";


    String EXCEPTION_GET_MANAGER_BILL_BY_ID = "Cant find Manager Bill with id ";
    String EXCEPTION_UPDATE_MANAGER_BILL = "Cant update Manager Bill";
    String EXCEPTION_CREATE_MANAGER_BILL = "Cant create Manager Bill";

    ManagerBill getManagerBillById(BigInteger id);

    void updateManagerBill(ManagerBill managerBill);

    void createManagerBill(ManagerBill managerBill);
}
