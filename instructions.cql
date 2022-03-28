CREATE KEYSPACE IF NOT EXISTS sensors WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : '1' } ;

CREATE TABLE IF NOT EXISTS sensors.sensor_data(
sensor_id int,
day_key text,
created text,
sensor_name text,
description text,
device_id int,
uid int,
serial_number int,
model text,
active boolean,
value float,
PRIMARY KEY ((sensor_id, day_key), created)
);