CREATE TABLE library_branch(
  branch_id    VARCHAR(10) PRIMARY KEY,
  branch_name  VARCHAR(20) NOT NULL, 
  address      VARCHAR(50)
);

CREATE TABLE borrower(
  card_no    VARCHAR(20) PRIMARY KEY,
  name       VARCHAR(20) NOT NULL,
  address    VARCHAR(50) NOT NULL,
  phone      VARCHAR(20) 
);

CREATE TABLE publisher(
  name     VARCHAR(20) PRIMARY KEY,
  address  VARCHAR(50),
  phone    VARCHAR(20)
);

CREATE TABLE book(
  book_id         VARCHAR(20) PRIMARY KEY,
  title           VARCHAR(50) NOT NULL,
  publisher_name  VARCHAR(20) NOT NULL,
  FOREIGN KEY (publisher_name) REFERENCES publisher (name) 
    ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE book_author(
  book_id      VARCHAR(20),
  author_name  VARCHAR(50),
  PRIMARY KEY (book_id, author_name),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE book_copies(
  book_id        VARCHAR(20),
  branch_id      VARCHAR(20),
  no_of_copies   VARCHAR(20) CHECK (no_of_copies >=0),
  PRIMARY KEY (book_id, branch_id),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (branch_id) REFERENCES library_branch (branch_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE book_loans(
  book_id        VARCHAR(20),
  branch_id      VARCHAR(20),
  card_no        VARCHAR(20),
  date_out       DATE NOT NULL,
  due_date       DATE NOT NULL, 
  PRIMARY KEY (book_id, branch_id, card_no),
  FOREIGN KEY (book_id) REFERENCES book (book_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (branch_id) REFERENCES library_branch (branch_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (card_no) REFERENCES borrower (card_no)
    ON DELETE CASCADE ON UPDATE CASCADE
);




