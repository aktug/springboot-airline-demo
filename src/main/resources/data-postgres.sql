INSERT INTO airport(name)
VALUES ('Test-Airport#1'),
       ('Test-Airport#2');

INSERT INTO airline(name)
VALUES ('Test-Airline#1'),
       ('Test-Airline#2');

INSERT INTO route(from_id, to_id)
VALUES (1, 2),
       (2, 1);

INSERT INTO flight(current_price, date, quota, airline_id, route_id)
VALUES (50, '2019-12-05 12:00:00', 10, 1, 1),
       (100, '2019-12-05 13:00:00', 50, 1, 2);