package com.netcracker.dao;

import com.netcracker.models.Apartment;

import java.math.BigInteger;
import java.util.List;

public interface ApartmentDao {

    List<Apartment> getAllApartments();

    Apartment getApartmentById(BigInteger apartmentId);

    void createApartment(Apartment apartment);

    void updateApartment(Apartment apartment);

    String GET_ALL_APARTMENTS = "SELECT APRT.OBJECT_ID account_id," +
            "                   APTNUM.VALUE   apartment_number," +
            "                   SQUARE.VALUE   square_metres," +
            "                   FLOOR.VALUE    floor," +
            "                   PPLCOUNT.VALUE people_count," +
            "                   EMAIL.VALUE    email," +
            "                   PASW.VALUE     password," +
            "                   FNAME.VALUE    first_name," +
            "                   LNAME.VALUE    last_name," +
            "                   PHNUM.VALUE    phone_number," +
            "                   ROLE.VALUE  role_name" +
            "            FROM OBJECTS APRT," +
            "                 ATTRIBUTES APTNUM," +
            "                 ATTRIBUTES SQUARE," +
            "                 ATTRIBUTES FLOOR," +
            "                 ATTRIBUTES PPLCOUNT," +
            "                 ATTRIBUTES EMAIL," +
            "                 ATTRIBUTES PASW," +
            "                 ATTRIBUTES FNAME," +
            "                 ATTRIBUTES LNAME," +
            "                 ATTRIBUTES PHNUM," +
            "                 ATTRIBUTES ROLE" +
            "            WHERE APRT.OBJECT_TYPE_ID = 7" +
            "              AND APTNUM.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND APTNUM.ATTR_ID = 15" +
            "              AND SQUARE.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND SQUARE.ATTR_ID = 16" +
            "              AND FLOOR.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND FLOOR.ATTR_ID = 17" +
            "              AND PPLCOUNT.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND PPLCOUNT.ATTR_ID = 18" +
            "              AND EMAIL.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND EMAIL.ATTR_ID = 2" +
            "              AND PASW.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND PASW.ATTR_ID = 3" +
            "              AND FNAME.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND FNAME.ATTR_ID = 4" +
            "              AND LNAME.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND LNAME.ATTR_ID = 5" +
            "              AND PHNUM.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND PHNUM.ATTR_ID = 6" +
            "              AND ROLE.OBJECT_ID = APRT.OBJECT_ID" +
            "              AND ROLE.ATTR_ID = 1;" ;
    String GET_APARTMENT_BY_ID = "" +
            "SELECT APRT.OBJECT_ID account_id," +
            "       APTNUM.VALUE   apartment_number," +
            "       SQUARE.VALUE   square," +
            "       FLOOR.VALUE    floor," +
            "       PPLCOUNT.VALUE people_count," +
            "       EMAIL.VALUE    email," +
            "       PASW.VALUE     password," +
            "       FNAME.VALUE    first_name," +
            "       LNAME.VALUE    last_name," +
            "       PHNUM.VALUE    phone_number," +
            "       ROLE.VALUE role_name" +
            "FROM OBJECTS APRT," +
            "     ATTRIBUTES APTNUM," +
            "     ATTRIBUTES SQUARE," +
            "     ATTRIBUTES FLOOR," +
            "     ATTRIBUTES PPLCOUNT," +
            "     ATTRIBUTES EMAIL," +
            "     ATTRIBUTES PASW," +
            "     ATTRIBUTES FNAME," +
            "     ATTRIBUTES LNAME," +
            "     ATTRIBUTES PHNUM," +
            "     ATTRIBUTES ROLE" +
            "WHERE APRT.OBJECT_ID = ?" +
            "  AND APRT.OBJECT_TYPE_ID = 7" +
            "  AND APTNUM.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND APTNUM.ATTR_ID = 15" +
            "  AND SQUARE.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND SQUARE.ATTR_ID = 16" +
            "  AND FLOOR.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND FLOOR.ATTR_ID = 17" +
            "  AND PPLCOUNT.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND PPLCOUNT.ATTR_ID = 18" +
            "  AND EMAIL.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND EMAIL.ATTR_ID = 2" +
            "  AND PASW.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND PASW.ATTR_ID = 3" +
            "  AND FNAME.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND FNAME.ATTR_ID = 4" +
            "  AND LNAME.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND LNAME.ATTR_ID = 5" +
            "  AND PHNUM.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND PHNUM.ATTR_ID = 6" +
            "  AND ROLE.OBJECT_ID = APRT.OBJECT_ID" +
            "  AND ROLE.ATTR_ID = 1;";
    String CREATE_APARTMENT = "" +
            "INSERT ALL" +
            "    INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)" +
            "VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (1, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (2, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (3, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (4, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (5, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (6, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (15, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (16, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (17, obj_id_seq.currval, ?)" +
            "INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)" +
            "VALUES (18, obj_id_seq.currval, ?)" +
            "SELECT *" +
            "FROM DUAL;";
    String UPDATE_APARTMENT = "" +
            "MERGE INTO ATTRIBUTES x" +
            "USING (" +
            "    SELECT 15 ATTR_ID, ? VALUE FROM DUAL" +
            "       UNION ALL" +
            "       SELECT 16, ? FROM DUAL" +
            "       UNION ALL" +
            "       SELECT 17, ?  FROM DUAL" +
            "       UNION ALL" +
            "       SELECT 18, ? FROM DUAL" +
            "    ) y" +
            "ON (x.OBJECT_ID = ? AND x.ATTR_ID=y.ATTR_ID)" +
            "WHEN MATCHED THEN" +
            "    UPDATE" +
            "    SET x.VALUE   = y.VALUE" +
            "    WHERE  x.VALUE <> y.VALUE;";
}
