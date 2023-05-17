CREATE TABLE IF NOT EXISTS writer
(
    writer_id SERIAL
    PRIMARY KEY,
    firstname VARCHAR(255)                                       NOT NULL,
    lastname  VARCHAR(255)                                       NOT NULL
    );
