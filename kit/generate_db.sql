create table if not exists demodb.cities
(
    city_id bigint       not null
    primary key,
    name    varchar(255) not null,
    lat     varchar(255) not null,
    lon     varchar(255) not null
    );

create table if not exists demodb.pollutions
(
    id   bigint       not null
    primary key,
    city varchar(255) not null,
    date date         not null,
    co   varchar(255) not null,
    so2  varchar(255) not null,
    o3   varchar(255) not null
    );


