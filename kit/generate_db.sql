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

CREATE TABLE cities (
    name varchar(255) PRIMARY KEY NOT NULL,
    lat varchar(255) NOT NULL,
    lon varchar(255) NOT NULL
);

CREATE TABLE pollutions (
    id bigint PRIMARY KEY NOT NULL,
    city varchar(255) NOT NULL,
    date date NOT NULL,
    co varchar(255) NOT NULL,
    so2 varchar(255) NOT NULL,
    o3 varchar(255) NOT NULL
);