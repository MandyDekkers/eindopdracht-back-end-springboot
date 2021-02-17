INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- INSERT INTO appuser_roles(app_user_id,roles_id) VALUES(1,1),(2,1),(3,1),(4,1);

INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Maandag 1 maart 2021, 20:00 uur', '30', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Dinsdag 2 maart 2021, 19:00 uur', '15', 'Corecamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Woensdag 3 maart 2021, 19:30 uur', '22', 'Fitcamp','gemiddeld');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Donderdag 4 maart 2021, 18:00 uur', '25', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Vrijdag 5 maart 2021, 18:30 uur', '30', 'Fitcamp','beginner');

INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('De Moer', '17', '5176NA', 'Dorsvlegel');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Dongen', '5', '8095PO', 'Maas');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Kaatsheuvel', '9', '5555LK', 'Laan');
INSERT INTO address (city, housenumber, postalcode, streetname) VALUES ('Amsterdam', '896', '5896OP', 'Waal');

INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('dekkersmandy@hotmail.com', 'Mandy', 'Dekkers', 'password', 'mandydekkers', 1);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('janklaassen@hotmail.com', 'Jan', 'Klaassen', 'pasword', 'janklaassen', 2);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('piet@hotmail.com', 'Pietje', 'Puk', 'password', 'pietjepuk', 3);
INSERT INTO appuser (email, first_name, last_name, password, username, address_id) VALUES ('sjaak@hotmail.com', 'Sjakie', 'Sjaak', 'password', 'sjakie', 4);

alter sequence native restart with 10;

INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 1, 'test1');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 2, 'test2');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 1, 'test3');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 2, 'test4');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 1, 'test5');
INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 2, 'test6');