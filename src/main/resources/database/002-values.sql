--liquibase formatted sql
--changeset intersport:10
INSERT INTO GENDERS(ID, NAME)
VALUES (1, 'male'),
       (2, 'female'),
       (3, 'unisex');

--changeset intersport:11
INSERT INTO SIZE_CATEGORIES(ID, NAME)
VALUES (1, 'shoes'),
       (2, 'clothes'),
       (3, 'pants');

--changeset intersport:12
INSERT INTO SIZES (ID, SIZES, SIZE_CATEGORY_ID)
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

--changeset intersport:13
INSERT INTO SIZES (ID, SIZES, SIZE_CATEGORY_ID)
VALUES (14, 'XS', 2),
       (15, 'S', 2),
       (16, 'M', 2),
       (17, 'L', 2),
       (18, 'XL', 2),
       (19, 'XXL', 2),
       (20, 'XXXL', 2);

--changeset intersport:14
INSERT INTO SIZES (ID, SIZES, SIZE_CATEGORY_ID)
VALUES (21, '26/27', 3),
       (22, '30/31', 3),
       (23, 'W33', 3),
       (24, 'W40', 3);

--changeset intersport:15
INSERT INTO TYPES (ID, NAME)
VALUES (1, 'sport shoes'),
       (2, 'winter shoes'),
       (3, 'trekking shoes'),
       (4, 'daily'),
       (5, 'boxers'),
       (6, 'swimming trunks'),
       (7, 'with hood');

--changeset intersport:16
INSERT INTO BRANDS (ID, NAME)
VALUES (1, 'Nike'),
       (2, 'Puma'),
       (3, 'New Balance'),
       (4, 'Ballenciaga');

--changeset intersport:17
INSERT INTO CATEGORIES(ID, NAME)
VALUES (1, 'shoes'),
       (2, 'clothes'),
       (3, 'accessories'),
       (4, 'underwear');
--changeset intersport:18
INSERT INTO MODELS(ID, NAME, GENDER_ID, TYPE_ID)
VALUES (1, 'Air Force 1', 1, 1),
       (2, 'Air Jordan 9', 1, 1),
       (3, 'Hoodie PSG', 2, 6),
       (4, 'Tiger pants', 1, 5);
--changeset intersport:19
INSERT INTO PRODUCTS(ID, ARCHIVED, BRAND_ID, CATEGORY_ID, MODEL_ID)
VALUES (1, 0, 1, 1, 1),
       (2, 0, 1, 1, 2),
       (3, 0, 4, 2, 3),
       (4, 0, 2, 4, 4);

--changeset intersport:20
INSERT INTO VARIANTS(ID, COLOR, PRICE, MODEL_ID, SIZE_ID)
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
