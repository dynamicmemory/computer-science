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

-- QUESTION 1

--a) For each department whose average employee salary is more than 30k, retrieve
-- the department name and the number of employees working for that department. 
SELECT dname AS "Department Name", COUNT(*) AS "Number of employees" FROM department, 
employee WHERE dnumber = dno GROUP BY dname HAVING AVG(salary) > 30000;

--b) Retireve all employees with a last name that start with the character 'S'
SELECT * FROM employee WHERE lname LIKE 'S%';

--c) Find all employees that work on a project with the word 'Product' in its name 
SELECT * FROM employee, project WHERE pname LIKE 'Product%';

-- QUESTION 2

--a) Create a view that has the department name, manager name and manager salary for 
-- every department 
CREATE VIEW department_info AS SELECT dname AS "Department name", fname||' '||lname 
AS "Manager", salary AS "Salary" FROM department, employee WHERE mgr_ssn = ssn;

--b) A view that has the employee name, supervisor name and employee salary for 
-- each employee who works in the 'Research' department 
CREATE VIEW research_department AS SELECT e.fname||' '||e.lname AS "Name", s.fname||' '||s.lname
AS "Supervisor", e.salary AS "Salary" FROM employee AS e, employee as s, department as d 
WHERE s.ssn = e.super_ssn AND e.dno = d.dnumber AND d.dname LIKE 'Research';

--c) A view that has the project name, controlling department name, number of 
-- employees, and total hours worked per week on the project for each project. 
CREATE VIEW project_info AS SELECT pname AS "Project name", dname AS "Department", 
    COUNT(DISTINCT ssn) AS "Number of Employees", SUM(hours) AS "Weekly Hours" FROM 
project, department, employee, works_on WHERE dnum = dnumber AND employee_ssn = ssn 
AND pnumber = project_number GROUP BY pname, dname;

