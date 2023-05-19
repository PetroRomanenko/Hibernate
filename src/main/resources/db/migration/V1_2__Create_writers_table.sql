CREATE TABLE IF NOT EXISTS writers(
    id        SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL
);
