ALTER TABLE recipes ADD COLUMN IF NOT EXISTS calories_per_serving INT;

ALTER TABLE recipes ADD COLUMN IF NOT EXISTS description TEXT;

ALTER TABLE recipes ADD COLUMN IF NOT EXISTS notes TEXT;

ALTER TABLE recipes ADD COLUMN IF NOT EXISTS servings INT;