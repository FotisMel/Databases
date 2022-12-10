-- CREATE STATEMENTS

-- CONSTRAINT CONSTRAINT: [referenced table (abbr.)]_[current field (abbr.)]_[referenced field (abbr.)].
-- CONSTRAINT EXAMPLE: TABLE guide REFERENCES TABLE worker ON FIELD wrk_AT -> wrk_gui_at.

-- ALL INTEGER PRIMARY KEYS ARE SET TO AUTO_INCREMENT
-- FOR EASE OF USE WHEN INSERTING RECORDS.

CREATE DATABASE IF NOT EXISTS travel_agency;

USE IF EXISTS travel_agency;

-- A branch CANNOT HAVE A NULL VALUE IN ANY OF ITS FIELDS.

CREATE TABLE branch(

br_code INT(11) NOT NULL AUTO_INCREMENT,
br_street VARCHAR(30) NOT NULL, 
br_num INT(4) NOT NULL,
br_city VARCHAR(30) NOT NULL,

PRIMARY KEY(br_code)

);

CREATE TABLE IF NOT EXISTS phones(

ph_br_code INT(11) NOT NULL,
ph_number CHAR(10) NOT NULL,

PRIMARY KEY(ph_br_code, ph_number),

CONSTRAINT br_ph_code
FOREIGN KEY(ph_br_code)
REFERENCES branch(br_code)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- IF A branch IS DELETED SET ALL ITS workers'wrk_br_code
-- FIELDS TO NULL FOR EASE OF TRANSFERING workers.

CREATE TABLE IF NOT EXISTS worker(

wrk_AT CHAR(10) NOT NULL,
wrk_name VARCHAR(20) DEFAULT 'uknown',
wrk_lname VARCHAR(20) DEFAULT 'uknown',
wrk_salary FLOAT(7, 2),
wrk_br_code INT(11),

PRIMARY KEY(wrk_AT),

CONSTRAINT br_wrk_code
FOREIGN KEY(wrk_br_code)
REFERENCES branch(br_code)
ON DELETE SET NULL ON UPDATE CASCADE

);

-- A driver CANNOT HAVE A NULL VALUE IN ANY OF ITS FIELDS.

CREATE TABLE IF NOT EXISTS driver(

drv_AT CHAR(10) NOT NULL,
drv_license ENUM('A', 'B', 'C', 'D') NOT NULL,
drv_route ENUM('LOCAL', 'ABROAD') NOT NULL,
drv_experience TINYINT(4) DEFAULT 0,

PRIMARY KEY(drv_AT),

CONSTRAINT wrk_drv_at
FOREIGN KEY(drv_AT)
REFERENCES worker(wrk_AT)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- AN admin MIGHT NOT HAVE A adm_diploma SO IT'S
-- THE ONLY FIELD ALLOWED TO HAVE A NULL VALUE.

CREATE TABLE IF NOT EXISTS admin(

adm_AT CHAR(10) NOT NULL,
adm_type ENUM('LOGISTICS', 'ADMINISTRATIVE', 'ACCOUNTING') NOT NULL,
adm_diploma VARCHAR(200),

PRIMARY KEY(adm_AT),

CONSTRAINT wrk_adm_at
FOREIGN KEY(adm_AT)
REFERENCES worker(wrk_AT)
ON DELETE CASCADE ON UPDATE CASCADE

);



CREATE TABLE IF NOT EXISTS manages(

mng_adm_AT CHAR(10) NOT NULL,
mng_br_code INT(11) NOT NULL,

PRIMARY KEY(mng_adm_AT, mng_br_code),

CONSTRAINT adm_mng_at
FOREIGN KEY(mng_adm_AT)
REFERENCES admin(adm_AT)
ON DELETE CASCADE ON UPDATE CASCADE,

CONSTRAINT br_mng_code
FOREIGN KEY(mng_br_code)
REFERENCES branch(br_code)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- ΕΝΑΣ guide ΠΡΕΠΕΙ ΝΑ ΕΧΕΙ gui_cv.

CREATE TABLE IF NOT EXISTS guide(

gui_AT CHAR(10) NOT NULL,
gui_cv TEXT NOT NULL,

PRIMARY KEY(gui_AT),

CONSTRAINT wrk_gui_at
FOREIGN KEY(gui_AT)
REFERENCES worker(wrk_AT)
ON DELETE CASCADE ON UPDATE CASCADE

);



CREATE TABLE IF NOT EXISTS languages(

lng_gui_AT CHAR(10) NOT NULL,
lng_language VARCHAR(30) NOT NULL,

PRIMARY KEY(lng_gui_AT, lng_language),

CONSTRAINT gui_lng_at
FOREIGN KEY(lng_gui_AT)
REFERENCES guide(gui_AT)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- A trip MIGHT NOT HAVE A tr_return, A tr_cost, A driver OR A guide SPECIFIED,

CREATE TABLE IF NOT EXISTS trip(

tr_id INT(11) NOT NULL AUTO_INCREMENT,
tr_departure datetime NOT NULL,
tr_return datetime,
tr_maxseats TINYINT(4) NOT NULL,
tr_cost FLOAT(7, 2),
tr_br_code INT(11) NOT NULL,
tr_gui_AT CHAR(10),
tr_drv_AT CHAR(10),

PRIMARY KEY(tr_id),

CONSTRAINT br_tr_code
FOREIGN KEY(tr_br_code)
REFERENCES branch(br_code)
ON DELETE CASCADE ON UPDATE CASCADE,

CONSTRAINT gui_tr_at
FOREIGN KEY(tr_gui_AT)
REFERENCES guide(gui_AT)
ON DELETE SET NULL ON UPDATE CASCADE,

CONSTRAINT drv_tr_at 
FOREIGN KEY(tr_drv_AT)
REFERENCES driver(drv_AT)
ON DELETE SET NULL ON UPDATE CASCADE

);

-- AN event CANNOT HAVE A NULL VALUE IN ANY OF ITS FIELDS.

CREATE TABLE IF NOT EXISTS event(

ev_tr_id INT(11) NOT NULL,
ev_start datetime NOT NULL,
ev_end datetime NOT NULL,
ev_descr TEXT NOT NULL,

PRIMARY KEY(ev_tr_id, ev_start),

CONSTRAINT tr_ev_id
FOREIGN KEY(ev_tr_id)
REFERENCES trip(tr_id)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- A destination MUST HAVE dst_rtype & dst_language VALUES.

CREATE TABLE IF NOT EXISTS destination(

dst_id INT(11) NOT NULL AUTO_INCREMENT,
dst_name VARCHAR(50),
dst_descr TEXT,
dst_rtype ENUM('LOCAL', 'ABROAD') NOT NULL,
dst_language VARCHAR(30) NOT NULL,
dst_location INT(11),

PRIMARY KEY(dst_id),

CONSTRAINT dst_dst_id
FOREIGN KEY(dst_location)
REFERENCES destination(dst_id)
ON DELETE CASCADE ON UPDATE CASCADE

);



CREATE TABLE IF NOT EXISTS travel_to(

to_tr_id INT(11) NOT NULL,
to_dst_id INT(11) NOT NULL,
to_arrival datetime,
to_departure datetime,

PRIMARY KEY(to_tr_id, to_dst_id),

CONSTRAINT tr_to_id
FOREIGN KEY(to_tr_id)
REFERENCES trip(tr_id)
ON DELETE CASCADE ON UPDATE CASCADE,

CONSTRAINT dst_to_id
FOREIGN KEY(to_dst_id)
REFERENCES destination(dst_id)
ON DELETE CASCADE ON UPDATE CASCADE

);

-- A reservation CANNOT HAVE A NULL VALUE IN ANY OF ITS FIELDS.

CREATE TABLE IF NOT EXISTS reservation(

res_tr_id INT(11) NOT NULL,
res_seatnum TINYINT(4) NOT NULL,
res_name VARCHAR(20) NOT NULL,
res_lname VARCHAR(20) NOT NULL,
res_isadult ENUM('ADULT', 'MINOR') NOT NULL,

PRIMARY KEY(res_tr_id, res_seatnum),

CONSTRAINT tr_res_id
FOREIGN KEY(res_tr_id)
REFERENCES trip(tr_id)
ON DELETE CASCADE ON UPDATE CASCADE

);