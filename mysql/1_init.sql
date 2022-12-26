set names utf8;

create table if not exists role
(
    role_id serial primary key,
    role_name varchar(256) not null
);

create table if not exists person
(
    person_id serial primary key,
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
    article_title text not null,
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
);

INSERT INTO role (role_name) VALUES ('USER'), ('AUTHOR'), ('ADMIN');

INSERT INTO person (username, password, role_id, person_name, person_surname)
VALUES
    ('admin', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 3, 'Даниил', 'Левицкий'),
    ('a1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 2, 'Дмитрий', 'Лебедев'),
    ('a2', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Анастасия', 'Овчаренко'),
    ('a3', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Александра', 'Нешкумай'),
    ('g1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Василиса', 'Мурунова');

INSERT INTO tag (tag_name)
VALUES
    ('Экономика'),
    ('Политика'),
    ('Общество'),
    ('Наука');

INSERT INTO article (tag_id, author_id, article_title, article_content, article_date, picture_link)
VALUES
    (1, 4, '"Это не фотошоп" - Свидетели гор Краснодарского края', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo dicta fuga, officia excepturi animi porro, consequuntur ab, voluptate nisi repellendus sit explicabo vitae rem! Asperiores perferendis tempora sit explicabo laboriosam. Lorem ipsum dolor, sit amet consectetur adipisicing elit. Maiores repellat voluptatem dignissimos asperiores officiis iste saepe, at dolor voluptates, repudiandae necessitatibus. Odio quis voluptas hic id amet veritatis laudantium a. Lorem ipsum, dolor sit amet consectetur adipisicing elit. Pariatur nam, recusandae qui, adipisci laborum vitae odio inventore nostrum porro, dolorum culpa itaque? Voluptatibus nemo cumque, officia nobis magnam doloremque dolore? Lorem ipsum dolor sit amet consectetur adipisicing elit. Veniam error sed soluta, neque doloribus, blanditiis omnis pariatur unde tempora excepturi voluptates perspiciatis debitis possimus adipisci corrupti, labore accusantium rerum fugit? Lorem ipsum dolor sit, amet consectetur adipisicing elit. Illum aspernatur, ex quos obcaecati blanditiis maiores inventore eveniet officia ipsa reiciendis eaque quis consectetur necessitatibus repellendus voluptatibus nulla odit cupiditate tenetur? Lorem ipsum dolor, sit amet consectetur adipisicing elit. Provident optio placeat consequatur. Expedita facere temporibus perferendis odit veritatis quod assumenda itaque minima dolor, nihil voluptates, saepe omnis eligendi debitis repellat? Lorem ipsum dolor sit amet consectetur adipisicing elit. Aspernatur, magnam blanditiis. Incidunt, tempore laudantium modi saepe, quisquam quidem facere molestias, cupiditate non ab pariatur? Tempora, reprehenderit. Cumque quibusdam nam facilis!', DATE(NOW()), 'mountain.jpg'),
    (3, 2, 'Панда в Московском зоопарке вкусно покушала и легла спать', '...2', DATE(NOW() - interval 2 day), 'panda.png'),
    (2, 3, 'Очередной парк снесут и построят в Москве', '...3', DATE(NOW() - interval 1 day), 'park_renovation.png'),
    (4, 1, 'В России разработают технологию автоматического анализа состояния кожи', '...4', DATE(NOW() - interval 1 day), 'microscope.jpg'),
    (2, 2, 'Церковь в районе Восточное Измайлово откроют в конце весны', '...5', DATE(NOW() - interval 1 day), 'church.png'),
    (1, 1, 'Запущена новая нефтедобывающая вышка в Сибири', '...6', DATE(NOW()), 'worker.jpg'),
    (4, 3, 'Российские ученые обнаружили новое семейство магнитов', '...7', DATE(NOW()), 'scientist.jpg');

INSERT INTO comment(person_id, article_id, comment_content, comment_date)
VALUES
    (2, 1, 'Чудесная статья', DATE(NOW())),
    (3, 1, 'Очень красиво, мне нравится', DATE(NOW() - interval 1 day)),
    (4, 1, 'Я люблю горы', DATE(NOW()));