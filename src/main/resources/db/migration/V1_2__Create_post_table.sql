CREATE TABLE if NOT EXISTS post
(
    post_id          SERIAL
    PRIMARY KEY,
    content     VARCHAR(255) NOT NULL,
    created     DATE         NOT NULL,
    updated     DATE,
    post_status VARCHAR(255) NOT NULL
    );