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
