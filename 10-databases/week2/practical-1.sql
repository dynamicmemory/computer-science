CREATE TABLE department (
  dname          VARCHAR(25) NOT NULL,
  dnumber        INTEGER PRIMARY KEY,
  mgr_ssn        CHAR(9) NOT NULL,
  mgr_start_date DATE
);


CREATE TABLE project (
  pname     VARCHAR(25) UNIQUE NOT NULL,
  pnumber   INTEGER PRIMARY KEY,
  plocation VARCHAR(15),
  dnum      INTEGER NOT NULL,
  FOREIGN KEY (dnum) REFERENCES department(dnumber)
);


CREATE TABLE employee (
  fname      VARCHAR(15) NOT NULL, 
  minit      VARCHAR(1),
  lname      VARCHAR(15) NOT NULL,
  ssn        CHAR(9) PRIMARY KEY,
  birth_date DATE, 
  address    VARCHAR(50),
  sex        CHAR,
  salary     DECIMAL(10,2),
  super_ssn  CHAR(9),
  dno        INTEGER,
  FOREIGN KEY (super_ssn) REFERENCES employee(ssn)
      ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (dno) REFERENCES department(dnumber)
      ON DELETE SET NULL ON UPDATE CASCADE
);


CREATE TABLE dependent (
  employee_ssn     CHAR(9),
  dependent_name   VARCHAR(15),
  sex              CHAR(1) CHECK (sex IN ('M', 'F')),
  birth_date       DATE,
  relationship     VARCHAR(8),
  PRIMARY KEY (employee_ssn, dependent_name),
  FOREIGN KEY (employee_ssn) REFERENCES employee(ssn)
);


CREATE TABLE dept_locations (
  dnumber      INTEGER,
  dlocation    VARCHAR(15),
  PRIMARY KEY (dnumber, dlocation),
  FOREIGN KEY (dnumber) REFERENCES department(dnumber)
);


CREATE TABLE works_on (
  employee_ssn    CHAR(9),
  project_number  INTEGER,
  hours           DECIMAL(4,1),
  PRIMARY KEY (employee_ssn, project_number),
  FOREIGN KEY (employee_ssn) REFERENCES employee(ssn),
  FOREIGN KEY (project_number) REFERENCES project(pnumber)
);


SELECT fname, minit, lname FROM employee  
