-------------------------------------------------Test data for Apartments ---------------------------------------------
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
-------------------------------------------------------END--------------------------------------------------------------

----------------------------------------------------Test data for Manager-----------------------------------------------
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 8, 'Manager_Account', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Manager');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (2, obj_id_seq.currval, 'manager@gmail.com');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (3, obj_id_seq.currval, 'manager_password');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (4, obj_id_seq.currval, 'Manager');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (5, obj_id_seq.currval, 'SuperManager');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (6, obj_id_seq.currval, '3456456887');

--ManagerBill
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 9, 'ManagerBill', NULL);

INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (19, obj_id_seq.currval, '5678 9876 5678 7890');

--Reference
INSERT INTO OBJREFERENCE (ATTR_ID,OBJECT_ID,REFERENCE) VALUES (31,obj_id_seq.currval,obj_id_seq.currval-1);
-------------------------------------------------------END--------------------------------------------------------------

---------------------------------------Test data for CalculationMethod  and CommunalUtilities---------------------------
--CalculationMethod_1 and CommunalUtility_1
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod_1',null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility_1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,1);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null,3 );
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-12');

--CalculationMethod_2 and CommunalUtility_2
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod_2',null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility_2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null, 3 );
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-3');

--CalculationMethod_3 and CommunalUtility_3
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL,10,'CalculationMethod_3',null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES(20,obj_id_seq.currval,'newMethod'||obj_id_seq.currval,null,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION)
VALUES (obj_id_seq.nextval,obj_id_seq.currval-1,11,'CommunalUtility_3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES(21, obj_id_seq.currval, 'com_util_name' || obj_id_seq.currval);
INSERT INTO ATTRIBUTES(attr_id, object_id, value,LIST_VALUE_ID)
VALUES(22, obj_id_seq.currval, null,2);
INSERT INTO ATTRIBUTES(attr_id, object_id, value, LIST_VALUE_ID)
VALUES(23, obj_id_seq.currval, null,4);
INSERT INTO ATTRIBUTES(attr_id, object_id, DATE_VALUE)
VALUES(24, obj_id_seq.currval, DATE '2021-01-6');
-------------------------------------------------------END--------------------------------------------------------------

----------------------------------------------Test data for ManagerSubBill----------------------------------------------
--ManagerSubBill for CommunalUtility_1
INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='CommunalUtility_1'),14,'MANAGERSUBBIL_1',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE, DATE_VALUE, LIST_VALUE_ID)
VALUES(25,obj_id_seq.currval,'9999991'||obj_id_seq.currval,null,null)
select * from dual;
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
VALUES (36,OBJ_ID_SEQ.currval,OBJ_ID_SEQ.currval-1)
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
-------------------------------------------------------END-------------------------------------------------------------

-----------------------------------Announcement, HouseVoting, VotingOption------------------------------------------
INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_1Test',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Оцените нашу систему.')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'Нам важно Ваше мнение.' )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'false')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Announcement_1Test'),5,'House_Voting_1Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (13,OBJ_ID_SEQ.currval,'Вы довольны сервисом?')
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='House_Voting_1Test'),6,'Voting_Option_1Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14,OBJ_ID_SEQ.currval,'Да')
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='House_Voting_1Test'),6,'Voting_Option_2Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14,OBJ_ID_SEQ.currval,'Нет')
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='House_Voting_1Test'),6,'Voting_Option_3Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14,OBJ_ID_SEQ.currval,'Не определился')
SELECT * FROM DUAL;

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_3Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_2'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_1Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_3'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_1Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_1'));

INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_2Test',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'Проверка отопления')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'Нам важно Ваше мнение.' )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'false')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Announcement_2Test'),5,'House_Voting_2Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (13,OBJ_ID_SEQ.currval,'Достаточно ли тепло в квартире?')
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='House_Voting_2Test'),6,'Voting_Option_4Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14,OBJ_ID_SEQ.currval,'Да')
SELECT * FROM DUAL;

INSERT ALL
    INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='House_Voting_2Test'),6,'Voting_Option_5Test',NULL)
INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (14,OBJ_ID_SEQ.currval,'Нет')
SELECT * FROM DUAL;

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_1'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_2'));

INSERT INTO OBJREFERENCE(ATTR_ID, OBJECT_ID, REFERENCE)
VALUES (30,(SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Voting_Option_4Test'),
        (SELECT OBJECT_ID FROM OBJECTS WHERE NAME='Apartment_3'));
-------------------------------------------------------END-------------------------------------------------------------

------------------------------------CREATE COMMENTS, ANNOUNCEMENTS AND APARTMENTS---------------------------------------
INSERT ALL
    INTO OBJECTS (OBJECT_ID,PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_1' ,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_1',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_4', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
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
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_3' ,NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_2',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_5', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
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
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_4',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_4',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_6', NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
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
VALUES (obj_id_seq.nextval,NULL,3,'Announcement_5',NULL)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(7,obj_id_seq.currval, 'HELLO'||obj_id_seq.currval)
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(8,obj_id_seq.currval, 'BODY OF ANNC'||obj_id_seq.currval )
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(9,obj_id_seq.currval, 'TRUE')
INTO ATTRIBUTES(ATTR_ID, OBJECT_ID, VALUE)
VALUES(10,obj_id_seq.currval, SYSDATE )
SELECT * FROM DUAL;

INSERT
    ALL INTO OBJECTS(OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (OBJ_ID_SEQ.nextval, OBJ_ID_SEQ.currval-1,4,'Comment_5',NULL)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (11,OBJ_ID_SEQ.currval,'NEWCOMMENT'||OBJ_ID_SEQ.currval,null,null)
INTO ATTRIBUTES(attr_id, object_id, value, date_value, list_value_id)
VALUES (12,OBJ_ID_SEQ.currval,null,systimestamp,null)
SELECT * FROM DUAL;

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_7' , NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (1, obj_id_seq.currval, 'Owner');
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