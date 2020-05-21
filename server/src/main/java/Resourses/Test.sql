CREATE EXTENSION "uuid-ossp";
CREATE EXTENSION pgcrypto;

create type rating as enum('G', 'PG', 'PG_13');
create type genre as enum('COMEDY', 'MUSICAL', 'FANTASY');

create table locations (
location_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
x integer NOT NULL,
y bigint,
z integer NOT NULL,
location_name varchar(100) NOT NULL
);

create table coords (
coords_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
x integer NOT NULL,
y real
);

create table directors (
director_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
director_name varchar(100) NOT NULL,
birthday date NOT NULL default current_timestamp,
height double precision NOT NULL check (height > 0),
weight real NOT NULL check (weight > 0),
location uuid REFERENCES locations(location_id) NOT NULL
);

create table movies (
movie_id uuid PRIMARY KEY DEFAULT gen_random_uuid(),
movie_name varchar(100) NOT NULL,
movie_coords uuid REFERENCES coords(coords_id) NOT NULL,
date_of_creation timestamp NOT NULL default current_timestamp,
oscars integer NOT NULL check (oscars > 0),
length integer NOT NULL,
movie_genre genre NOT NULL,
movie_rating rating NOT NULL,
director uuid REFERENCES directors(director_id) NOT NULL,
movie_key text NOT NULL
);