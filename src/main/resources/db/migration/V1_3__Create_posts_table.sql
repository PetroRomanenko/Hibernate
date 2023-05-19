CREATE TABLE if NOT EXISTS posts
(
    id          SERIAL PRIMARY KEY,
    content     VARCHAR(255) NOT NULL,
    created     DATE         NOT NULL,
    updated     DATE,
    status          VARCHAR(255) NOT NULL,
    writer_id INTEGER CONSTRAINT fk_post_writer_id REFERENCES writers (id)
);
