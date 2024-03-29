-- Remove the NOT NULL constraint from the recipe_id column in the steps table
ALTER TABLE steps MODIFY COLUMN recipe_id INT;

-- Remove the NOT NULL constraint from the recipe_id column in the recipe_tags table
ALTER TABLE recipe_tags MODIFY COLUMN recipe_id INT;

-- Remove the NOT NULL constraint from the recipe_id column in the recipe_ingredients table
ALTER TABLE recipe_ingredients MODIFY COLUMN recipe_id INT;
