CREATE TABLE bank (
  bcode      INT PRIMARY KEY,
  bname      VARCHAR(20),
  h_address  VARCHAR(50)
);

CREATE TABLE customer (
  ssn        INT PRIMARY KEY,
  name       VARCHAR(20),
  phone      VARCHAR(15),
  c_address  VARCHAR(50)
);

CREATE TABLE bank_branch (
  bnum       INT,
  bco        INT,
  b_address  VARCHAR(50),
  PRIMARY KEY (bnum,bco),
  FOREIGN KEY (bco) REFERENCES bank (bcode)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE account (
  anum   INT PRIMARY KEY, 
  bno    INT,
  bco    INT,
  atype  VARCHAR(20) CHECK (atype IN ('Saver', 'Student', 'Investor', 'Spender')),
  FOREIGN KEY (bno, bco) REFERENCES bank_branch (bnum, bco)
    ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE loan (
  lnum   INT PRIMARY KEY,
  bno    INT,
  bco    INT,
  ltype  VARCHAR(20) CHECK (ltype IN ('House', 'Car', 'General')),
  FOREIGN KEY (bno, bco) REFERENCES bank_branch (bnum, bco)
    ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE customer_account( 
  cssn     INT,
  ano      INT,
  balance  INT CHECK (balance >= 0),
  PRIMARY KEY (cssn, ano),
  FOREIGN KEY (cssn) REFERENCES customer (ssn)
    ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (ano) REFERENCES account (anum)
    ON DELETE SET NULL ON UPDATE CASCADE
);
  
CREATE TABLE customer_loan( 
  cssn    INT,
  lno     INT,
  amount  INT CHECK (amount >= 0),
  PRIMARY KEY (cssn, lno),
  FOREIGN KEY (cssn) REFERENCES customer (ssn)
    ON DELETE SET NULL ON UPDATE CASCADE,
  FOREIGN KEY (lno) REFERENCES loan (lnum)
    ON DELETE SET NULL ON UPDATE CASCADE
);

-- Inserting into a table 
-- INSERT INTO bank (bcode, bname, h_address) 
--   VALUES (555, 'My Bank', 'Sydney");

--Bank:
INSERT INTO bank (bcode,bname,h_address)
VALUES (555, 'My Bank', 'Sydney');

--Bank Branch:
INSERT INTO bank_branch (bnum,bco,b_address)
VALUES
(1001,555,'Armidale'),
(1002,555,'Guyra'),
(1003,555,'Sydney'),
(1004,555,'Melbourne');

--Customer:
INSERT INTO customer (ssn,name,phone,c_address)
VALUES
(501,'Sonya Carter','(257) 231-1533','Melbourne, VIC 2222'),
(502,'Randolph Oliver','(476) 738-4677','Armidale, NSW 2350'),
(503,'Colleen Hall','(185) 336-3857','Armidale, NSW 2350'),
(504,'Wilson Peterson','(543) 667-3820','Armidale, NSW 2350'),
(505,'Bessie Snyder','(683) 431-7713',' Sydney, NSW 9999'),
(506,'Erik Drake','(771) 495-9114','Armidale, NSW 2350'),
(507,'Terry Little','(594) 141-5933','Hobart, TAS 1568'),
(508,'Mindy Hines','(522) 563-9580','Armidale, NSW 2350'),
(509,'Samantha Bass','(483) 131-6173','Melbourne, VIC 2222'),
(510,'Wilbert Harrington','(667) 217-7407','Guyra, NSW 2352');

--Account:
INSERT INTO account (anum,bco,bno,atype)
VALUES
(1,555,1001,'Saver'),
(2,555,1001,'Spender'),
(3,555,1001,'Student'),
(4,555,1001,'Investor'),
(5,555,1002,'Saver'),
(6,555,1002,'Spender'),
(7,555,1002,'Student'),
(8,555,1002,'Investor'),
(9,555,1003,'Saver'),
(10,555,1003,'Spender'),
(11,555,1003,'Student'),
(12,555,1003,'Investor'),
(13,555,1004,'Saver'),
(14,555,1004,'Spender'),
(15,555,1004,'Student'),
(16,555,1004,'Investor');

--Loan:
INSERT INTO loan (lnum, bco, bno,ltype)
VALUES
(111,555,1001,'House'),
(112,555,1001,'Car'),
(113,555,1001,'General'),
(114,555,1002,'House'),
(115,555,1002,'Car'),
(116,555,1002,'General'),
(117,555,1003,'House'),
(118,555,1003,'Car'),
(119,555,1003,'General'),
(120,555,1004,'House'),
(121,555,1004,'Car'),
(122,555,1004,'General');

--customer_account:
INSERT INTO customer_account (balance,ano,cssn)
VALUES
(3457,14,501),
(844,3,502),
(2184,2,503),
(4868,1,504),
(21462,9,505),
(171139,5,506),
(6542,16,508),
(284,3,508),
(3996,6,510),
(5,3,506),
(24228,13,509),
(6954537,12,507);

--customer_loan:
INSERT INTO customer_loan (lno, cssn, amount)
VALUES
(111,506,250000),
(115,510,10000),
(122,501,500),
(117,505,1000000);

-- Queries
-- UPDATE customer_account
--   SET balance = 50 
--     WHERE cssn = 506 AND ano = 3;

-- -- Display customer account numbers with balances greater than 500$
-- SELECT *
--   FROM customer_account
--   WHERE balance > 500;
--
-- -- Display customer loans with amounts less than  1000$
-- SELECT * 
--   FROM customer_loan
--   WHERE amount = 10000;
--
-- -- Display customer names with ssns between 502 and 504 
-- SELECT * FROM customer
--   WHERE ssn >= 502 AND ssn <= 504;
--
-- -- Display customers that are from Armidale
-- SELECT * 
--   FROM customer 
--   WHERE c_address LIKE '%Armidale%'; 
--
-- -- Display names and phone numbers for cistomers with an area code starting with 5 
-- SELECT *
--   FROM customer 
--   WHERE phone LIKE '(5%';
--
-- -- Display bank and branch numbers for student accounts in one cell, preceed by bsb 
-- SELECT DISTINCT 'BSB: '||bco||bno 
--   FROM account;
--
-- -- Join customer_account and account 
-- SELECT 'BSB: '||a.bco||a.bno AS BSB  
--   FROM account AS a
--     JOIN customer_account AS ca ON ca.ano = a.anum;
--
-- -- Display customers with a student account in armidale branch 
-- SELECT * 
--   FROM customer AS c 
--     JOIN customer_account AS ca ON c.ssn = ca.cssn 
--     JOIN account AS a ON ca.ano = a.anum 
--     JOIN bank_branch AS bb ON a.bno = bb.bnum
--   WHERE bb.b_address LIKE '%Armidale%' AND a.atype = 'Student';
--
-- -- Display average, total and count of balances per branch 
-- SELECT AVG(ca.balance), SUM(ca.balance), COUNT(ca.balance)
--   FROM bank_branch AS bb 
--     JOIN account AS a ON bb.bnum = a.bno 
--     JOIN customer_account AS ca ON a.anum = ca.ano 
--   GROUP BY bb.bnum;
--
-- -- List the number of accounts and names of customers who have more than one bank account. 
-- SELECT COUNT(ca.cssn), c.name 
--   FROM customer AS c
--     JOIN customer_account AS ca ON c.ssn = ca.cssn 
--   GROUP BY c.ssn 
--     HAVING COUNT(ca.cssn) > 1;

-- Create a view with customer name, bsb, acc, branch location, amount, loan type 
CREATE VIEW customer_loans
AS SELECT c.name "Customer Name", bb.bnum||''||bb.bco "BSB", l.lnum||''||   "Account Number", 
    bb.b_address "Address", cl.amount "Amount", l.ltype "Loan Type"
     FROM customer AS c 
       JOIN customer_loan AS cl ON c.ssn = cl.cssn 
       JOIN loan AS l ON cl.lno = l.lnum 
       JOIN bank_branch AS bb ON l.bno = bb.bnum;

SELECT ca.balance, a.atype 
    FROM customer_account AS ca 
    LEFT OUTER JOIN account AS a ON ca.ano = a.anum 
  WHERE a.atype = 'Student';
  

SELECT c.name, COUNT(a.atype)
  FROM customer AS c 
    JOIN customer_account AS ca ON c.ssn = ca.cssn 
    JOIN account AS a ON ca.ano = a.anum
  GROUP BY c.name 
    HAVING COUNT(a.atype) > 1;

SELECT c.ssn 
  FROM customer AS c 
    JOIN customer_account AS ca ON c.ssn = ca.cssn 
    JOIN account AS a ON ca.ano = a.anum 
    JOIN bank_branch AS bb ON a.bno = bb.bnum 
  WHERE bb.b_address Like '%Armidale%' AND a.atype = 'Student' AND c.ssn NOT IN 
    (SELECT c1.ssn
      FROM customer AS c1 
        JOIN  customer_account AS ca1 ON c1.ssn = ca1.cssn 
    JOIN account AS a1 ON ca1.ano = a1.anum 
    JOIN bank_branch AS bb1 ON a1.bno = bb1.bnum 
  WHERE bb1.b_address != '%Armidale%' AND a.atype != 'Student'); 

SELECT c.name 
  FROM customer AS c 
    JOIN customer_loan AS cl ON c.ssn = cl.cssn
    JOIN loan AS l on cl.lno = l.lnum 
  WHERE ltype = 'General' AND NOT EXISTS 
    (SELECT c2.ssn 
      FROM customer AS c2
        JOIN customer_loan AS cl2 ON c2.ssn = cl2.cssn
        JOIN loan AS l2 on cl2.lno = l2.lnum 
     WHERE ltype = 'General' AND c2.ssn != c.ssn);



