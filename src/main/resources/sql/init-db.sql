CREATE TABLE IF NOT EXISTS postgres.homework (
    id BIGSERIAL NOT NULL,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS postgres.lesson (
    id BIGSERIAL NOT NULL,
    homework_id BIGINT NOT NULL,
    name VARCHAT(255) NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
    CONSTRAINT homework_id_fk FOREIGN KEY (homework_id) REFERENCES homework(id)
);
