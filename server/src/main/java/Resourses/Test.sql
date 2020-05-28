CREATE SEQUENCE uuid_gen;

create type rating as enum ('G', 'PG', 'PG_13');
create type genre as enum ('COMEDY', 'MUSICAL', 'FANTASY');

create table locations
(
    location_id   bigint PRIMARY KEY DEFAULT nextval('uuid_gen'),
    x             integer      NOT NULL,
    y             bigint,
    z             integer      NOT NULL,
    location_name varchar(100) NOT NULL
);

create table coords
(
    coords_id bigint PRIMARY KEY DEFAULT nextval('uuid_gen'),
    x         integer NOT NULL,
    y         real
);

create table directors
(
    director_id   bigint PRIMARY KEY    DEFAULT nextval('uuid_gen'),
    director_name varchar(100) NOT NULL,
    birthday      date         NOT NULL default current_timestamp,
    height        double precision check (height > 0),
    weight        real check (weight > 0),
    location      bigint REFERENCES locations (location_id)
);

create table movies
(
    movie_id         bigint PRIMARY KEY                                 DEFAULT nextval('uuid_gen'),
    movie_name       varchar(100)                              NOT NULL,
    movie_coords     bigint REFERENCES coords (coords_id)      NOT NULL,
    date_of_creation timestamp                                 NOT NULL default current_timestamp,
    oscars           integer                                   NOT NULL check (oscars > 0),
    length           integer,
    movie_genre      genre,
    movie_rating     rating,
    director         bigint REFERENCES directors (director_id) NOT NULL,
    movie_key        text                                      NOT NULL,
    usernames        varchar(100)
);

create table users (
id serial primary key,
username varchar(100) not null,
password varchar(100) not null
);