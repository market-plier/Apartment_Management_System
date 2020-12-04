/*Test data for table ApartmentDao*/

-- Creating an Instances of the Apartments

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL);
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

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL);
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

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL);
-- add account data
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '12');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '120');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '2');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '4');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL);
-- add account data
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '15');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '64');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '1');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '1');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 7, 'Apartment_' || obj_id_seq.currval, NULL);
-- add account data
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (15, obj_id_seq.currval, '22');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (16, obj_id_seq.currval, '72');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (17, obj_id_seq.currval, '3');
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (18, obj_id_seq.currval, '3');

/*Test data for table ApartmentSubBillDao*/

-- Creating an Instances of the ApartmentSubBills

-- SubBills for Apartment_1
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '300');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '500');

-- SubBills for Apartment_2
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '1678');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '120');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '80');

-- SubBills for Apartment_3
INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '800');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '250');

INSERT INTO OBJECTS (OBJECT_ID, PARENT_ID, OBJECT_TYPE_ID, NAME, DESCRIPTION)
VALUES (obj_id_seq.nextval, NULL, 13, 'SubBill_' || obj_id_seq.currval, NULL);
INSERT INTO ATTRIBUTES(attr_id, object_id, value)
VALUES (38, obj_id_seq.currval, '0');
