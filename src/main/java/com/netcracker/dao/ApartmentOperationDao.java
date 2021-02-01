package com.netcracker.dao;


import com.netcracker.models.ApartmentOperation;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface ApartmentOperationDao {
    String selectApartmentOperationsBySubBillId =
            "SELECT APOP.OBJECT_ID operation_id, APOP_SUM.VALUE sum, APOP_CREATED_AT.DATE_VALUE created_at, APOP_TRANSFERS.REFERENCE apartment_sub_bill_id\n" +
                    "                    FROM OBJECTS APOP, ATTRIBUTES APOP_SUM, ATTRIBUTES APOP_CREATED_AT, OBJREFERENCE APOP_TRANSFERS\n" +
                    "                    WHERE APOP.OBJECT_TYPE_ID = 17\n" +
                    "                      AND APOP_SUM.ATTR_ID = 26\n" +
                    "                      AND APOP_SUM.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                      AND APOP_CREATED_AT.ATTR_ID = 27\n" +
                    "                      AND APOP_CREATED_AT.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                      AND APOP_TRANSFERS.ATTR_ID = 34\n" +
                    "                      AND APOP_TRANSFERS.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                      AND APOP_TRANSFERS.REFERENCE = ?\n" +
                    "                    ORDER BY APOP_CREATED_AT.DATE_VALUE";

    String selectApartmentOperationsByApartmentId =
            " SELECT COMMUNAL_UTILITY_NAME.VALUE communal_name,APOP.OBJECT_ID operation_id, APOP_SUM.VALUE sum, APOP_CREATED_AT.DATE_VALUE created_at, APOP_TRANSFERS.REFERENCE apartment_sub_bill_id\n" +
                    "                    FROM OBJECTS APSB, OBJREFERENCE APSB_APARTMENT_NUMBER,\n" +
                    "                         OBJECTS APOP, ATTRIBUTES APOP_SUM, ATTRIBUTES APOP_CREATED_AT, OBJREFERENCE APOP_TRANSFERS,ATTRIBUTES COMMUNAL_UTILITY_NAME, OBJECTS COMMUNAL_UTILITY\n" +
                    "                    WHERE APSB.OBJECT_TYPE_ID = 13\n" +
                    "                    AND APSB_APARTMENT_NUMBER.ATTR_ID = 33\n" +
                    "                    AND APSB_APARTMENT_NUMBER.REFERENCE = ?\n" +
                    "                    AND APSB_APARTMENT_NUMBER.OBJECT_ID = APSB.OBJECT_ID\n" +
                    "                    AND APOP.OBJECT_TYPE_ID = 17\n" +
                    "                    AND APOP_SUM.ATTR_ID = 26\n" +
                    "                    AND APOP_SUM.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                    AND APOP_CREATED_AT.ATTR_ID = 27\n" +
                    "                    AND APOP_CREATED_AT.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                    AND APOP_TRANSFERS.ATTR_ID = 34\n" +
                    "                    AND APOP_TRANSFERS.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "                    AND APOP_TRANSFERS.REFERENCE = APSB.OBJECT_ID\n" +
                    "                    AND APSB.PARENT_ID = COMMUNAL_UTILITY.OBJECT_ID\n" +
                    "                    AND COMMUNAL_UTILITY.OBJECT_TYPE_ID = 11\n" +
                    "                    AND COMMUNAL_UTILITY_NAME.OBJECT_ID = COMMUNAL_UTILITY.OBJECT_ID\n" +
                    "                    AND COMMUNAL_UTILITY_NAME.ATTR_ID = 21\n" +
                    "                    ORDER BY APOP_CREATED_AT.DATE_VALUE";

    String insertApartmentOperation =
            "INSERT ALL\n" +
                    "INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION) VALUES (OBJ_ID_SEQ.nextval, NULL, 17, 'AparmentOperation_'||OBJ_ID_SEQ.currval, NULL)\n" +
                    "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID) VALUES (26, OBJ_ID_SEQ.currval, ?, NULL, NULL)\n" +
                    "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID) VALUES (27, OBJ_ID_SEQ.currval, NULL, SYSDATE, NULL)\n" +
                    "INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE) VALUES (34, OBJ_ID_SEQ.currval, ?)\n" +
                    "SELECT * FROM DUAL\n";

    String selectApartmentOperationsByDateRangeAndApartmentNumber =
            "SELECT COMMUNAL_UTILITY_NAME.VALUE communal_name, APOP.OBJECT_ID operation_id, APOP_SUM.VALUE sum, APOP_CREATED_AT.DATE_VALUE created_at, APOP_TRANSFERS.REFERENCE apartment_sub_bill_id\n" +
                    "FROM OBJECTS APSB, OBJREFERENCE APSB_APARTMENT_NUMBER,\n" +
                    "OBJECTS APOP, ATTRIBUTES APOP_SUM, ATTRIBUTES APOP_CREATED_AT, OBJREFERENCE APOP_TRANSFERS,\n" +
                    "ATTRIBUTES COMMUNAL_UTILITY_NAME, OBJECTS COMMUNAL_UTILITY,\n" +
                    "ATTRIBUTES NUMBER_OF_APARTMENT_VALUE\n" +
                    "WHERE APSB.OBJECT_TYPE_ID = 13\n" +
                    "AND NUMBER_OF_APARTMENT_VALUE.VALUE = ?\n" +
                    "AND NUMBER_OF_APARTMENT_VALUE.ATTR_ID = 15\n" +
                    " AND NUMBER_OF_APARTMENT_VALUE.OBJECT_ID = APSB_APARTMENT_NUMBER.REFERENCE\n" +
                    "AND APSB_APARTMENT_NUMBER.ATTR_ID = 33\n" +
                    "AND APSB_APARTMENT_NUMBER.OBJECT_ID = APSB.OBJECT_ID\n" +
                    "AND APOP.OBJECT_TYPE_ID = 17\n" +
                    "AND APOP_SUM.ATTR_ID = 26\n" +
                    "AND APOP_SUM.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_TRANSFERS.ATTR_ID = 34\n" +
                    "AND APOP_TRANSFERS.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_TRANSFERS.REFERENCE = APSB.OBJECT_ID\n" +
                    "AND APOP_CREATED_AT.ATTR_ID = 27\n" +
                    "AND APOP_CREATED_AT.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_CREATED_AT.DATE_VALUE BETWEEN ? AND ?\n" +
                    "AND APSB.PARENT_ID = COMMUNAL_UTILITY.OBJECT_ID\n" +
                    "AND COMMUNAL_UTILITY.OBJECT_TYPE_ID = 11\n" +
                    "AND COMMUNAL_UTILITY_NAME.OBJECT_ID = COMMUNAL_UTILITY.OBJECT_ID\n" +
                    "AND COMMUNAL_UTILITY_NAME.ATTR_ID = 21\n" +
                    "ORDER BY APOP_CREATED_AT.DATE_VALUE";

    String selectApartmentOperationsByDateRangeAndApartmentSubBillId =
            "SELECT APOP.OBJECT_ID operation_id, APOP_SUM.VALUE sum, APOP_CREATED_AT.DATE_VALUE created_at, APOP_TRANSFERS.REFERENCE apartment_sub_bill_id\n" +
                    "FROM OBJECTS APOP, ATTRIBUTES APOP_SUM, ATTRIBUTES APOP_CREATED_AT, OBJREFERENCE APOP_TRANSFERS\n" +
                    "WHERE APOP.OBJECT_TYPE_ID = 17\n" +
                    "AND APOP_SUM.ATTR_ID = 26\n" +
                    "AND APOP_SUM.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_TRANSFERS.ATTR_ID = 34\n" +
                    "AND APOP_TRANSFERS.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_TRANSFERS.REFERENCE = ?\n" +
                    "AND APOP_CREATED_AT.ATTR_ID = 27\n" +
                    "AND APOP_CREATED_AT.OBJECT_ID = APOP.OBJECT_ID\n" +
                    "AND APOP_CREATED_AT.DATE_VALUE BETWEEN ? AND ?\n" +
                    "ORDER BY APOP_CREATED_AT.DATE_VALUE";

    String EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_SUB_BILL_ID = "Couldn't find Apartment operation with sub_bill_id";
    String EXCEPTION_GET_APARTMENT_OPERATIONS_BY_APARTMENT_ID = "Couldn't find Apartment operation with sub_bill_id";
    String EXCEPTION_INSERT_APARTMENT_OPERATION = "Can't insert Apartment Operation";
    String EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_ID = "Couldn't find Apartment operation within date range and with apartment_id";
    String EXCEPTION_GET_APARTMENT_OPERATIONS_BY_DATE_RANGE_AND_APARTMENT_SUB_BILL_ID = "Couldn't find Apartment operation within date range and with apartment_sub_bill_id";

    List<ApartmentOperation> getAllApartmentOperationsBySubBillId(BigInteger subBillId);

    List<ApartmentOperation> getAllApartmentOperationsByApartmentId(BigInteger apartmentId);

    void createApartmentOperation(ApartmentOperation apartmentOperation);

    List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentNumber(Integer apartmentNumber, Date from, Date to);

    List<ApartmentOperation> getApartmentOperationsByDateRangeAndApartmentSubBillId(BigInteger ApartmentSubBillId, Date from, Date to);
}
