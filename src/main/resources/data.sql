
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Maandag 1 maart 2021, 20:00 uur', '30', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Dinsdag 2 maart 2021, 19:00 uur', '15', 'Corecamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Woensdag 3 maart 2021, 19:30 uur', '22', 'Fitcamp','gemiddeld');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Donderdag 4 maart 2021, 18:00 uur', '25', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Vrijdag 5 maart 2021, 18:30 uur', '30', 'Fitcamp','beginner');

INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('dekkersmandy@hotmail.com', 'Mandy', 'Dekkers', 'password', 'mandydekkers');
INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('janklaassen@hotmail.com', 'Jan', 'Klaassen', 'pasword', 'janklaassen');
INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('piet@hotmail.com', 'Pietje', 'Puk', 'password', 'pietjepuk');

INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('De Moer', '17', '5176NA', 'Dorsvlegel');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Dongen', '5', '8095PO', 'Maas');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Kaatsheuvel', '9', '5555LK', 'Laan');

INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 1, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 2, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 3, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 1, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 2, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 3, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 1, 'testcomment');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 2, 'testcomment');