CREATE ROLE admin WITH
    LOGIN
    SUPERUSER
    CREATEDB
    CREATEROLE
    INHERIT
    REPLICATION
    CONNECTION LIMIT -1
    PASSWORD '1234';

DROP DATABASE db_example;

CREATE DATABASE db_example
    WITH
    OWNER = root
    ENCODING = 'UTF8'
    TABLESPACE = pg_default;

CREATE SCHEMA IF NOT EXISTS sc_example AUTHORIZATION root;

SET search_path TO sc_example;

CREATE TABLE posts (
  id bigint PRIMARY KEY NOT NULL,
  user_id bigint NOT NULL,
  title varchar(255) NOT NULL,
  body varchar(255) NOT NULL
);

insert into posts(id, user_id, title, body) values (1, 1, 'title-1', 'body-1');
insert into posts(id, user_id, title, body) values (2, 1, 'title-2', 'body-2');
insert into posts(id, user_id, title, body) values (3, 2, 'title-3', 'body-3');
insert into posts(id, user_id, title, body) values (4, 3, 'title-4', 'body-4');