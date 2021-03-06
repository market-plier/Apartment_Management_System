-------------------------------------------------Test data for Apartments ---------------------------------------------
--Apartment 1
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_1', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'mariakozusan@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '91f1771840b4b0b71d70338e9259e14fbe2de8a58260cde750a46321333ed7c2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Mariia');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Kozhushan');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '+380987688777');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '2');

-- Apartment3
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_3', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES (1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'user@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd'); -- Password1
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Igor');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Yabluchkin');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '+380967655444');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '3');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '120');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '4');

-- Apartment2
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_2', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'nonarush1@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '1c268eb823897fe3d7b75e9c1614afb1dadea2d65e4606db1fd68546e86a284b');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Dasha');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Markovkina');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '+380985433678');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '4');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '44');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '2');
-------------------------------------------------------END--------------------------------------------------------------

----------------------------------------------------Test data for Manager-----------------------------------------------
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 8, 'Manager_Account', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES (1, obj_id_seq.currval, 6);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'maksimenkogleb@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '19513fdc9da4fb72a4a05eb66917548d3c90ff94d5419e1f2363eea89dfee1dd'); -- Password1
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Manager');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'SuperManager');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '+380634783299');

--ManagerBill
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 9, 'ManagerBill', NULL);

INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (19, obj_id_seq.currval, '5678 9876 5678 7890');

--Reference
INSERT INTO OBJREFERENCE (ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (31, obj_id_seq.currval, obj_id_seq.currval - 1);
-------------------------------------------------------END--------------------------------------------------------------

---------------------------------------Test data for CalculationMethod  and CommunalUtilities---------------------------
-- CommunalUtility_1


INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 11, 'CommunalUtility_1', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (20, obj_id_seq.currval, null, 9);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (21, obj_id_seq.currval, 'garbage removal');
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (22, obj_id_seq.currval, null, 1);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (23, obj_id_seq.currval, null, 3);
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES (24, obj_id_seq.currval, DATE '2021-01-12');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (40, obj_id_seq.currval, '0.765');
--  CommunalUtility_2

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, null, 11, 'CommunalUtility_2', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (20, obj_id_seq.currval, null, 8);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (21, obj_id_seq.currval, 'renovation services');
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (22, obj_id_seq.currval, null, 2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (23, obj_id_seq.currval, null, 3);
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES (24, obj_id_seq.currval, DATE '2021-01-3');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (40, obj_id_seq.currval, '0.65');
-- CommunalUtility_3

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, null, 11, 'CommunalUtility_3', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (20, obj_id_seq.currval, null, 7);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (21, obj_id_seq.currval, 'heating');
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (22, obj_id_seq.currval, null, 2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES (23, obj_id_seq.currval, null, 4);
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES (24, obj_id_seq.currval, DATE '2021-01-6');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (40, obj_id_seq.currval, '0.365');
-------------------------------------------------------END--------------------------------------------------------------

----------------------------------------------Test data for ManagerSubBill----------------------------------------------
--ManagerSubBill for CommunalUtility_1
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'CommunalUtility_1'), 14, 'MANAGERSUBBIL_1',
        NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (25, obj_id_seq.currval, '9999991' || obj_id_seq.currval, null, null)
select *
from dual;
--Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (32,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Manager_Account'));

--ManagerSubBill for CommunalUtility_2
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_2'),14,'MANAGERSUBBIL_2',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES(25,obj_id_seq.currval,'9999998'||obj_id_seq.currval,null,null)
select * from dual;
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (32,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Manager_Account'));

--ManagerSubBill for CommunalUtility_3
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_3'),14,'MANAGERSUBBIL_3',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES(25,obj_id_seq.currval,'9999900'||obj_id_seq.currval,null,null)
select * from dual;
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (32,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Manager_Account'));

-------------------------------------------------------END--------------------------------------------------------------

----------------------------------------------Test data for ManagerOperationSpending------------------------------------
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval,null,18,'ManagerOperationSpending_1',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval,NULL,18,'ManagerOperationSpending_2',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval,NULL,18,'ManagerOperationSpending_3',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (28,OBJ_ID_SEQ.currval,'NEW DESC'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (26,OBJ_ID_SEQ.currval,'909090'||OBJ_ID_SEQ.currval,NULL,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES (27,OBJ_ID_SEQ.currval,null,systimestamp,NULL)
INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-2)
SELECT * FROM DUAL;
commit ;
---------------------------------------------------------END------------------------------------------------------------

-------------------------------------------Test data for ApartmentSubBills----------------------------------------------
-- SubBills for Apartment_1
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_1'), 13, 'SubBill_11', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '340.50');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '300');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_1'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_2'), 13, 'SubBill_12', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '127.80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_1'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_3'), 13, 'SubBill_13', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '569');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '500');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_1'));

-- SubBills for Apartment2
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility1_'), 13, 'SubBill_21', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '112');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '1678');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_2'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_2'), 13, 'SubBill_22', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '67');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '120');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_2'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_3'), 13, 'SubBill_23', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '890');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '80');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_2'));

-- SubBills for Apartment3
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_1'), 13, 'SubBill_31', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '321');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '800');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_3'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_2'), 13, 'SubBill_32', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '0');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '250');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_3'));

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_3'), 13, 'SubBill_33', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (25, obj_id_seq.currval, '2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');
-- Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (33,obj_id_seq.currval,
                                                               (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_3'));
-------------------------------------------------------END--------------------------------------------------------------

--------------------------------------Test data for table ApartmentOperation--------------------------------------------
-- ApartmentOperations for Apartment_Sub_Bill_1 for Apartment_1
-- ApartmentOperation1
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,17,'Apartment_Operation1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, '1000');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '01.01.2001');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (34, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='SubBill_11'));

-- ApartmentOperation2
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,17,'Apartment_Operation2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, '2000');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '02.02.2002');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (34, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='SubBill_11'));
-------------------------------------------------------END-------------------------------------------------------------

--------------------------------------Test data for table DebtPaymentOperation------------------------------------------
-- DebtPaymentOperation for for Apartment_Sub_Bill_1 for Apartment_1
-- DebtPaymentOperation1
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,16,'DebtPaymentOperation1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, '44');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '01.12.2020');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (35, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='SubBill_11'));
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (37, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='MANAGERSUBBIL_1'));

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,16,'DebtPaymentOperation2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, 3000);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '01.01.2001');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (35, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='SubBill_13'));
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (37, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='MANAGERSUBBIL_3'));

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,16,'DebtPaymentOperation3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(26, obj_id_seq.currval, 5000);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(27, obj_id_seq.currval, '02.02.2002');
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (35, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='SubBill_21'));
INSERT INTO OBJREFERENCE(attr_id, object_id, reference) VALUES (37, obj_id_seq.currval,
                                                                (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='MANAGERSUBBIL_1'));
