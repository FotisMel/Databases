-- INSERT STATEMENTS

-- ALL INSERTED VALUES ARE USED TO DEPICT
-- A NORMAL DATASET, INCLUDING EDGE CASES.

INSERT INTO branch VALUES

(NULL, 'Papadopoulou', 22, 'Volos'),
(NULL, 'Xarilaou Trikoupi', 43, 'Athina'),
(NULL, 'Maizwnos', 78, 'Patra'),
(NULL, 'Don Kixwti', 81, 'Thessaloniki'),
(NULL, 'Paulou Mela', 114, 'Iwannina'),
(NULL, 'Ragkavi', 56, 'Rodos'),
(NULL, 'Dimitriadi', 09, 'Trikala'),
(NULL, 'Triwn Nauarxwn', 16, 'Patra'),
(NULL, 'Amerikis', 47, 'Lamia'),
(NULL, 'Orfeos', 25, 'Mutilini'),
(NULL, 'Xaralampous', 124, 'Sparti'),
(NULL, 'Korai', 235, 'Athina'

);

INSERT INTO phones VALUES

(00000000001, '2421083671'),
(00000000002, '2107308002'),
(00000000003, '2610968475'),
(00000000004, '2310723938'),
(00000000004, '2310092334'),
(00000000005, '2651060987'),
(00000000006, '2241023450'),
(00000000007, '2431009236'),
(00000000009, '2231023405'),
(00000000010, '2251033859'),
(00000000011, '2731049746'),
(00000000012, '2251042768'),
(00000000003, '2610084882'),
(00000000004, '2310234597')

;

INSERT INTO worker VALUES

(NULL, 'Nikolaos', 'Dimitriadis', 65000, 1),
(NULL, 'Lazaros', 'Oikonomou', 45000, 2),
(NULL, 'Periklis', 'Dellakos', 87000, 1),
(NULL, 'Antonios', 'Dimosthenous', 56000, 3),
(NULL, 'Athanasios', 'Makris', 48000, 4),
(NULL, 'Theofilos', 'Sarris', 49000, 5),
(NULL, 'Maria', 'Papanikolaou', 112000, 4),
(NULL, 'Elpida', 'Prodromou', 34000, 5),
(NULL, 'Aggeliki', 'Maniatidi', 83000, 6),
(NULL, 'Basiliki', 'Galana', 23000, 7),
(NULL, 'Paraskevi', 'Kwstopoulou', 69000, 7),
(NULL, 'Andromaxi', 'Kwsti', 94000, 7),
(NULL, 'lydia', 'Anastopoulou', 72000, 6),
(NULL, 'Kwnstantinos', 'Karamanos', 100000, 1),
(NULL, 'Apostolos', 'Metaxas', 32000, 9),
(NULL, 'Dionusios', 'Liapis', 49000, 10),
(NULL, 'Lewnidas', 'Karakasis', 50000, 10),
(NULL, 'Despoina', 'Eleutheriadi', 60000, 12),
(NULL, 'Fwteini', 'Mauridi', 75000, 11),
(NULL, 'Iwanna', 'Lazarou', 85000, 11),
(NULL, 'Niki', 'Kexagia', 123000, 12),
(NULL, 'Marios', 'lampropoulos', 77000, 10),
(NULL, 'Kosmas', 'Papadakis', 52000, 7),
(NULL, 'Dimitris', 'Liakopoulos', 64000, 6),
(NULL, 'Alexandros', 'Germanos', 67000, 8),
(NULL, 'Andreas', 'Gennimatas', 47000, 3),
(NULL, 'Zaxarias', 'Maragkos', 81000, 7),
(NULL, 'Kuriakos', 'Giannaris', 86000, 2),
(NULL, 'Aristeidis', 'Iwannou', 40000, 5),
(NULL, 'Fwtios', 'Tsoukalas', 49000, 9)

;

INSERT INTO driver VALUES

('0000000008', 'A', 'Local' , 34),
('0000000010', 'A', 'Abroad', 234),
('0000000005', 'C', 'Local', 236),
('0000000019', 'D', 'Abroad', 456),
('0000000028', 'D', 'Abroad', 123),
('0000000016', 'C', 'Local',345),
('0000000014', 'A', 'Local', 78),
('0000000025', 'B', 'Abroad', 45)

;

INSERT INTO admin VALUES

('0000000001', 'Logistics', 'Data Management'),
('0000000002', 'Accounting', 'Economics'),
('0000000007', 'Accounting', NULL),
('0000000009', 'Administrative', 'Marketing'),
('0000000003', 'Logistics', 'Data Mining'),
('0000000012', 'Administrative', 'System Security'),
('0000000017', 'Logistics', 'Business Logistics'),
('0000000021', 'Accounting', 'Data Security'),
('0000000029', 'Administrative', 'Public Relations'),
('0000000018', 'Accounting', NULL),
('0000000013', 'Administrative', 'Marketing'),
('0000000006', 'Logistics', 'Business Management')

;

INSERT INTO manages VALUES

