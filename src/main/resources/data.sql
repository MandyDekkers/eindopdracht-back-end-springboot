
INSERT INTO role(name) VALUES('ROLE_USER');
INSERT INTO role(name) VALUES('ROLE_ADMIN');

-- INSERT INTO appuser_roles(app_user_id,roles_id) VALUES(1,1),(2,1),(3,1),(4,1);

INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Maandag 1 maart 2021, 20:00 uur', '30', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Dinsdag 2 maart 2021, 19:00 uur', '15', 'Corecamp','beginner');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Woensdag 3 maart 2021, 19:30 uur', '22', 'Fitcamp','gemiddeld');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Donderdag 4 maart 2021, 18:00 uur', '25', 'Fitcamp','gevorderd');
INSERT INTO lesson (date, amount_members, name, niveau) VALUES ('Vrijdag 5 maart 2021, 18:30 uur', '30', 'Fitcamp','beginner');


INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('dekkersmandy@hotmail.com', 'Mandy', 'Dekkers', 'password', 'mandydekkers');
INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('janklaassen@hotmail.com', 'Jan', 'Klaassen', 'pasword', 'janklaassen');
INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('piet@hotmail.com', 'Pietje', 'Puk', 'password', 'pietjepuk');
INSERT INTO appuser (email, first_name, last_name, password, username) VALUES ('sjaak@hotmail.com', 'Sjakie', 'Sjaak', 'password', 'sjakie');

alter sequence native restart with 10;

INSERT INTO address (city, housenumber, postalcode, streetname, appuser_id) VALUES ('De Moer', '17', '5176NA', 'Dorsvlegel', 1);
INSERT INTO address (city, housenumber, postalcode, streetname, appuser_id) VALUES ('Dongen', '5', '8095PO', 'Maas', 2);
INSERT INTO address (city, housenumber, postalcode, streetname, appuser_id) VALUES ('Kaatsheuvel', '9', '5555LK', 'Laan', 3);
INSERT INTO address (city, housenumber, postalcode, streetname, appuser_id) VALUES ('Amsterdam', '896', '5896OP', 'Waal', 4);

-- INSERT INTO address (city, housenumber, postalcode, streetname, appuser) VALUES ('De Moer', '17', '5176NA', 'Dorsvlegel', 1);
-- INSERT INTO address (city, housenumber, postalcode, streetname, appuser) VALUES ('Dongen', '5', '8095PO', 'Maas', 2);
-- INSERT INTO address (city, housenumber, postalcode, streetname, appuser) VALUES ('Kaatsheuvel', '9', '5555LK', 'Laan', 3);
-- INSERT INTO address (city, housenumber, postalcode, streetname, appuser) VALUES ('Amsterdam', '896', '5896OP', 'Waal', 4);


-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 1, 'testcomment');
-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (1, 2, 'testcomment');
-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 1, 'testcomment');
-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (2, 2, 'testcomment');
-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 1, 'testcomment');
-- INSERT INTO reservation (appuser_id, lesson_id, comment) VALUES (3, 2, 'testcomment');