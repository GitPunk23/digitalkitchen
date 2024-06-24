CREATE TABLE IF NOT EXISTS meal_records (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    meal_id BIGINT NOT NULL,
    meal_plan_id BIGINT NOT NULL,
    date VARCHAR(10) NOT NULL,
    type VARCHAR(20) NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (meal_plan_id) REFERENCES meal_plans(id)
);
