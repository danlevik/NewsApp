
create table if not exists role
(
  role_id serial primary key,
  role_name varchar(256) not null
);

create table if not exists person
(
    person_id serial primary key,
#     person_role varchar(30) not null,
    role_id bigint unsigned not null,
    person_name varchar(30),
    person_surname varchar(30),
    username varchar(30) not null,
    password varchar(256) not null,
    foreign key (role_id) references role (role_id) on delete cascade
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
    comment_date date not null,
    foreign key (person_id) references person (person_id) on delete cascade,
    foreign key (article_id) references article (article_id) on delete cascade
)