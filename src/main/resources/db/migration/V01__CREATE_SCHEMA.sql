CREATE TABLE IF NOT EXISTS authors (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS recipes (
    id BIGINT NOT NULL AUTO_INCREMENT,
    author_id BIGINT NOT NULL,
    category varchar(30) DEFAULT NULL,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY `unique_name_constraint` (`name`),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS ingredients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(50) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tags (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name varchar(20) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS recipe_ingredients (
    id BIGINT NOT NULL AUTO_INCREMENT,
    recipe_id BIGINT,
    ingredient_id BIGINT NOT NULL,
    measurement varchar(20) DEFAULT NULL,
    quantity float NOT NULL,
    notes text,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients(id)
);

CREATE TABLE IF NOT EXISTS recipe_tags (
    id BIGINT NOT NULL AUTO_INCREMENT,
    recipe_id BIGINT,
    tag_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tags(id)
);

CREATE TABLE IF NOT EXISTS steps (
    id BIGINT NOT NULL AUTO_INCREMENT,
    recipe_id BIGINT,
    step_number int NOT NULL,
    description text,
    PRIMARY KEY (id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS meals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    author_id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    notes VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS meal_recipes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    recipe_id BIGINT NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);

CREATE TABLE IF NOT EXISTS meal_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date VARCHAR(10) NOT NULL,
    end_date VARCHAR(10) NOT NULL,
    author_id BIGINT NOT NULL,
    CHECK (end_date > start_date),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

CREATE TABLE IF NOT EXISTS meal_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    meal_plan_id BIGINT NOT NULL,
    date VARCHAR(10) NOT NULL,
    type VARCHAR(20) NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (meal_plan_id) REFERENCES meal_plans(id)
);

INSERT INTO authors (name)
SELECT 'system'
WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'system');

INSERT INTO authors (name)
SELECT 'brett'
WHERE NOT EXISTS (SELECT 1 FROM authors WHERE name = 'brett');




