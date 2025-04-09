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

