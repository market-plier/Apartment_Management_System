
/*Test data for table ApartmentDao*/

-- Creating an Instances of the Apartments

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,7,'Apartment_1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(15,obj_id_seq.currval,'10');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(16,obj_id_seq.currval ,'80');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(17,obj_id_seq.currval ,'1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(18,obj_id_seq.currval ,'2');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,7,'Apartment_2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(15,obj_id_seq.currval,'11');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(16,obj_id_seq.currval ,'44');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(17,obj_id_seq.currval ,'1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(18,obj_id_seq.currval ,'2');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,7,'Apartment_3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(15,obj_id_seq.currval,'12');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(16,obj_id_seq.currval ,'120');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(17,obj_id_seq.currval ,'2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(18,obj_id_seq.currval ,'4');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,7,'Apartment_4',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(15,obj_id_seq.currval,'15');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(16,obj_id_seq.currval ,'64');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(17,obj_id_seq.currval ,'1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(18,obj_id_seq.currval ,'1');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,7,'Apartment_5',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(15,obj_id_seq.currval,'22');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(16,obj_id_seq.currval ,'72');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(17,obj_id_seq.currval ,'3');
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(18,obj_id_seq.currval ,'3');

/*Test data for table ApartmentSubBillDao*/

-- Creating an Instances of the ApartmentSubBills

-- SubBills for Apartment_1
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_1_Apartment_1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '300');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_2_Apartment_1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '0');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_3_Apartment_1',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '500');

-- SubBills for Apartment_2
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_1_Apartment_2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '1678');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_2_Apartment_2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '120');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_3_Apartment_2',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '80');

-- SubBills for Apartment_3
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_1_Apartment_3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '800');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_2_Apartment_3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '250');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_3_Apartment_3',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '0');

-- SubBills for Apartment_4
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_1_Apartment_4',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '50');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_2_Apartment_4',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '10');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_3_Apartment_4',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '1000');

-- SubBills for Apartment_5
INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_1_Apartment_5',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '129');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_2_Apartment_5',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '8060');

INSERT INTO OBJECTS (OBJECT_ID,PARENT_ID,OBJECT_TYPE_ID,NAME,DESCRIPTION) VALUES (obj_id_seq.nextval,NULL,13,'SubBill_3_Apartment_5',NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value) VALUES(38, obj_id_seq.currval, '15000');

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

