CREATE TABLE IF NOT EXISTS passenger (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ticket (
    id BIGSERIAL PRIMARY KEY,
    ticket_number VARCHAR(100) NOT NULL UNIQUE,
    price NUMERIC NOT NULL,
    plane_number VARCHAR(100) NOT NULL,
    seat NUMERIC NOT NULL,
    category VARCHAR(20) NOT NULL,
    passenger_id BIGINT NOT NULL,
    CONSTRAINT fk_passenger FOREIGN KEY(passenger_id) REFERENCES passenger(id)
);
