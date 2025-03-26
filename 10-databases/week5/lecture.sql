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
    PRIMARY KEY (bnum, bco)
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
 


-- Lecture 10 

-- Return names of customers with more than 1 account type 
SELECT DISTINCT c.name FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn
    AND EXISTS
    (SELECT * FROM customer_account AS ca2
        WHERE c.ssn = ca2.cssn AND ca.ano != ca2.ano); 

-- As aggregation
SELECT c.name FROM customer AS c, customer_account AS ca 
    WHERE c.ssn = ca.cssn GROUP BY ca.cssn, c.name HAVING COUNT(ca.ano) > 1;


-- Return 

SELECT c.name FROM customer AS c, customer_loan as cl, loan AS l 
    WHERE c.ssn = cl.cssn AND cl.lno = l.lnum AND l.ltype = 'General'
    AND c.ssn NOT IN 
    (SELECT cl1.cssn FROM customer_loan as cl1, loan AS l 
    WHERE cl1.lno = l.lnum AND l.ltype != 'General');

-- Return names of cusrtomers who only have student accounts in armidale branch 
SELECT c.name FROM customer AS c, customer_account AS ca, bank_branch AS bb, account AS a 
    WHERE c.ssn = ca.cssn AND ca.ano = a.anum 
        AND a.atype = 'Student' AND bb.b_address = 'Armidale' 
        AND NOT EXISTS 
        (SELECT * FROM customer_account AS ca1 
        WHERE c.ssn = ca1.cssn AND ca.ano != ca1.ano);


