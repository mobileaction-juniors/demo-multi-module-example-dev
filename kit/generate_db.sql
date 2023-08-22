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

CREATE TABLE users (
  id bigint PRIMARY KEY NOT NULL,
  name varchar(255) NOT NULL,
  username varchar(255) NOT NULL,
  email varchar(255) NOT NULL
);
