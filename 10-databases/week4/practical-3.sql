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


SELECT fname, minit, lname FROM employee; 


-- Using NULL 
SELECT fname, lname FROM employee WHERE super_ssn IS NOT NULL;

SELECT fname, lname FROM employee WHERE super_ssn IS NULL;


-- Return a list of project numbers that involve an employ weith last name smith as manager or worker


SELECT DISTINCT pnumber FROM project WHERE pnumber IN 
    (SELECT pnumber FROM project, department, employee WHERE dnum = dnumber
    AND mgr_ssn=ssn and lname = 'smith')
    OR
    pnumber IN 
    (SELECT pnumber FROM works_on, employee WHERE employee_ssn=ssn AND lname='Smith');



SELECT DISTINCT employee_ssn FROM works_on WHERE (project_number, hours) IN
    (SELECT project_number, hours FROM works_on WHERE employee_ssn = '123456789');


-- Return all employees with a salary greater than a;; employees in department 5

SELECT fname, lname FROM employee WHERE salary > ALL 
    (SELECT salary FROM employee WHERE dno = 5);


SELECT e.fname, e.lname FROM employee As e WHERE e.ssn IN 
    (SELECT employee_ssn FROM dependent AS d WHERE e.fname = d.dependent_name
    AND e.sex = d.sex);


-- Returning true or false 

SELECT e.fname, e.lname FROM employee AS e WHERE EXISTS
    (SELECT * FROM dependent AS d WHERE e.ssn = d.employee_ssn AND 
        e.sex = d.sex AND e.fname = d.dependent_name);

SELECT fname, lname FROM employee WHERE NOT EXISTS
    (SELECT * FROM dependent WHERE ssn = employee_ssn);


SELECT fname, lname FROM employee WHERE EXISTS 
    (SELECT * FROM works_on AS b WHERE 
        (b.project_number IN 
            (SELECT pnumber FROM project WHERE dnum = 4)
            AND NOT EXISTS
            (SELECT * FROM works_on AS c WHERE c.employee_ssn = ssn 
                AND c.project_number = b.project_number)));


-- different joins 

SELECT e.lname AS Employee_Name, s.lname AS Supervisor_Name FROM 
    (employee AS e LEFT OUTER JOIN employee as s ON e.super_ssn = s.ssn);

-- QUESTION 1

-- a) Retrieve the first name and last name of all emp[louees who do not work 
--    on the project with the name 'ProductZ'

SELECT fname, lname FROM employee WHERE NOT EXISTS 
    (SELECT * FROM works_on, project WHERE 
        ssn = employee_ssn AND project_number = pnumber AND pname = 'ProductZ');

-- b) Retrieve the first name and last name of all employees that work on a 
--    project that 'John Smith' works on. 

SELECT DISTINCT fname, lname FROM employee, works_on, project 
    WHERE ssn = employee_ssn AND project_number IN 
    (SELECT pnumber FROM project AS p, employee AS e, works_on AS w 
        WHERE p.pnumber = w.project_number AND 
          w.employee_ssn = e.ssn AND 
          e.fname = 'John' AND 
          e.lname = 'Smith');

