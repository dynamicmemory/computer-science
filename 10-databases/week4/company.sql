CREATE TABLE department (
    dname        varchar(25) not null,
    dnumber      integer primary key,
    mgrssn       char(9) not null,
    mgrstartdate date
);

CREATE TABLE project (
    pname      varchar(25) unique not null,
    pnumber    integer primary key,
    plocation  varchar(15),
    dnum       integer not null,
    foreign key (dnum) references department(dnumber)
      ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE employee (
    fname    varchar(15) NOT NULL,
    minit    varchar(1),
    lname    varchar(15) NOT NULL,
    ssn      char(9) PRIMARY KEY,
    bdate    date,
    address  varchar(50),
    sex      char,
    salary   decimal(10,2),
    superssn char(9),
    dno      integer,
    foreign key (superssn) references employee(ssn)
      ON DELETE SET NULL ON UPDATE CASCADE,
    foreign key (dno) references department(dnumber)
      ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE dependent (
    essn            char(9),
    dependent_name  varchar(15),
    sex             char,
    bdate           date,
    relationship    varchar(8),
    primary key (essn,dependent_name),
    foreign key (essn) references employee(ssn)
      ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dept_locations (
    dnumber    integer,
    dlocation  varchar(15),
    primary key (dnumber,dlocation),
    foreign key (dnumber) references department(dnumber)
      ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE works_on (
    essn   char(9),
    pno    integer,
    hours  decimal(4,1),
    primary key (essn,pno),
    foreign key (essn) references employee(ssn)
      ON DELETE CASCADE ON UPDATE CASCADE,
    foreign key (pno) references project(pnumber)
      ON DELETE CASCADE ON UPDATE CASCADE
);


-- Prac 1 queries

-- Return names of all employees from dept 5 who worked 10 or more hours on projectX
-- SELECT e.fname, e.lname
--   FROM employee as e
--    JOIN works_on as w ON e.ssn = w.essn
--    JOIN project as p ON w.pno = p.pnumber 
--    JOIN department as d ON p.dnum = d.dnumber
--   WHERE p.pname = 'ProductX' AND w.hours >= 10; 

-- List all employees who have a dependant with the same name as themselves
-- SELECT e.fname, e.lname 
--   FROM employee e 
--     JOIN dependent d ON e.ssn = d.essn 
--   WHERE e.fname = d.dependent_name;

-- Find all employees who are directly supervised by franklin wong
-- SELECT * 
--   FROM employee e1
--     WHERE e1.superssn IN 
--       (SELECT ssn
--         FROM employee e2 
--         WHERE e2.fname = 'Franklin' AND e2.lname = 'Wong');

-- Prac 2 queries 

-- Questions 1

-- For each department whose average employee salary is more than 30000, retrieve
-- the department name and the numer of employees working for that department 
SELECT dname, COUNT(*)
  FROM department AS d 
    JOIN employee AS e ON d.dnumber = e.dno 
  GROUP BY dname
  HAVING AVG(e.salary) > 30000;

-- Retrueve all employees with a last name that starts with the character 'S'
SELECT * 
  FROM employee
    WHERE lname LIKE 'S%';

-- Find all emplouiyees that work on a project with the word 'Product' in its name 
SELECT DISTINCT e.fname, e.lname, p.pname 
  FROM project AS p
    JOIN works_on AS w ON p.pnumber = w.pno 
    JOIN employee AS e ON e.ssn = w.essn 
  WHERE p.pname LIKE '%Product%';

-- QUESTIONS 2

-- Create a view that has the department name, manager name and manafer salary
-- for every department 
CREATE VIEW department_info
AS SELECT d.dname "Department Name", e.fname ||' '|| e.lname "Manager", e.salary "Salary"
    FROM department AS d 
      JOIN employee AS e ON d.mgrssn = e.ssn; 

-- Create a view that has the employee name , supervisor name and employee salary 
-- for each employee who works in the 'Research' department.
CREATE VIEW research_info
  AS SELECT e.fname||' '||e.lname "Name", s.fname||' '||s.lname "Supervisor" , e.salary "Salary"
    FROM employee AS e 
      JOIN employee AS s ON e.superssn = s.ssn;

-- Create a view that has the project name, controlling department name, and 
-- number of employees and total hours worjed per week on the poject for each project
CREATE VIEW project_into 
  AS SELECT p.pname "Project Name", d.dname "Department", COUNT(DISTINCT w.essn) "Number of Employee", 
    SUM(w.hours) "Hours Worked"
    FROM employee AS e 
      JOIN works_on AS w ON e.ssn = w.essn 
      JOIN project AS p ON w.pno = p.pnumber 
      JOIN department AS d ON p.dnum = d.dnumber
  GROUP BY p.pname, d.dname;


-- Prac 3 

SELECT e.fname||' '||e.lname "Peasants", s.fname||' '||s.lname "Overlords"
  FROM employee AS e 
    LEFT OUTER JOIN employee AS s ON e.superssn = s.ssn;

-- Questions 1
-- Retriene the first and last name of all employees who do not work on the 
-- project with name 'ProductZ'
SELECT e.fname||' '||e.lname "Non Cucked employees"
  FROM employee AS e 
  WHERE e.ssn NOT IN 
    (SELECT ssn 
      FROM employee 
        JOIN works_on AS w ON ssn = w.essn
        JOIN project AS p ON w.pno = p.pnumber 
      WHERE p.pname Like 'ProductZ');

-- Retrieve the first and last name of all emplouees that work on a project 
-- that John Smith works on 
SELECT DISTINCT fname||' '||lname "Johns Bitches"
  FROM employee 
    JOIN works_on ON ssn = essn 
    JOIN project ON pno = pnumber 
  WHERE pname IN 
    (SELECT p.pname 
      FROM project AS p
        JOIN works_on AS w ON p.pnumber = w.pno 
        JOIN employee AS e ON w.essn = e.ssn 
          WHERE e.fname Like 'John' AND e.lname Like 'Smith');

-- List the projhect names of all projects that have at least one employee working 
-- on them who are supervised by John James 

SELECT p.pname 
  FROM project AS p 
    JOIN works_on AS w ON p.pnumber = w.pno 
    JOIN employee AS e2 ON w.essn = e2.ssn 
  WHERE e2.ssn = 
    (SELECT e1.ssn 
      FROM employee AS e1
        WHERE e1.superssn = 
          (SELECT ssn 
            FROM employee 
              WHERE fname Like 'John' AND lname Like 'James'));

-- List names of employees that only work on the 'ProductX' project 

SELECT e.fname||' '||e.lname "Elons Bitches"
  FROM employee AS e 
    JOIN works_on AS w ON e.ssn = w.essn 
    JOIN project AS p ON w.pno = p.pnumber 
  WHERE p.pname = 'ProductX' AND e.ssn NOT IN 
    (SELECT e1.ssn 
      FROM employee AS e1
        JOIN works_on AS w1 ON e1.ssn = w1.essn
        JOIN project AS p1 ON w1.pno = p1.pnumber
      WHERE p1.pname != 'ProductX');

-- QUESTION 3 

-- A view that has theproject name, controlling department name , number of employees,
-- and total hours worked per week on the project for each project with more than one
-- employee working on it.

CREATE VIEW project_info AS 
  SELECT p.pname "Project Name", d.dname "Department", COUNT(e.ssn) "Number of Employees", 
    SUM(w.hours) "Total hours"
    FROM employee AS e 
      JOIN works_on AS w ON e.ssn = w.essn 
      JOIN project AS p ON w.pno = p.pnumber 
      JOIN department AS d ON p.dnum = d.dnumber
   GROUP BY p.pname, d.dname 
     HAVING COUNT(e.ssn) > 1;



