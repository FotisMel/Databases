DELIMITER $

--

CREATE PROCEDURE IF NOT EXISTS assign_driver(
IN new_wrk_AT CHAR(10),
IN new_wrk_name VARCHAR(20),
IN new_wrk_lname VARCHAR(20),
IN new_wrk_salary FLOAT(7, 2),
IN new_drv_license ENUM('A', 'B', 'C', 'D'),
IN new_drv_route ENUM('LOCAL', 'ABROAD'),
IN new_drv_experience TINYINT(4)
)
BEGIN
	DECLARE min_value INT;
	DEClARE selected_branch INT(11);

	SELECT MIN(number_of_drivers)
	INTO min_value
	FROM(
		SELECT COUNT(*) AS 'number_of_drivers'
		FROM branch
		INNER JOIN worker ON br_code = wrk_br_code
		INNER JOIN driver ON wrk_AT = drv_AT
		GROUP BY br_code
	) AS temp;
	
	SELECT br_code
	INTO selected_branch
	FROM(
		SELECT br_code, COUNT(*) AS 'number_of_drivers'
		FROM branch
		INNER JOIN worker ON br_code = wrk_br_code
		INNER JOIN driver ON wrk_AT = drv_AT
		GROUP BY br_code
		HAVING number_of_drivers = min_value
		LIMIT 1
	) AS temp;
	
	INSERT INTO worker VALUES(
	new_wrk_AT,
	new_wrk_name,
	new_wrk_lname,
	new_wrk_salary,
	selected_branch
	);
	
	INSERT INTO driver VALUES(
	new_wrk_AT,
	new_drv_license,
	new_drv_route,
	new_drv_experience
	);
END $

--

CREATE PROCEDURE IF NOT EXISTS check_branch_trips(
IN selected_br_code INT(11),
IN first_date DATE,
IN second_date DATE
)
BEGIN
	select 
		tr_cost, tr_maxseats,
		count(*) as 'reservations', tr_maxseats - count(*) as 'empty seats',
		d.wrk_lname AS 'Driver lname', d.wrk_name AS 'Driver name',
		g.wrk_lname AS 'Guide lname', g.wrk_name AS 'Guide name',
		tr_departure, tr_return
	from branch
	inner join trip on br_code = tr_br_code
	inner join reservation on tr_id = res_tr_id
	left join guide on gui_AT = tr_gui_AT
	left join driver on drv_AT = tr_drv_AT
	left join worker AS g on gui_AT = g.wrk_AT
	left join worker AS d on drv_AT = d.wrk_AT
	where br_code = selected_br_code
		AND tr_departure >= first_date
		AND tr_departure <= second_date
	group by tr_id;
END $

--

CREATE PROCEDURE IF NOT EXISTS delete_admin(
IN selected_name VARCHAR(20),
IN selected_lname VARCHAR(20)
)
BEGIN
	DECLARE check_AT CHAR(10);
	DECLARE check_branch INT(11);
	
	SET check_AT = NULL;
	SET check_branch = NULL;
	
	SELECT adm_AT
	INTO check_AT
	FROM worker
	LEFT JOIN admin ON wrk_AT = adm_AT
	WHERE wrk_name = selected_name
		AND wrk_lname = selected_lname;
	
	IF(check_AT IS NULL) THEN
		SELECT 'This worker is not an admin.' AS 'Message';
	ELSE
		SELECT mng_br_code
		INTO check_branch
		FROM manages
		WHERE mng_adm_AT = check_AT;
		
		IF(check_branch IS NULL) THEN
			DELETE FROM worker
			WHERE wrk_AT = check_AT;
			
			SELECT 'Deleted successfully!' AS 'Message';
		ELSE
			SELECT 'This admin manages a branch, so he cannot be deleted.' AS 'Message';
		END IF;
	END IF;
END $

--

CREATE PROCEDURE IF NOT EXISTS find_reservation_offer_by_fee(
IN bottom_limit FLOAT(7, 2),
IN upper_limit FLOAT(7, 2)
)
BEGIN
	SELECT res_ofr_lname, res_ofr_name
	FROM reservation_offer
	WHERE res_ofr_fee >= bottom_limit
		AND res_ofr_fee <= upper_limit;
END $

--

CREATE PROCEDURE IF NOT EXISTS find_reservation_by_last_name(IN selected_lname VARCHAR(20))
BEGIN
	SELECT res_ofr_lname, res_ofr_name, res_ofr_ofr_id, count(*)
	FROM reservation_offer
	WHERE res_ofr_lname = selected_lname
	GROUP BY res_ofr_name, res_ofr_ofr_id;
END $

--

DELIMITER ;

--

CREATE INDEX reservation_offer_res_ofr_fee_index
USING BTREE
ON reservation_offer(res_ofr_fee);

--

CREATE INDEX reservation_offer_res_ofr_lname_index
USING BTREE
ON reservation_offer(res_ofr_lname);