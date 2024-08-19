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

CREATE TABLE users
(
    id bigint PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    phone varchar(255),
    website varchar(255)
);
INSERT INTO sc_example.posts (id, user_id, title, body) VALUES
                                                            (1, 1, 'sunt aut facere repellat provident occaecati excepturi optio reprehenderit',
                                                             'quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto'),
                                                            (2, 1, 'qui est esse',
                                                             'est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla');