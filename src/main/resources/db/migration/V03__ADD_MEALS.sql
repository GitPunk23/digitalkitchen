CREATE TABLE meals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    date VARCHAR(10) NOT NULL,
    type VARCHAR(20) NOT NULL,
    mealPlanId BIGINT ,
    notes VARCHAR(255)
);

CREATE TABLE meal_recipes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    recipe_id int NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (recipe_id) REFERENCES recipes(id)
);

CREATE TABLE meal_plans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    startDate VARCHAR(10) NOT NULL,
    endDate VARCHAR(10) NOT NULL
);
