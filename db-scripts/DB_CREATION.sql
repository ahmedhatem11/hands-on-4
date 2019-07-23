CREATE SCHEMA STAFF;

CREATE TABLE STAFF.DEPARTMENT (
	DEPTCODE char(4) not null,
	DEPTNAME varchar(60) not null,
	MANAGER char(6)
);

INSERT INTO STAFF.DEPARTMENT ( DEPTCODE, DEPTNAME)
	VALUES ('D000','Unassigned');
INSERT INTO STAFF.DEPARTMENT ( DEPTCODE, DEPTNAME, MANAGER)
	VALUES ('D501','Marketing', 'E10002');
INSERT INTO STAFF.DEPARTMENT ( DEPTCODE, DEPTNAME)
	VALUES ('D601','Operations');
INSERT INTO STAFF.DEPARTMENT ( DEPTCODE, DEPTNAME)
	VALUES ('D701','Finance');
INSERT INTO STAFF.DEPARTMENT ( DEPTCODE, DEPTNAME)
	VALUES ('D801','Research and Development');

CREATE TABLE STAFF.EMPLOYEE (
	EMPID char(6) not null,
	DEPTCODE char(4) not null,
	JOBTITLE varchar (30),
	GIVENNAME varchar(30) not null,
	FAMILYNAME varchar(30) not null,
	COMMONNAME varchar (60),
	NAMETITLE varchar(6)
);

INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10001', 'D501','Salesperson', 'Edward James', 'Thompson', 'Ed Thompson', 'Mr');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10002', 'D501','Marketing Manager', 'Grace', 'Serraro', 'Grace Serrero', 'Miss');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10003', 'D601','Procurements', 'Marie Therese', 'Sauve', 'Marie Sauve', 'Ms');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10004', 'D701','Receivables clerk', 'Alison H. J.', 'Hiivala', 'Ali Hiivala', 'Miss');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10005', 'D701','Accountant', 'Lars K.', 'Instrom', 'Lars Instrom', 'Mr');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10006', 'D701','Cashier', 'Sally Louisa', 'Bavarra', 'Louise Bavarra ', 'Ms');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10007', 'D501','Web Master', 'Tung Dae', 'Twan', 'T.D. Twan', 'Mr');
	INSERT INTO STAFF.EMPLOYEE ( EMPID, DEPTCODE, JOBTITLE, GIVENNAME, FAMILYNAME, COMMONNAME, NAMETITLE)
	VALUES ('E10008', 'D501','Salesperson', 'Katherine', 'Chomsky', 'Kathy Chomsky', 'Mrs');



CREATE TABLE STAFF.ADDRESS (
	ADDRESSID integer not null,
	EMPID char(6) not null,
	ADDLINE1 varchar (50),
	ADDLINE2 varchar (50),
	CITY varchar (20),
	REGION varchar (20),
	COUNTRY varchar (20),
	POSTCODE varchar (10)
);

INSERT INTO STAFF.ADDRESS ( ADDRESSID, EMPID, ADDLINE1, CITY, REGION, COUNTRY, POSTCODE)
	VALUES (1, 'E10001','107 Main Street, Apt 912', 'Toronto', 'ON', 'Canada', 'M4R2G5');

CREATE TABLE STAFF.PHONENUMBER (
	PHONEID integer not null,
	EMPID char(6) not null,
	LOCALNUM varchar (15) not null,
	INTLPREFIX varchar (5),
	PHONETYPE varchar (11) not null
);

INSERT INTO STAFF.PHONENUMBER ( PHONEID, EMPID, LOCALNUM, PHONETYPE)
	VALUES (1, 'E10001', '4165429764','HOME');

CREATE TABLE STAFF.EMAIL (
	EMAILID integer not null,
	EMPID char(6) not null,
	EMAILADDRESS char(40) not null,
	EMAILTYPE varchar (5) not null
);

INSERT INTO STAFF.EMAIL ( EMAILID, EMPID, EMAILADDRESS, EMAILTYPE)
	VALUES (1, 'E10001','ejthompson@apex.com', 'WORK');
INSERT INTO STAFF.EMAIL ( EMAILID, EMPID, EMAILADDRESS, EMAILTYPE)
	VALUES (2, 'E10001','edjt101@mymail.com', 'HOME');
INSERT INTO STAFF.EMAIL ( EMAILID, EMPID, EMAILADDRESS, EMAILTYPE)
	VALUES (3, 'E10002','grace@algernon.com', 'WORK');
INSERT INTO STAFF.EMAIL ( EMAILID, EMPID, EMAILADDRESS, EMAILTYPE)
	VALUES (4, 'E10003','mtsauve@supermail.com', 'HOME');

CREATE TABLE STAFF.PHOTO (
	PHOTOID integer not null,
	EMPID char(6) not null,
	IMAGENAME varchar(40)
);

