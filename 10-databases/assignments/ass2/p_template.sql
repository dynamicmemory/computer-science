-- COSC210 Practical Assignment Template

-- Please complete the assignment questions using the view templates
-- provided below.

-- *******************************************************************
--                           IMPORTANT
-- *******************************************************************

-- Make sure that you do not alter the names of the views or their 
-- attribute values. If you do your assignment will not work in the
-- auto-marking software and you may lose marks!

-- *******************************************************************

-- 1. Create a view called 'movie_summary' which returns the movie_title, 
--    release_date, media_type and retail_price for all movies in the database. 
--    This will contain some duplicates for media_type. (10 Mark)
CREATE VIEW movie_summary(movie_title, release_date, media_type, retail_price)
  AS SELECT m.movie_title, m.release_date, s.media_type, s.retail_price
    FROM movies AS m, stock AS s
      WHERE m.movie_id = s.movie_id;
    
-- 2. Create a view called 'old_shipments' that lists the customer first_name 
--    and last_name, movie_id, shipment_id and shipment_date for every shipment
--    before 2010. (10 Mark)
CREATE VIEW old_shipments(first_name, last_name, movie_id, shipment_id, shipment_date)
  AS SELECT c.first_name, c.last_name, s.movie_id, s.shipment_id, s.shipment_date 
    FROM customers AS c, shipments AS s 
      WHERE c.customer_id = s.customer_id AND s.shipment_date < '2010-01-01';


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

-- 3. Create a view called 'richie' that Returns a list of all movies directed by Ron Howard
CREATE VIEW richie(movie_title)
  AS SELECT movie_title
    FROM movies 
      WHERE director_first_name LIKE 'Ron' AND director_last_name LIKE 'Howard';

-- 4. Create a view called 'retail_price_hike' that returns the movie_id, retail_price 
--    and a final column that contains the retail price increased by 25% (10 marks)
CREATE VIEW retail_price_hike(movie_id , retail_price, new_price)
  AS SELECT movie_id, retail_price, retail_price * 1.25 -- Wanted to round to 2 places, but unsure if it  
    FROM stock;                                         -- would break the auto marking.


--  5. Create a view called 'profits_from_movie' that returns the movie_id and 
--     movie_title for each movie along with the difference between the sum of 
--     the cost and retail values across all shipments for each movie. The results 
--     should be grouped by movie_title. (10 Mark)
CREATE VIEW profits_from_movie(movie_id, movie_title, total_profit)
  AS SELECT ship.movie_id, m.movie_title, ((SUM(s.retail_price) - SUM(s.cost_price)) * COUNT(s.movie_id))
    FROM movies AS m, stock AS s, shipments AS ship 
      GROUP BY ship.movie_id, m.movie_title;

--  6. Create a view called 'binge_watcher' that returns a list of customers 
--     (first_name and last_name) that have hired more than one movie on the same date. (10 Mark)
CREATE VIEW binge_watcher(first_name, last_name)
  AS SELECT

--  7. Create a view called 'the_sith' that returns the first name, last name of any 
--     customer who has not purchased any media_type of the movie named 'Star Wars: 
--     Episode V - The Empire Strikes Back'. (15 Marks)
CREATE VIEW the_sith(first_name, last_name)
  AS SELECT

-- 8. Create a view called 'sole_angry_man' that returns the first_name and last_name
--    of any customer (If one exists) who is the only customer to buy '12 Angry Men' 
--    (Note the customer may buy other movies as well, but if anyone else buys the movie,
--    no records should be returned). (15 Marks)
CREATE VIEW sole_angry_man(first_name, last_name)
  AS SELECT                            

                          
                          

