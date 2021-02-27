INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');


INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Maandag 1 maart 2021, 20:00 uur', '5', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Dinsdag 2 maart 2021, 19:00 uur', '8', 'Corecamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Woensdag 3 maart 2021, 19:30 uur', '4', 'Fitcamp','gemiddeld');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Donderdag 4 maart 2021, 18:00 uur', '7', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Vrijdag 5 maart 2021, 18:30 uur', '8', 'Wandelcamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Zaterdag 6 maart 2021, 20:00 uur', '5', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Zondag 7 maart 2021, 19:00 uur', '8', 'Corecamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Maandag 8 maart 2021, 19:30 uur', '4', 'Fitcamp','gemiddeld');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Dinsdag 9 maart 2021, 18:00 uur', '7', 'Set-upcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Woensdag 10 maart 2021, 18:30 uur', '8', 'Fitcamp','beginner');


INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('De Moer', '17', '5176NA', 'Dorsvlegel');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Dongen', '5', '8095PO', 'Maas');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Kaatsheuvel', '9', '5555LK', 'Laan');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Amsterdam', '896', '5896OP', 'Waal');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Arhem', '17', '89636NT', 'Molenbeek');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Den Bosch', '5', '8095PO', 'Vaarweg');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Tilburg', '56', '5555LK', 'Ekster');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Boxtel', '1', '5896OP', 'Hooivork');


INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('dekkersmandy@hotmail.com', 'Mandy', 'Dekkers', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'mandydekkers', 1);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('janklaassen@hotmail.com', 'Jan', 'Klaassen', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'janklaassen', 2);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('pietpuk@hotmail.com', 'Pietje', 'Puk', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'pietjepuk', 3);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('sjaaksjakie@hotmail.com', 'Sjakie', 'Sjaak', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'sjakie', 4);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('alberteinstein@hotmail.com', 'Albert', 'Einstein', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'mandydekkers', 5);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('jansmit@hotmail.com', 'Jan', 'Smit', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'janklaassen', 6);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('willemalexander@hotmail.com', 'Willem', 'Alexander', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'willemalexander', 7);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('jantjebeton@hotmail.com', 'Jantje', 'Beton', '$2y$12$fD7Zrm4IfEPNZuhuJ8X0R..zrXnButMTQCgp3DrMASoUUa80S4goC', 'jantjebeton', 8);

alter sequence native restart with 10;

INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 1, 'last van enkel');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 2, 'wellicht iets later aanwezig');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 1, 'moet 10 min. eerder weg');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 2, 'test4');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 3, 'test5');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 4, 'test6');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (4, 3, 'test7');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (4, 4, 'test8');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (5, 5, 'test9');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (5, 6, 'test10');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (6, 5, 'test11');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (6, 6, 'test12');
