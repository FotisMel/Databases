CREATE TABLE IF NOT EXISTS temp_names(

name VARCHAR(20) NOT NULL,
lname VARCHAR(20) NOT NULL

);

--

LOAD DATA 
INFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/names.txt' 
INTO TABLE travel_agency.temp_names
FIELDS TERMINATED BY '\t'
ENCLOSED BY '"'
LINES TERMINATED BY '\r\n';

--

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 1, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 10001;

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 1, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 20001;

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 2, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 30001;

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 2, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 40001;

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 3, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 50001;

INSERT INTO reservation_offer(res_ofr_id, res_ofr_lname, res_ofr_name, res_ofr_ofr_id, res_ofr_fee)
SELECT NULL, lname, name, 3, ROUND(RAND()*(200 - 50 + 1) + 50, 0)
FROM temp_names;

ALTER TABLE reservation_offer AUTO_INCREMENT = 60001;

--

DROP TABLE IF EXISTS temp_names;

