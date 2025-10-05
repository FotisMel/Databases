DELIMITER $

--

CREATE TRIGGER IF NOT EXISTS trip_insert_log_trigger
AFTER INSERT 
ON trip
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Trip', 'Insert', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS trip_update_log_trigger
AFTER UPDATE 
ON trip
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Trip', 'UPDATE', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS trip_delete_log_trigger
AFTER DELETE 
ON trip
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Trip', 'Delete', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS reservation_insert_log_trigger
AFTER INSERT
ON reservation
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Reservation', 'Insert', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS reservation_update_log_trigger
AFTER UPDATE
ON reservation
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Reservation', 'Update', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS reservation_delete_log_trigger
AFTER DELETE
ON reservation
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Reservation', 'Delete', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS event_insert_log_trigger
AFTER INSERT
ON event
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Event', 'Insert', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS event_update_log_trigger
AFTER UPDATE
ON event
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Event', 'Update', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS event_delete_log_trigger
AFTER DELETE
ON event
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Event', 'Delete', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS travel_to_insert_log_trigger
AFTER INSERT
ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Travel_to', 'Insert', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS travel_to_update_log_trigger
AFTER UPDATE
ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Travel_to', 'Update', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS travel_to_delete_log_trigger
AFTER DELETE
ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Travel_to', 'Delete', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS destination_insert_log_trigger
AFTER INSERT
ON destination
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Destination', 'Insert', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS destination_update_log_trigger
AFTER UPDATE
ON destination
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Destination', 'Update', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS destination_delete_log_trigger
AFTER DELETE
ON destination
FOR EACH ROW
BEGIN
	DECLARE user_AT CHAR(10);
	
	SET user_AT = (
		SELECT user_IT_AT
		FROM current_active_user
	);
	
	INSERT INTO table_log VALUES
	('Destination', 'Delete', user_AT);
END $

--

CREATE TRIGGER IF NOT EXISTS chech_reservation_of_trip
BEFORE UPDATE
ON trip
FOR EACH ROW
BEGIN
	DECLARE num_of_reservations INT;

	SELECT COUNT(*)
	INTO num_of_reservations
	FROM trip
	INNER JOIN reservation ON tr_id = res_tr_id
	WHERE tr_id = OLD.tr_id;
	
	IF(num_of_reservations != 0) THEN
		SIGNAL SQLSTATE VALUE '45000'
		SET MESSAGE_TEXT = 'ERROR';
	END IF;
END $

--

CREATE TRIGGER IF NOT EXISTS check_worker_salary_change
BEFORE UPDATE
ON worker
FOR EACH ROW
BEGIN
	IF(OLD.wrk_salary > NEW.wrk_salary) THEN
		SIGNAL SQLSTATE VALUE '45000'
		SET MESSAGE_TEXT = 'ERROR';
	END IF;
END $

--

DELIMITER ;