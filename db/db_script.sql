
begin
  execute immediate 'drop table OBJTYPE CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

begin
  execute immediate 'drop table ATTRTYPE CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

begin
  execute immediate 'drop table Lists CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

begin
  execute immediate 'drop table OBJECTS CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

begin
  execute immediate 'drop table ATTRIBUTES CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

begin
  execute immediate 'drop table OBJREFERENCE CASCADE CONSTRAINTS';
  exception
  when others then null;
end;
/

-- Таблица описаний объектных типов
CREATE TABLE OBJTYPE
  (
    OBJECT_TYPE_ID NUMBER(20) NOT NULL ENABLE,
    PARENT_ID      NUMBER(20),
    CODE           VARCHAR2(20) NOT NULL UNIQUE,
    NAME           VARCHAR2(200 BYTE),
    DESCRIPTION    VARCHAR2(1000 BYTE),
    CONSTRAINT CON_OBJECT_TYPE_ID PRIMARY KEY (OBJECT_TYPE_ID),
    CONSTRAINT CON_PARENT_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID) ON DELETE CASCADE ENABLE
  );


 -- Таблица описаний атрибутных типов
 CREATE TABLE ATTRTYPE (
    ATTR_ID      		NUMBER(20) NOT NULL,
    OBJECT_TYPE_ID 		NUMBER(20) NOT NULL,
	OBJECT_TYPE_ID_REF 	NUMBER(20),
    CODE         		VARCHAR2(20),
    NAME         		VARCHAR2(200 BYTE),
    CONSTRAINT CON_ATTR_ID PRIMARY KEY (ATTR_ID),
    CONSTRAINT CON_ATTR_OBJECT_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID),
	CONSTRAINT CON_ATTR_OBJECT_TYPE_ID_REF FOREIGN KEY (OBJECT_TYPE_ID_REF) REFERENCES OBJTYPE (OBJECT_TYPE_ID)
);

-- Таблица для хранения листовых значений
create table Lists
(
    attr_id number(10) not null,
    list_value_id number(10) not null,
    value varchar(4000),
    CONSTRAINT CON_list_value_id PRIMARY KEY (list_value_id),
    constraint con_L_attr_id foreign key (attr_id) references AttrType (attr_id) on delete cascade
);

-- Таблица описаний экземпляров объектов
 CREATE TABLE OBJECTS (
    OBJECT_ID      NUMBER(20) NOT NULL,
    PARENT_ID      NUMBER(20),
    OBJECT_TYPE_ID NUMBER(20) NOT NULL,
    NAME           VARCHAR2(2000 BYTE),
    DESCRIPTION    VARCHAR2(4000 BYTE),
    CONSTRAINT CON_OBJECTS_ID PRIMARY KEY (OBJECT_ID),
    CONSTRAINT CON_PARENTS_ID FOREIGN KEY (PARENT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE DEFERRABLE,
    CONSTRAINT CON_OBJ_TYPE_ID FOREIGN KEY (OBJECT_TYPE_ID) REFERENCES OBJTYPE (OBJECT_TYPE_ID)
);

-- Таблица описаний значений атрибутов экземпляров объектов
CREATE TABLE ATTRIBUTES
  (
    attr_id number(10) not null,
    object_id number(20) not null,
    value varchar2(4000 byte),
    date_value date,
    list_value_id number(10),
    constraint  con_ATR_attr_id foreign key (attr_id) references AttrType (attr_id) on delete cascade,
    constraint con_ATR_object_id foreign key (object_id) references Objects (object_id) on delete cascade
  );
  
-- Таблица описаний связей "простая ассоциация" между экземплярами объектов
CREATE TABLE OBJREFERENCE
  (
    ATTR_ID   NUMBER(20) NOT NULL,
    OBJECT_ID NUMBER(20) NOT NULL,
    REFERENCE NUMBER(20) NOT NULL,
	CONSTRAINT CON_OBJREFERENCE_PK PRIMARY KEY (ATTR_ID,REFERENCE,OBJECT_ID),
    CONSTRAINT CON_REFERENCE FOREIGN KEY (REFERENCE) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
    CONSTRAINT CON_ROBJECT_ID FOREIGN KEY (OBJECT_ID) REFERENCES OBJECTS (OBJECT_ID) ON DELETE CASCADE,
    CONSTRAINT CON_RATTR_ID FOREIGN KEY (ATTR_ID) REFERENCES ATTRTYPE (ATTR_ID) ON DELETE CASCADE
  ); 
 
