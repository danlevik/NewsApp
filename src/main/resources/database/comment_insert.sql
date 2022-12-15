INSERT INTO comment(person_id, article_id, comment_content, comment_date)
VALUES
(2, 1, 'Чудесная статья', DATE(NOW())),
(3, 1, 'Очень красиво, мне нравится', DATE(NOW() - interval 1 day)),
(4, 1, 'Я люблю горы', DATE(NOW()));