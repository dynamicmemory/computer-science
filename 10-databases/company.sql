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