('0000000001', 00000000003),
('0000000021', 00000000007),
('0000000002', 00000000008),
('0000000018', 00000000004),
('0000000012', 00000000010),
('0000000017', 00000000001),
('0000000007', 00000000011),
('0000000029', 00000000006),
('0000000009', 00000000009),
('0000000003', 00000000002),
('0000000006', 00000000005),
('0000000013', 00000000012)

;

INSERT INTO guide VALUES

('0000000024', 'Modern English History'),
('0000000011', 'South-Eastern Chinese History'),
('0000000015', 'Northern European Studies'),
('0000000020', 'Middle Eastern History'),
('0000000022', 'Japanese & Korean Mythology-History'),
('0000000027', '18th Century French History & Political Studies'),
('0000000004', 'Balkan Studies'),
('0000000026', 'Egyptian Myths & Legends'),
('0000000030', 'Eastern European Studies - The U.S.S.R')

;

INSERT INTO languages VALUES

('0000000024', 'English'),
('0000000011', 'Cantonese'),
('0000000015', 'German'),
('0000000015', 'Dutch'),
('0000000020', 'Arabic'),
('0000000022', 'Japanese'),
('0000000022', 'Korean'),
('0000000022', 'Mandarin'),
('0000000027', 'French'),
('0000000004', 'Turkish'),
('0000000004', 'Boulgarian'),
('0000000026', 'Egyptian'),
('0000000027', 'Spanish'),
('0000000030', 'Russian'),
('0000000030', 'Ukrainian'),
('0000000030', 'Romanian'),
('0000000030', 'Polish')

;

INSERT INTO trip VALUES

(NULL, '2023-02-17 11:30:00', NULL, 96, 3400, 00000000006, '0000000024', '0000000008'),
(NULL, '2023-03-15 12:00:00', '2023-03-21 10:00:00', 187, 8745, 00000000012, '0000000015', NULL),
(NULL, '2023-04-05 10:00:00', '2023-04-18 11:45:00', 145, NULL, 00000000008, '0000000022', '0000000019'),
(NULL, '2023-04-21 12:15:00', '2023-04-28 10:00:00', 250, 2345, 00000000003, NULL, '0000000016'),
(NULL, '2023-05-16 18:00:00', '2023-05-26 10:15:00', 135, 9678, 00000000011, '0000000027', '0000000010'),
(NULL, '2023-07-13 14:45:00', '2023-07-19 10:00:00', 87, 3835, 00000000009, '0000000020', '0000000028'),
(NULL, '2023-08-27 08:15:00', '2023-08-31 10:45:00', 246, 6578, 00000000005, '0000000030', NULL)

;

INSERT INTO event VALUES

(00000000002, '2023-03-17 13:45:00', '2023-03-20 13:45:00', 'CES 2023'),
(00000000005, '2023-05-20 09:00:00', '2023-05-24 18:30:00', 'E3 2023'),
(00000000007, '2023-08-28 012:30:00', '2023-08-30 23:30:00', 'Super Smash Bros Ultimate Tournament'),
(00000000004, '2023-04-23 10:30:00', '2023-04-27 14:45:00', 'The Game Awards')

;

INSERT INTO destination VALUES

(NULL, , , , , ),
(NULL, NULL, , , , ),
(NULL, , , , 'English', ),
(NULL, , , , , NULL),
(NULL, , NULL, , 'German', ),
(NULL, , , , , ),
(NULL, , , , 'Japanese', NULL)

;

INSERT INTO travel_to VALUES

(00000000001, 00000000003, NULL, ),
(, , , ),
(, , NULL, ),
(, , , ),
(, , , NULL),
(, , NULL, ),
(, , , NULL)

;

INSERT INTO reservation VALUES

(00000000005, 78, 'Nikolaos', 'Anastasiadis', 'Adult'),
(00000000005, 79, 'Kwnstantinos', 'Anastasiadis', 'Minor'),
(00000000005, 80, 'Georgia', 'Manta', 'Adult'),
(00000000002, 94, 'Iwannis', 'Papadimitriou', 'Adult'),
(00000000003, 34, 'Elpida', 'Papadopoulou', 'Adult'),
(00000000003, 35, 'Antonios', 'Stamatopoulos', 'Minor'),
(00000000003, 36, 'Eleni', 'Stamatopoulou', 'Minor'),
(00000000003, 37, 'Petros', 'Stamatopoulos', 'Adult'),
(00000000006, 57, 'Iakwvos', 'Papagiwtou', 'Minor'),
(00000000006, 58, 'Apostolia', 'Dervisi', 'Adult'),
(00000000002, 112, 'Vasiliki', 'Petraki', 'Adult'),
(00000000007, 12, 'Andreas', 'Kwstopoulos', 'Adult'),
(00000000007, 13, 'Kalliopi', 'Dimopoulou', 'Adult'),
(00000000007, 14, 'Markos', 'Kwstopoulos', 'Minor'),
(00000000001, 88, 'Danai', 'Mauridi', 'Minor'),
(00000000001, 89, 'Alexandros', 'Mauridis', 'Adult'),
(00000000004, 235, 'Athanasios', 'Karpas', 'Adult'),
(00000000004, 236, 'Grigoria', 'Karpa', 'Minor')

;