CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

INSERT INTO authors (name)
SELECT 'system'
WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'system');

INSERT INTO authors (name)
SELECT 'brett'
WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'brett');

ALTER TABLE meal_plans
    ADD COLUMN author_id BIGINT NOT NULL DEFAULT 2,
    ADD CONSTRAINT fk_meal_plans_author FOREIGN KEY (author_id) REFERENCES authors(id);;

ALTER TABLE meals
    ADD COLUMN author_id BIGINT NOT NULL DEFAULT 2,
    ADD CONSTRAINT fk_meals_author FOREIGN KEY (author_id) REFERENCES authors(id);

ALTER TABLE recipes
    DROP COLUMN author,
    ADD CONSTRAINT fk_recipes_author FOREIGN KEY (author_id) REFERENCES authors(id);

ALTER TABLE recipe_ingredients
    ADD COLUMN notes text;
