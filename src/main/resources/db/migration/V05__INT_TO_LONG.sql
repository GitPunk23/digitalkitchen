ALTER TABLE recipes MODIFY COLUMN id BIGINT;
ALTER TABLE meal_recipes MODIFY COLUMN recipe_id BIGINT;
ALTER TABLE recipe_ingredients MODIFY COLUMN recipe_id BIGINT;
ALTER TABLE recipe_ingredients MODIFY COLUMN ingredient_id BIGINT;
ALTER TABLE recipe_tags MODIFY COLUMN recipe_id BIGINT;
ALTER TABLE recipe_tags MODIFY COLUMN tag_id BIGINT;
ALTER TABLE steps MODIFY COLUMN recipe_id BIGINT;

