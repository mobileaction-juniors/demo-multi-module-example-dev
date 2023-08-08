CREATE TABLE posts
(
    id      bigint PRIMARY KEY NOT NULL,
    user_id bigint             NOT NULL,
    title   varchar(255)       NOT NULL,
    body    varchar(255)       NOT NULL
);

