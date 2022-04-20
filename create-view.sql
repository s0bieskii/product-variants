CREATE VIEW product AS
    SELECT  variants.id AS "id wariantu",
			models.name AS "nazwa modelu",
            types.name AS "typ",
            brands.name AS "producent",
            variants.color AS "kolor",
            sizes.sizes AS "rozmiar",
            variants.price AS "cena"
    FROM    models, types, products, brands, variants, size_categories, sizes
    WHERE   models.type_id = types.id
    AND     products.model_id = models.id
    AND     products.brand_id = brands.id
    AND     variants.model_id = models.id
    AND     variants.size_id = sizes.id
    AND     sizes.size_category_id = size_categories.id;