INSERT INTO genders(id, name)
VALUES (1, 'male'),
       (2, 'female'),
       (3, 'unisex');

INSERT INTO size_categories(id, name)
VALUES (1, 'shoes'),
       (2, 'clothes'),
       (3, 'pants');

INSERT INTO sizes (id, sizes, size_category_id)
VALUES (1, '36', 1),
       (2, '37', 1),
       (3, '38', 1),
       (4, '39', 1),
       (5, '40', 1),
       (6, '41', 1),
       (7, '42', 1),
       (8, '43', 1),
       (9, '44', 1),
       (10, '45', 1),
       (11, '46', 1),
       (12, '47', 1),
       (13, '48', 1);

INSERT INTO sizes (id, sizes, size_category_id)
VALUES (14, 'xs', 2),
       (15, 's', 2),
       (16, 'm', 2),
       (17, 'l', 2),
       (18, 'xl', 2),
       (19, 'xxl', 2),
       (20, 'xxxl', 2);

INSERT INTO sizes (id, sizes, size_category_id)
VALUES (21, '26/27', 3),
       (22, '30/31', 3),
       (23, 'w33', 3),
       (24, 'w40', 3);

INSERT INTO types (id, name)
VALUES (1, 'sport shoes'),
       (2, 'winter shoes'),
       (3, 'trekking shoes'),
       (4, 'daily'),
       (5, 'boxers'),
       (6, 'swimming trunks'),
       (7, 'with hood');

INSERT INTO brands (id, name)
VALUES (1, 'nike'),
       (2, 'puma'),
       (3, 'new balance'),
       (4, 'ballenciaga');

INSERT INTO categories(id, name)
VALUES (1, 'shoes'),
       (2, 'clothes'),
       (3, 'accessories'),
       (4, 'underwear');

INSERT INTO models(id, name, gender_id, type_id)
VALUES (1, 'air force 1', 1, 1),
       (2, 'air jordan 9', 1, 1),
       (3, 'hoodie psg', 2, 6),
       (4, 'tiger pants', 1, 5);

INSERT INTO products(id, archived, brand_id, category_id, model_id)
VALUES (1, 0, 1, 1, 1),
       (2, 0, 1, 1, 2),
       (3, 0, 4, 2, 3),
       (4, 0, 2, 4, 4);

INSERT INTO variants(id, color, price, model_id, size_id)
VALUES (1, 'white', 750.00, 1, 5),
       (2, 'white', 750.00, 1, 6),
       (3, 'white', 750.00, 1, 7),
       (4, 'white', 750.00, 1, 8),
       (5, 'black', 700.00, 1, 5),
       (6, 'black', 700.00, 1, 6),
       (7, 'black', 700.00, 1, 7),
       (8, 'black/white', 735.00, 1, 5),
       (9, 'black/white', 735.00, 1, 6),
       (10, 'black/white', 735.00, 1, 9),
       (11, 'black/white', 735.00, 1, 10),
       (12, 'black', 935.00, 2, 3),
       (13, 'black', 935.00, 2, 4),
       (14, 'black', 935.00, 2, 5),
       (15, 'black', 935.00, 2, 6),
       (16, 'black', 935.00, 2, 7),
       (17, 'red', 999.00, 2, 5),
       (18, 'red', 999.00, 2, 6),
       (19, 'red', 999.00, 2, 7),
       (20, 'black', 240.00, 3, 16),
       (21, 'black', 240.00, 3, 17),
       (22, 'black', 240.00, 3, 18),
       (23, 'multicolor', 135.00, 4, 18),
       (24, 'multicolor', 135.00, 4, 19);