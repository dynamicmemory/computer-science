CREATE TABLE bank (
    bcode INT PRIMARY KEY,
    bname VARCHAR(20),
    h_address VARCHAR(50)
);

CREATE TABLE customer (
    ssn INT PRIMARY KEY,
    name VARCHAR(30),
    c_address VARCHAR(50),
    phone VARCHAR(20)
);

CREATE TABLE bank_branch (
    bnum INT,
    bco INT, 
    b_address VARCHAR(50),
    PRIMARY KEY (bnum, bco),
    FOREIGN KEY (bco) REFERENCES bank (bcode)
        ON DELETE CASCADE ON UPDATE CASCADE 
);

CREATE TABLE account (
    anum INT PRIMARY KEY,
    bno INT,
    bco INT,
    atype VARCHAR(20),
    FOREIGN KEY (bno,bco) REFERENCES bank_branch (bnum,bco)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE loan (
    lnum INT PRIMARY KEY,
    bco INT,
    bno INT,
    ltype VARCHAR(20),
    FOREIGN KEY (bno,bco) REFERENCES bank_branch (bnum,bco)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE customer_account (
    cssn INT,
    ano INT,
    balance INT,
    PRIMARY KEY (cssn, ano),
    FOREIGN KEY (cssn) REFERENCES customer (ssn)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (ano) REFERENCES account (anum)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE customer_loan (
    cssn INT,
    lno INT,
    amount INT,
    PRIMARY KEY (cssn, lno),
    FOREIGN KEY (cssn) REFERENCES customer (ssn)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (lno) REFERENCES loan (lnum)
        ON DELETE SET NULL ON UPDATE CASCADE
);


-------------------------------------------------------------------------------
-- Inserting data 

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

INSERT INTO bank (bcode, bname, h_address)
    VALUES (555, 'My Bank', 'Sydney');


UPDATE customer_account
    SET balance = balance*100
    WHERE TRUE;



-----------------------------------------------------------------------------
-- Lecture 6 


SELECT * FROM customer_account
        WHERE balance >= 500000;

SELECT * FROM customer_loan
        WHERE amount < 1000000;

SELECT cssn,balance FROM customer_account
        WHERE balance >= 500000;

SELECT * FROM customer 
        WHERE ssn >= 502 AND ssn <= 504;

-- string examples 

SELECT * FROM customer 
        WHERE c_address LIKE '%Armidale%';

-- Pattern matching % wildcard like *
SELECT * FROM customer
        WHERE phone LIKE '(5%';

SELECT 'BSB:'||bco||bno, anum FROM account;

-- No doubles 
SELECT DISTINCT 'BSB:'||bco||bno, anum FROM account;

-- Join examples
SELECT * FROM account AS a
        JOIN customer_account AS ca ON ca.ano=a.anum;

SELECT ''||a.bco||a.bno AS BSB, ''||ca.ano||ca.cssn FROM account AS a    
        JOIN customer_account AS ca ON ca.ano=a.num;

-- Display customers with a student account from an Armidale branch 
SELECT c.name, a.atype, ca.balance, bb.b_address FROM customer AS c 
    JOIN customer_account AS ca ON ca.cssn = c.ssn
    JOIN account AS a ON a.anum = ca.ano
    JOIN bank_branch AS bb ON (a.bno,a.bco) = (bb.bnum, bb.bco)
    WHERE bb.b_address LIKE '%Armidale%' AND a.stype = 'Student';


-- Same thing but modern syntax
SELECT c.name, a.atype, ca.balance, bb.b_address FROM customer AS c, 
    customer_account AS ca, account AS a, bank_branch AS bb
    WHERE c.ssn = ca.cssn AND ca.ano = a.anum AND (a.bno, a.bco) = (bb.bnum, bb.bco) 
    AND bb.b_address LIKE '%Armidale%' AND a.stype = 'Student';

-- Display average, total and count of balances per branch
SELECT '$'||''||ROUND(AVG(ca.balance)/100, 2) AS "average balance", SUM(ca.balance), COUNT(ca.balance) 
    FROM customer_account AS ca, account AS a, bank_branch AS bb 
    WHERE ca.ano = a.ano AND (a.bno, a.bco) = (bb.bnum, bb.bco) GROUP BY (bb.bnum, bb.bco);

-- List the number of accounts and names of customer who have more thean one bank account 
SELECT c.ssn, c.name, COUNT(ca.cssn) FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn GROUP BY c.ssn HAVING COUNT(ca.cssn > 1);

-- creating views
CREATE VIEW account_details AS 
SELECT c.name, ''||a.bco||a.bno AS BSB, ''||ca.ano||ca.cssn AS ACC, '$'||''||ROUND(ca.balances)/100, 2) AS balance 
        FROM account AS a    
        JOIN customer_account AS ca ON ca.ano=a.number
        JOIN customer AS c ON ca.cssn = c.ssn;

-------------------------------------------------------------------------------
-- Lecure 8
CREATE VIEW customer_loans_view AS
    SELECT c.name AS Customer Name, 
           b.bco||''||b.bnum as BSB, 
           cl.lno||''||cl.cssn AS Acc,
           b.b_address AS Address, '$'||ROUND(cl.amount/100, 2) AS Amount, 
           l.ltype AS "Loan Type" 
    FROM customer AS c, bank_branch AS b, customer_loan AS cl, loan AS l 
    WHERE c.ssn = cl.cssn AND
          l.lnum = cl.lno AND (l.bco, l.bno)=(b.bco, b.bnum);


SELECT ca.cssn, a.atype FROM customer_account AS ca, account AS a WHERE ca.ano=a.anum 
    AND a.atype='Student';



SELECT ca.cssn, a.atype, a.anum FROM customer_account AS ca 
    RIGHT OUTER JOIN account AS a ON ca.ano = a.anum 
    WHERE a.atype = 'Student';


-- UNION customer_accouints and customer_loans

SELECT c.name, 'B:'||ca.balance AS Total FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn
    UNION
        SELECT c.name, 'L:'||cl.amount FROM customer AS c, customer_loan AS cl 
            WHERE c.ssn = cl.cssn;

-- Customers who have accounts, but not loans 

SELECT c.name FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn AND c.ssn NOT IN 
    (SELECT c.ssn FROM customer AS c, customer_loan as cl 
        WHERE c.ssn = cl.cssn);


SELECT c.name FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn 
    AND NOT EXISTS
    (SELECT * FROM customer AS c1, customer_loan as cl 
        WHERE c1.ssn = cl.cssn AND c.ssn = c1.ssn);


-- Customers who dont have an account in armidale branch 

SELECT DISTINCT c.name FROM customer AS c 
    WHERE c.ssn NOT IN 
    (SELECT ca.cssn FROM customer_account AS ca, account AS a, bank_branch AS bb
        WHERE ca.ano = a.anum AND (a.bno, a.bco) = (bb.bnum, bb.bco) AND 
        bb.b_address = 'Armidale');


SELECT DISTINCT c.name FROM customer AS c 
    WHERE NOT EXISTS 
    (SELECT * FROM customer_account AS ca, account AS a, bank_branch AS bb
        WHERE ca.ano = a.anum AND (a.bno, a.bco) = (bb.bnum, bb.bco) AND 
        bb.b_address = 'Armidale' AND c.ssn = ca.cssn);


-- Return true if more than one house loan exists 

SELECT EXISTS 
    (SELECT * FROM customer_loan AS cl, loan AS l 
        WHERE cl.lno = l.lnum AND l.ltype = 'House'
        AND EXISTS
        (SELECT * FROM customer_loan AS cl1, loan AS l1
            WHERE cl1.lno = l1.lnum AND l1.ltype = 'House' AND cl.cssn != cl1.cssn));
