
/*Test data for table ApartmentOperationDao*/
-- ApartmentOperations for Apartment_Sub_Bill_1 for Apartment_1

-- ApartmentOperation1
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,17,'Apartment_Operation1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, '1000');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '01.01.2001');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (34, obj_id_seq.currval, 2);

-- ApartmentOperation2
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,17,'Apartment_Operation2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, '2000');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '02.02.2002');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (34, obj_id_seq.currval, 2);


-----------------------------------------Create Manager Operation Spending----------------------------------------------
INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod'||obj_id_seq.currval,null)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,1);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null,3 );
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-12');

INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,14,'MANAGERSUBBIL_'||obj_id_seq.currval,NULL)
        INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(25,obj_id_seq.currval,'9999991'||obj_id_seq.currval,null,null)
select * from dual;

INSERT ALL
            INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (OBJ_ID_SEQ.nextval,null,18,'ManagerOperationSpending_'||OBJ_ID_SEQ.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
            INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
            VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
SELECT * FROM DUAL;

----------------
INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod'||obj_id_seq.currval,null)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null, 3 );
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-3');

INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,14,'MANAGERSUBBIL_'||obj_id_seq.currval,NULL)
        INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(25,obj_id_seq.currval,'9999998'||obj_id_seq.currval,null,null)
select * from dual;

INSERT ALL
            INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (OBJ_ID_SEQ.nextval,NULL,18,'ManagerOperationSpending_'||OBJ_ID_SEQ.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
            INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
            VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
SELECT * FROM DUAL;
--------
INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod'||obj_id_seq.currval,null)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null,4);
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-6');

INSERT ALL
        INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,14,'MANAGERSUBBIL_'||obj_id_seq.currval,NULL)
        INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
        VALUES(25,obj_id_seq.currval,'9999900'||obj_id_seq.currval,null,null)
select * from dual;


INSERT ALL
            INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (OBJ_ID_SEQ.nextval,NULL,18,'ManagerOperationSpending_'||OBJ_ID_SEQ.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
            INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
            VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
SELECT * FROM DUAL;
commit ;
---------------------------------------------------------END------------------------------------------------------------

-----------------------------------------Test data for Apartments and ApartmentSubBills----------------------------------------------
--Apartment 1
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_1', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'masha@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '1234567');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Mariia');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Kozhushan');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '10');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '2');

-- SubBills for Apartment_1
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility1'), 13, 'SubBill_11', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '340.50');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '300');


-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-1);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility2'), 13, 'SubBill_12', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '127.80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-2);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility3'), 13, 'SubBill_13', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '569');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '500');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-3);

-- Apartment2
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_2', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'dasha@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '12345457');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Dasha');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Markovkina');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '3456456887');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '11');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '44');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '2');

-- SubBills for Apartment2
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility1'), 13, 'SubBill_21', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '112');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '1678');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-1);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility2'), 13, 'SubBill_22', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '67');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '120');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-2);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility3'), 13, 'SubBill_23', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '890');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '80');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-3);
-- Apartment3
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_3', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'artem@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '888005553535');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Igor');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Yabluchkin');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '9876543679');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '12');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '120');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '4');

-- SubBills for Apartment3
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility1'), 13, 'SubBill_31', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '321');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '800');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-1);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility2'), 13, 'SubBill_32', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '0');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '250');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-2);

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility3'), 13, 'SubBill_33', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,obj_id_seq.currval-3);
-------------------------------------------------------END-------------------------------------------------------------

------------------------------------CREATE COMMENTS, ANNOUNCEMENTS AND APARTMENTS---------------------------------------
INSERT ALL
            INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (obj_id_seq.nextval,NULL,3,'Announcement_' || obj_id_seq.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(9,obj_id_seq.currval, 'TRUE')
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(10,obj_id_seq.currval, SYSDATE )
sELECT * FROM DUAL;



INSERT
        ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_'||OBJ_ID_SEQ.currval,NULL)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
         INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
sELECT * FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, ?, 7, 'Apartment_' || obj_id_seq.currval, NULL); -- 3 - ? ROLE OBJECT_ID
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (2, obj_id_seq.currval, 'vvv@gmail.com'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (3, obj_id_seq.currval, '1234567'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (4, obj_id_seq.currval, 'vasya'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (5, obj_id_seq.currval, 'pupkin'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (15, obj_id_seq.currval, '10'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (17, obj_id_seq.currval, '1'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
        VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

INSERT ALL
            INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (obj_id_seq.nextval,NULL,3,'Announcement_' || obj_id_seq.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(9,obj_id_seq.currval, 'TRUE')
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(10,obj_id_seq.currval, SYSDATE )
sELECT * FROM DUAL;



INSERT
        ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_'||OBJ_ID_SEQ.currval,NULL)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
         INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
sELECT * FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, ?, 7, 'Apartment_' || obj_id_seq.currval, NULL); -- 3 - ? ROLE OBJECT_ID
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (2, obj_id_seq.currval, 'vvv@gmail.com'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (3, obj_id_seq.currval, '1234567'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (4, obj_id_seq.currval, 'vasya'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (5, obj_id_seq.currval, 'pupkin'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (15, obj_id_seq.currval, '10'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (17, obj_id_seq.currval, '1'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
        VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

INSERT ALL
            INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (obj_id_seq.nextval,NULL,3,'Announcement_' || obj_id_seq.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(9,obj_id_seq.currval, 'TRUE')
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(10,obj_id_seq.currval, SYSDATE )
sELECT * FROM DUAL;



INSERT
        ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_'||OBJ_ID_SEQ.currval,NULL)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
         INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
sELECT * FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, ?, 7, 'Apartment_' || obj_id_seq.currval, NULL); -- 3 - ? ROLE OBJECT_ID
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (2, obj_id_seq.currval, 'vvv@gmail.com'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (3, obj_id_seq.currval, '1234567'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (4, obj_id_seq.currval, 'vasya'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (5, obj_id_seq.currval, 'pupkin'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (15, obj_id_seq.currval, '10'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (17, obj_id_seq.currval, '1'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
        VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

INSERT ALL
            INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
            VALUES (obj_id_seq.nextval,NULL,3,'Announcement_' || obj_id_seq.currval,NULL)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(9,obj_id_seq.currval, 'TRUE')
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
            VALUES(10,obj_id_seq.currval, SYSDATE )
sELECT * FROM DUAL;



INSERT
        ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_'||OBJ_ID_SEQ.currval,NULL)
        INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
         INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
        VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
sELECT * FROM DUAL;

        INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
        VALUES (obj_id_seq.nextval, ?, 7, 'Apartment_' || obj_id_seq.currval, NULL); -- 3 - ? ROLE OBJECT_ID
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (2, obj_id_seq.currval, 'vvv@gmail.com'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (3, obj_id_seq.currval, '1234567'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (4, obj_id_seq.currval, 'vasya'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (5, obj_id_seq.currval, 'pupkin'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (15, obj_id_seq.currval, '10'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (17, obj_id_seq.currval, '1'||obj_id_seq.currval);
        INSERT INTO ATTRIBUTES(attr_id, object_id, value)
        VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
        VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

commit ;

-----------------------------------------------END----------------------------------------------------------------------




