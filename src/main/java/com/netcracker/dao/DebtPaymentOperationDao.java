package com.netcracker.dao;

import com.netcracker.models.DebtPaymentOperation;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface DebtPaymentOperationDao {
    String insertDebtPaymentOperation =
            "INSERT ALL\n" +
                    "INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) VALUES (OBJ_ID_SEQ.nextval, NULL, 16, 'DebtPaymentOperation' || OBJ_ID_SEQ.currval, NULL)\n" +
                    "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID) VALUES (26, OBJ_ID_SEQ.currval, ?, NULL, NULL)\n" +
                    "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID) VALUES (27, OBJ_ID_SEQ.currval, NULL, SYSDATE, NULL)\n" +
                    "INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE) VALUES (35, OBJ_ID_SEQ.currval, ?)\n" +
                    "INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE) VALUES (37, OBJ_ID_SEQ.currval, ?)\n" +
                    "SELECT * FROM DUAL";

    String selectDebtPaymentOperationByApartmentSubBillID =
            "SELECT DPOP.OBJECT_ID operation_id, DPOP_SUM.VALUE sum,\n" +
                    "DPOP_CREATED_AT.DATE_VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "FROM OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "WHERE DPOP.OBJECT_TYPE_ID = 16\n" +
                    "AND DPOP_SUM.ATTR_ID = 26\n" +
                    "AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_PAYS.REFERENCE = ?\n" +
                    "AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String selectDebtPaymentOperationByApartmentId =
            "SELECT  DPOP.OBJECT_ID operation_id, DPOP_SUM.VALUE sum,\n" +
                    "       DPOP_CREATED_AT.DATE_VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "       DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "FROM OBJECTS APSB, OBJREFERENCE APSB_APARTMENT_NUMBER,\n" +
                    "     OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "WHERE APSB.OBJECT_TYPE_ID = 13\n" +
                    "AND APSB_APARTMENT_NUMBER.ATTR_ID = 33\n" +
                    "AND APSB_APARTMENT_NUMBER.REFERENCE = ?\n" +
                    "AND APSB_APARTMENT_NUMBER.OBJECT_ID = APSB.OBJECT_ID\n" +
                    "AND DPOP.OBJECT_TYPE_ID = 16\n" +
                    "AND DPOP_SUM.ATTR_ID = 26\n" +
                    "AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_PAYS.REFERENCE = APSB.OBJECT_ID\n" +
                    "AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String selectDebtPaymentOperationByManagerSubBillId =
            "SELECT DPOP.OBJECT_ID operation_id, DPOP_SUM.VALUE sum,\n" +
                    "    DPOP_CREATED_AT.DATE_VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "    DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "    FROM OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "    WHERE DPOP.OBJECT_TYPE_ID = 16\n" +
                    "    AND DPOP_SUM.ATTR_ID = 26\n" +
                    "    AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "    AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "    AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "    AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_RECEIVES.REFERENCE = ?\n" +
                    "    ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String selectDebtPaymentOperationByDateRangeAndApartmentId =
            "SELECT DPOP.OBJECT_ID operation_id, DPOP_SUM.VALUE sum,\n" +
                    "       DPOP_CREATED_AT.DATE_VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "       DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "FROM OBJECTS APSB, OBJREFERENCE APSB_APARTMENT_NUMBER,\n" +
                    "     OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "WHERE APSB.OBJECT_TYPE_ID = 13\n" +
                    "AND APSB_APARTMENT_NUMBER.ATTR_ID = 33\n" +
                    "AND APSB_APARTMENT_NUMBER.REFERENCE = ?\n" +
                    "AND APSB_APARTMENT_NUMBER.OBJECT_ID = APSB.OBJECT_ID\n" +
                    "AND DPOP.OBJECT_TYPE_ID = 16\n" +
                    "AND DPOP_SUM.ATTR_ID = 26\n" +
                    "AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_CREATED_AT.DATE_VALUE BETWEEN ? AND ?\n" +
                    "AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "AND DPOP_PAYS.REFERENCE = APSB.OBJECT_ID\n" +
                    "AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String selectDebtPaymentOperationByDateRangeAndApartmentSubBillId =
            "SELECT DPOP.OBJECT_ID operation_id, DPOP_SUM.DATE_VALUE sum,\n" +
                    "    DPOP_CREATED_AT.VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "    DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "    FROM OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "    WHERE DPOP.OBJECT_TYPE_ID = 16\n" +
                    "    AND DPOP_SUM.ATTR_ID = 26\n" +
                    "    AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "    AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_PAYS.REFERENCE = ?\n" +
                    "    AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "    AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_CREATED_AT.DATE_VALUE BETWEEN ? AND ?\n" +
                    "    AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "    AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String selectDebtPaymentOperationByDateRangeAndManagerSubBillId =
            "SELECT DPOP.OBJECT_ID operation_id, DPOP_SUM.DATE_VALUE sum,\n" +
                    "    DPOP_CREATED_AT.VALUE created_at, DPOP_PAYS.REFERENCE apartment_sub_bill_id,\n" +
                    "    DPOP_RECEIVES.REFERENCE manager_sub_bill_id\n" +
                    "    FROM OBJECTS DPOP, ATTRIBUTES DPOP_SUM, ATTRIBUTES DPOP_CREATED_AT, OBJREFERENCE DPOP_PAYS, OBJREFERENCE DPOP_RECEIVES\n" +
                    "    WHERE DPOP.OBJECT_TYPE_ID = 16\n" +
                    "    AND DPOP_SUM.ATTR_ID = 26\n" +
                    "    AND DPOP_SUM.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_RECEIVES.ATTR_ID = 37\n" +
                    "    AND DPOP_RECEIVES.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_RECEIVES.REFERENCE = ?\n" +
                    "    AND DPOP_CREATED_AT.ATTR_ID = 27\n" +
                    "    AND DPOP_CREATED_AT.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    AND DPOP_CREATED_AT.DATE_VALUE BETWEEN ? AND ?\n" +
                    "    AND DPOP_PAYS.ATTR_ID = 35\n" +
                    "    AND DPOP_PAYS.OBJECT_ID = DPOP.OBJECT_ID\n" +
                    "    ORDER BY DPOP_CREATED_AT.DATE_VALUE";

    String EXCEPTION_INSERT_DEBT_PAYMENT_OPERATION = "Can't insert debt payment operation";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_APARTMENT_ID = "Couldn't find debt payment operation with apartment_id";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_APARTMENT_SUB_BILL_ID = "Couldn't find debt payment operation with apartment_sub_bill_id";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_MANAGER_SUB_BILL_ID = "Couldn't find debt payment operation with manager_sub_bill_id";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_APARTMENT_ID = "Couldn't find Debt payment operation within date range and with apartment_id";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_APARTMENT_SUB_BILL_ID = "Couldn't find Debt payment operation within date range and with apartment_sub_bill_id";
    String EXCEPTION_GET_DEBT_PAYMENT_OPERATION_BY_DATE_RANGE_AND_MANAGER_SUB_BILL_ID = "Couldn't find Debt payment operation within date range and with manager_sub_bill_id";

    List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentId(BigInteger apartmentId);

    List<DebtPaymentOperation> getDebtPaymentOperationsByApartmentSubBillId(BigInteger apartmentSubBillId);

    List<DebtPaymentOperation> getDebtPaymentOperationsByManagerSubBillId(BigInteger managerSubBilId);

    List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentId(Date from, Date to, BigInteger apartmentId);

    List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndApartmentSubBillId(Date from, Date to, BigInteger apartmentSubBillId);

    List<DebtPaymentOperation> getDebtPaymentOperationsByDateRangeAndManagerSubBillId(Date from, Date to, BigInteger managerSubBillId);

    void createDebtPaymentOperation(DebtPaymentOperation debtPaymentOperation);
}
