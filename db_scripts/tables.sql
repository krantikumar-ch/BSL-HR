CREATE TABLE "DEPARTMENTS" 
   (	"DEPARTMENT_ID" NUMBER(4,0), 
	"DEPARTMENT_NAME" VARCHAR2(30 BYTE), 
	"MANAGER_ID" NUMBER(6,0), 
	"LOCATION_ID" NUMBER(4,0)
   )

   
--------------------------------------------------------
--  DDL for Table EMPLOYEES
--------------------------------------------------------

  CREATE TABLE "EMPLOYEES" 
   (	"EMPLOYEE_ID" NUMBER(6,0), 
	"FIRST_NAME" VARCHAR2(20 BYTE), 
	"LAST_NAME" VARCHAR2(25 BYTE), 
	"EMAIL" VARCHAR2(25 BYTE), 
	"PHONE_NUMBER" VARCHAR2(20 BYTE), 
	"HIRE_DATE" DATE, 
	"JOB_ID" VARCHAR2(10 BYTE), 
	"SALARY" NUMBER(8,2), 
	"COMMISSION_PCT" NUMBER(2,2), 
	"MANAGER_ID" NUMBER(6,0), 
	"DEPARTMENT_ID" NUMBER(4,0), 
	"EMP_TYPE" VARCHAR2(1 BYTE)
   ) 
--------------------------------------------------------
--  DDL for Table USERS
--------------------------------------------------------

  CREATE TABLE "USERS" 
   (	"USER_ID" NUMBER(20,0), 
	"FIRST_NAME" VARCHAR2(255 BYTE), 
	"LAST_NAME" VARCHAR2(255 BYTE), 
	"USER_NAME" VARCHAR2(255 BYTE), 
	"PASSWORD" VARCHAR2(255 BYTE), 
	"EMAIL" VARCHAR2(255 BYTE), 
	"PHONE_NUMBER" VARCHAR2(255 BYTE), 
	"COUNTRY" VARCHAR2(255 BYTE), 
	"CREATED_BY" NUMBER(20,0), 
	"CREATED_DATE" DATE, 
	"LAST_UPDATED_BY" NUMBER, 
	"LAST_UPDATED_DATE" DATE, 
	"SUPER_USER" NUMBER(1,0) DEFAULT 0
   ) 
   
   --------------------------------------------------------
--  DDL for Table countries
--------------------------------------------------------

 CREATE TABLE countries (
  country_id number(20),
  iso varchar2(2) NOT NULL,
  name varchar2(80) NOT NULL,
  nice_name varchar2(80) NOT NULL,
  iso3 varchar2(3) DEFAULT NULL,
  num_code number(6) DEFAULT NULL,
  phone_code number(5) NOT NULL,
  PRIMARY KEY (country_id)
) 
