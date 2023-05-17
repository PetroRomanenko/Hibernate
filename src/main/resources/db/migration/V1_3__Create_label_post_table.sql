CREATE TABLE IF NOT EXISTS label_post
(
    post_id  INTEGER NOT NULL
    CONSTRAINT fk_post
    REFERENCES post (post_id)
    ON DELETE CASCADE,
    label_id INTEGER NOT NULL
    CONSTRAINT fk_label
    REFERENCES label (label_id)
    ON UPDATE CASCADE ON DELETE CASCADE
    );