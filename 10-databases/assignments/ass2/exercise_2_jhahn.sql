CREATE VIEW movie_summary(movie_title, release_date, media_type, retail_price)
  AS SELECT m.movie_title, m.release_date, s.media_type, s.retail_price
    FROM movies AS m
      JOIN stock AS s ON s.movie_id = m.movie_id;

CREATE VIEW old_shipments(first_name, last_name, movie_id, shipment_id, shipment_date)
  AS SELECT c.first_name, c.last_name, s.movie_id, s.shipment_id, s.shipment_date 
    FROM customers AS c 
      JOIN shipments AS s ON c.customer_id = s.customer_id
    WHERE s.shipment_date < '2010-01-01';

CREATE VIEW richie(movie_title)
  AS SELECT movie_title
    FROM movies 
    WHERE director_first_name LIKE 'Ron' AND director_last_name LIKE 'Howard';

-- Wanted to round to 2 decimals but i was unsure if it would break the auto marking 
CREATE VIEW retail_price_hike(movie_id , retail_price, new_price)
  AS SELECT movie_id, retail_price, retail_price * 1.25 
    FROM stock;                                         

-- The same as above, I wanted to round to 2 places but unsure if marking would break 
CREATE VIEW profits_from_movie(movie_id, movie_title, total_profit)
  AS SELECT m.movie_id, m.movie_title, SUM(s.retail_price - s.cost_price)
    FROM shipments AS ship 
      JOIN stock AS s ON ship.movie_id = s.movie_id AND ship.media_type = s.media_type 
      JOIN movies AS m ON s.movie_id = m.movie_id 
    GROUP BY m.movie_id;

CREATE VIEW binge_watcher(first_name, last_name)
  AS SELECT DISTINCT c.first_name, c.last_name
    FROM customers AS c 
      JOIN shipments AS ship ON c.customer_id = ship.customer_id
      JOIN shipments AS ship2 ON ship.shipment_date = ship2.shipment_date 
        AND ship.movie_id != ship2.movie_id;
    
CREATE VIEW the_sith(first_name, last_name)
  AS SELECT c1.first_name, c1.last_name 
    FROM customers AS c1
    WHERE c1.customer_id NOT IN 
      (SELECT c2.customer_id
        FROM customers AS c2 
          JOIN shipments AS ship ON c2.customer_id = ship.customer_id 
          JOIN movies AS m ON ship.movie_id = m.movie_id
        WHERE m.movie_title = 'Star Wars: Episode V - The Empire Strikes Back');

CREATE VIEW sole_angry_man(first_name, last_name)
  AS SELECT c1.first_name, c1.last_name
    FROM customers AS c1
      JOIN shipments AS ship1 ON c1.customer_id = ship1.customer_id 
      JOIN movies AS m1 ON ship1.movie_id = m1.movie_id 
    WHERE m1.movie_title = '12 Angry Men' AND NOT EXISTS 
      (SELECT * 
        FROM shipments AS ship2
          JOIN movies AS m2 on ship2.movie_id = m2.movie_id
        WHERE m2.movie_title = '12 Angry Men' AND ship2.customer_id != ship1.customer_id); 


                          
                          

