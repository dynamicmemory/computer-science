-- It was ambiguous as to if we were meant to add a CREATE db line as well in 
-- this assignment, so i chose to add a DROP line instead of out right doing it or 
-- leaving it out.
DROP DATABASE IF EXISTS username_moviedirect;
CREATE DATABASE username_moviedirect;

\c username_moviedirect

CREATE TABLE customers (
    customer_id INTEGER PRIMARY KEY, 
    last_name   VARCHAR(50) NOT NULL,
    first_name  VARCHAR(50) NOT NULL,
    address     VARCHAR(200),
    city        VARCHAR(50),
    state       VARCHAR(3) NOT NULL CHECK (state IN ('NSW','VIC','QLD','ACT','TAS','NT','SA','WA')), 
    postcode    VARCHAR(8)
);


CREATE TABLE movies (
    movie_id            INTEGER PRIMARY KEY,
    movie_title         VARCHAR(100) NOT NULL,
    director_first_name VARCHAR(50) NOT NULL,
    director_last_name  VARCHAR(50) NOT NULL,
    genre               VARCHAR(20) NOT NULL CHECK (genre IN ('Action','Adventure','Comedy','Romance', 
                                           'Science Fiction','Documentary','Drama','Horror')),
    release_date        DATE,
    studio_name         VARCHAR(50)
);


CREATE TABLE stock (
    movie_id      INTEGER,
    media_type    VARCHAR(20) NOT NULL CHECK (media_type IN ('DVD','Blu-Ray','Stream-Media')),
    cost_price    REAL CHECK (cost_price > 0),
    retail_price  REAL CHECK (retail_price > 0),
    current_stock REAL CHECK (current_stock >= 0), 
    PRIMARY KEY (movie_id, media_type),
    FOREIGN KEY (movie_id) REFERENCES movies(movie_id)
        ON DELETE CASCADE ON UPDATE CASCADE 
);


CREATE TABLE shipments (
    shipment_id   INTEGER PRIMARY KEY,
    customer_id   INTEGER NOT NULL,
    movie_id      INTEGER NOT NULL,
    media_type    VARCHAR(20) NOT NULL,
    shipment_date DATE,
    FOREIGN KEY (movie_id, media_type) REFERENCES stock(movie_id, media_type)
        ON DELETE CASCADE ON UPDATE CASCADE, 
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);


