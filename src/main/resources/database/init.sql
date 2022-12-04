-- CREATE TABLE IF NOT EXISTS types
-- (
--     id serial NOT NULL PRIMARY KEY ,
--     type_name TEXT NOT NULL
-- );
--
-- CREATE TABLE IF NOT EXISTS products
-- (
--     id SERIAL PRIMARY KEY ,
--     type_id bigint unsigned NOT NULL,
--     product_name TEXT NOT NULL ,
--     price INTEGER ,
--     description TEXT ,
--     cover_link TEXT,
--     FOREIGN KEY (type_id) REFERENCES types (id) ON DELETE CASCADE
--     );
--
-- create table if not exists users
-- (
--     id serial primary key,
--     username text,
--     password text,
--     role text
-- );
--
-- create table if not exists basket
-- (
--     id serial primary key,
--     user_id bigint unsigned,
--     product_id bigint unsigned,
--     product_count integer,
--     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
--     FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
--     );

-- create table if not exists person_role
-- (
--     person_role_id serial primary key,
--     person_role_name varchar(40) not null
--     );

create table if not exists person
(
    person_id serial primary key,
    person_role varchar(30),
--     person_role_id bigint unsigned,
    person_name varchar(30),
    person_surname varchar(30),
    username varchar(30) not null,
    password varchar(256) not null
--     foreign key (person_role_id) references person (person_id) on delete cascade
    );

create table if not exists tag
(
    tag_id serial primary key,
    tag_name varchar(50) not null
    );

create table if not exists article
(
    article_id serial primary key,
    tag_id bigint unsigned not null,
    author_id bigint unsigned not null,
    article_title varchar(100) not null,
    article_content text not null,
    article_date date not null,
    picture_link varchar(256),
    foreign key (author_id) references person (person_id) on delete cascade,
    foreign key (tag_id) references tag (tag_id) on delete cascade
    );

create table if not exists comment
(
    comment_id serial primary key,
    person_id bigint unsigned not null,
    article_id bigint unsigned not null,
    comment_content text not null,
    comment_date date not null
)