-------------------------------------------------------END-------------------------------------------------------------

-----------------------------------Announcement, HouseVoting, VotingOption------------------------------------------
INSERT ALL
    INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 3, 'Announcement_1Test', NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (7, obj_id_seq.currval, 'Service improvement')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (8, obj_id_seq.currval, 'Dear residents,
We always try to listen to your opinion and improve our service depending on your wishes. Now we are interested to know if the existing functionality of the service suits you?
Yours faithfully,
Administration')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (9, obj_id_seq.currval, 'true')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES (10, obj_id_seq.currval, null, systimestamp)
SELECT *
FROM DUAL;


INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_1',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval, 'This is a good service, I like it' ,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_4', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'vika@gmail.com'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '66df28329fe8c63299454fde6c5364126ad1e91c3bfdc54446d10e07892acb09');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Viktoria');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Lady');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '11');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '3');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '5');


INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);


INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Announcement_1Test'), 5, 'House_Voting_1Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (13, OBJ_ID_SEQ.currval, 'Are you satisfied with our service?')
SELECT *
FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'House_Voting_1Test'), 6, 'Voting_Option_1Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14, OBJ_ID_SEQ.currval, 'Yes')
SELECT *
FROM DUAL;




INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'House_Voting_1Test'), 6, 'Voting_Option_2Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14, OBJ_ID_SEQ.currval, 'No')
SELECT *
FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'House_Voting_1Test'), 6, 'Voting_Option_3Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14, OBJ_ID_SEQ.currval, 'I haven`t decided yet')
SELECT *
FROM DUAL;



INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_3Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_2'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_1Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_3'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_1Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_1'));



INSERT ALL
    INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 3, 'Announcement_2Test', NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (7, obj_id_seq.currval, 'Level of heating evaluation in the house')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (8, obj_id_seq.currval, 'Dear residents,
We have received complaints regarding the heating level in the apartments. For a more detailed analysis of this problem, we would like to know the average estimate of the current heating level in the house. After receiving the results, we can make decisions on how to deal with this problem.
Yours faithfully,
Administration')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES (9, obj_id_seq.currval, 'false')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES (10, obj_id_seq.currval, NULL, systimestamp)
SELECT *
FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Announcement_2Test'), 5, 'House_Voting_2Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (13, OBJ_ID_SEQ.currval, 'Is there warm in your apartment?')
SELECT *
FROM DUAL;


INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'House_Voting_2Test'), 6, 'Voting_Option_4Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14, OBJ_ID_SEQ.currval, 'Yes')
SELECT *
FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'House_Voting_2Test'), 6, 'Voting_Option_5Test',
        NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14, OBJ_ID_SEQ.currval, 'No')
SELECT *
FROM DUAL;

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_1'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_2'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME = 'Apartment_3'));
-------------------------------------------------------END-------------------------------------------------------------

------------------------------------CREATE COMMENTS, ANNOUNCEMENTS AND APARTMENTS---------------------------------------
INSERT ALL
    INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 3, 'Announcement_1', NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Requests section')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval,  'Dear residents,
Send your requests, complaints and suggestions to our Requests section. Your messages will definitely be read and will be kept in mind when making further decisions and any actions. The administration is doing everything to make your living in our house comfortable.
Yours faithfully,
Administration')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES(10,obj_id_seq.currval, null, systimestamp )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_1',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval, 'This is first comment',null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_4', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'may@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '9b367940ef727050936b6d9821dbf203f3c2e32b89a94663c23d9e7eafd2a84d');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Kostya');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Murchik');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '10');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_3' ,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Saturday cleanup')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'Dear residents,
We invite everyone to attend a Saturday cleanup this week. We plan to clean up the area around the house, paint the benches, plant new trees and flowers, and just relax and chat with each other.
You just need to come and be in a good mood.
Yours faithfully,
Administration' )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES(10,obj_id_seq.currval, null, systimestamp )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_2',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval, 'Okay, our apartment join to this event' ,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_4', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'misha@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, 'b6185f5fd9890739863418e45c8f43008bb92e487926dbdea5f905d0c3d035c9');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Michail');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Patochkin');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '20');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '5');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '5');


INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);


INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_4',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Noisy neighbors')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'Dear residents,
From time to time we receive complaints about the noise coming from different apartments at night.
William Castle wrote:
"It''s hard to be a good neighbor in a bad neighborhood".
Remember this and respect each other and only there we will be able to live in peace.
Yours faithfully,
Administration')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES(10,obj_id_seq.currval, null, systimestamp )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_4',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'Yes, the neighbors above don''t respect anyone at all',null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_6', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'nice@gmail.com'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, '59d5eb7b3bbf93c0a9957faa93340cb4f4e98fc9ec69ab356a370ca50688c6fb');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Ramses');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Kiwi');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '21');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '5');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_5',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Happy New Year!')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'Dear residents,
Wishing you 12 months of success, 52 weeks of laughter, 365 days of fun, 8,760 hours of joy, 525,600 minutes of good luck, and 31,536,000 seconds of happiness.
Yours faithfully,
Administration' )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE)
VALUES(10,obj_id_seq.currval, null, systimestamp )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_5',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'thank you, wish you same',null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_7' , NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, LIST_VALUE_ID)
VALUES(1, obj_id_seq.currval, 5);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'kira@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, 'd10e3dbd70c9f6e0a082d7bdfaca882a1ef55edaeb30122f1818bc484e81a067');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Kira');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'Klubnichkina');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '345675677887');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '25');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '80'||obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '8');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '5');

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (29,OBJ_ID_SEQ.currval-1,OBJ_ID_SEQ.currval);

-----------------------------------------------END----------------------------------------------------------------------

-----------------------------------------------MANAGER_BILL-------------------------------------------------------------
INSERT ALL
            INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
            VALUES (obj_id_seq.nextval,NULL,9,'ManagerBill',null)
            INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
            VALUES (19,obj_id_seq.currval,'1234599999',SYSDATE,null)
            INTO OBJREFERENCE(attr_id, object_id, reference)
            VALUES (31,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Manager_Account'),obj_id_seq.currval)
SELECT * FROM dual;

------------------------------------------------END---------------------------------------------------------------------

commit ;
