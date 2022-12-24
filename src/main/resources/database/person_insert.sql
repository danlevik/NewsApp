# INSERT INTO person (username, password, person_role, person_name, person_surname)
# VALUES
#        ('admin', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 'ADMIN', 'Даниил', 'Левицкий'),
#        ('a1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 'USER', 'Дмитрий', 'Лебедев'),
#        ('a2', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 'USER', 'Анастасия', 'Овчаренко'),
#        ('a3', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 'USER', 'Александра', 'Нешкумай'),
#        ('g1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 'USER', 'Василиса', 'Мурунова');

INSERT INTO person (username, password, role_id, person_name, person_surname)
VALUES
    ('admin', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 3, 'Даниил', 'Левицкий'),
    ('a1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 2, 'Дмитрий', 'Лебедев'),
    ('a2', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Анастасия', 'Овчаренко'),
    ('a3', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Александра', 'Нешкумай'),
    ('g1', '$2a$10$cFC7x5dLtTDljvseOQa7LOYL4S0u0vaM32AHWQB3o09RSgc0xFf7a', 1, 'Василиса', 'Мурунова');