INSERT INTO STAFF.PHOTO ( PHOTOID, EMPID, IMAGENAME)
	VALUES (1, 'E10002','db200130.gif');
INSERT INTO STAFF.PHOTO ( PHOTOID, EMPID, IMAGENAME)
	VALUES (2, 'E10004','db200140.gif');
INSERT INTO STAFF.PHOTO ( PHOTOID, EMPID, IMAGENAME)
	VALUES (3, 'E10005','db200150.gif');
INSERT INTO STAFF.PHOTO ( PHOTOID, EMPID, IMAGENAME)
	VALUES (4, 'E10007','db200190.gif');

CREATE TABLE STAFF.PROJECT (
	PROJID char(6) not null,
	PROJNAME varchar(80) not null,
	STARTDATE date,
	TARGETDATE date,
	STATUS char (7),
	DESCRIPTION varchar(1000)
);

INSERT INTO STAFF.PROJECT ( PROJID, PROJNAME, STARTDATE, STATUS)
	VALUES ('PK5555','MPD Research', '2009-01-01','ACTIVE');
INSERT INTO STAFF.PROJECT ( PROJID, PROJNAME, STARTDATE, TARGETDATE, STATUS)
	VALUES ('PK5666','Web Site Update', '2009-06-01', '2009-12-31', 'PENDING');
INSERT INTO STAFF.PROJECT ( PROJID, PROJNAME, STARTDATE, TARGETDATE, STATUS)
	VALUES ('PK5444','LMX Reduction', '2008-09-01', '2009-12-31', 'CLOSED');


create table STAFF.PROJECTMEMBER (
	PROJID char(6) not null,
	EMPID char(6) not null
);

INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5555','E10001');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5555','E10002');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5555','E10008');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5666','E10007');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5444','E10002');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5444','E10005');
INSERT INTO STAFF.PROJECTMEMBER (PROJID, EMPID)
   VALUES ('PK5444','E10007');

ALTER TABLE STAFF.EMPLOYEE
	ADD CONSTRAINT EMP_PK PRIMARY KEY (EMPID);

ALTER TABLE STAFF.DEPARTMENT
	ADD CONSTRAINT DEPT_PK PRIMARY KEY (DEPTCODE);
ALTER TABLE STAFF.DEPARTMENT
	ADD CONSTRAINT DEPT_MGR_FK FOREIGN KEY (MANAGER) REFERENCES STAFF.EMPLOYEE (EMPID);

ALTER TABLE STAFF.EMPLOYEE
	ADD CONSTRAINT EMP_DEPT_FK FOREIGN KEY (DEPTCODE) REFERENCES STAFF.DEPARTMENT (DEPTCODE);

ALTER TABLE STAFF.ADDRESS
	ADD CONSTRAINT ADD_PK PRIMARY KEY (ADDRESSID);
ALTER TABLE STAFF.ADDRESS
	ADD CONSTRAINT ADD_EMP_FK FOREIGN KEY (EMPID) REFERENCES STAFF.EMPLOYEE (EMPID);


ALTER TABLE STAFF.PHONENUMBER
	ADD CONSTRAINT PHONE_PK PRIMARY KEY (PHONEID);
ALTER TABLE STAFF.PHONENUMBER
	ADD CONSTRAINT PHONE_EMP_FK FOREIGN KEY (EMPID) REFERENCES STAFF.EMPLOYEE (EMPID);

ALTER TABLE STAFF.EMAIL
	ADD CONSTRAINT EMAIL_PK PRIMARY KEY (EMAILID);
ALTER TABLE STAFF.EMAIL
	ADD CONSTRAINT EMAIL_EMP_FK FOREIGN KEY (EMPID) REFERENCES STAFF.EMPLOYEE (EMPID);

ALTER TABLE STAFF.PHOTO
	ADD CONSTRAINT PHOTO_PK PRIMARY KEY (PHOTOID);
ALTER TABLE STAFF.PHOTO
	ADD CONSTRAINT PHOTO_EMP_FK FOREIGN KEY (EMPID) REFERENCES STAFF.EMPLOYEE (EMPID);

ALTER TABLE STAFF.PROJECT
	ADD CONSTRAINT PROJ_PK PRIMARY KEY (PROJID);

ALTER TABLE STAFF.PROJECTMEMBER
	ADD CONSTRAINT PROJMEM_PK PRIMARY KEY (PROJID, EMPID);
ALTER TABLE STAFF.PROJECTMEMBER
	ADD CONSTRAINT MEM_PROJ_FK FOREIGN KEY (PROJID) REFERENCES STAFF.PROJECT (PROJID);
ALTER TABLE STAFF.PROJECTMEMBER
	ADD CONSTRAINT PROJ_MEM_FK FOREIGN KEY (EMPID) REFERENCES STAFF.EMPLOYEE (EMPID);

