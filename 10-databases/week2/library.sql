CREATE TABLE library_branch (
  branch_id   INTEGER PRIMARY KEY,
  branch_name VARCHAR(20) NOT NULL,
  address     VARCHAR(50) NOT NULL
);


CREATE TABLE publisher (
  name    VARCHAR(30) PRIMARY KEY,
  address VARCHAR(50),
  phone   VARCHAR(20),
);


CREATE TABLE borrower (
  card_no INTEGER PRIMARY KEY,
  name    VARCHAR(30) NOT NULL,
  address VARCHAR(50) NOT NULL,
  phone   VARCHAR(20)
);


CREATE TABLE book (
  book_id        INTEGER PRIMARY KEY,
  title          VARCHAR(50) NOT NULL,
  publisher_name VARCHAR(30),
  FOREIGN KEY (pub_name) REFERENCES publisher(name)
    ON DELETE REJECT ON UPDATE CASCADE
);


CREATE TABLE book_author(
  book_id     INTEGER,
  author_name VARCHAR(30),
  PRIMARY KEY (book_id, book_author),
  FOREIGN KEY (book_id) REFERENCES book(book_id)
    ON DELETE CASCADE ON UPDATE CASCADE 
);


CREATE TABLE book_copies (
  book_id      INTEGER,
  branch_id    INTEGER,
  no_of_copies INTEGER CHECK (no_of_copies >= 0),
  PRIMARY KEY (book_id, branch_id),
  FOREIGN KEY (book_id) REFERENCES book(book_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id)
    ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE book_loans (
  book_id   INTEGER,
  branch_id INTEGER,
  card_no   INTEGER NOT NULL,
  date_out  DATE NOT NULL,
  due_date  DATE NOT NULL,
  PRIMARY KEY (book_id, branch_id, card_no),
  FOREIGN KEY (book_id) REFERENCES book(book_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (branch_id) REFERENCES library_branch(branch_id)
    ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (card_no) REFERENCES borrower(card_no) 
    ON DELETE REJECT ON UPDATE CASCADE
);
