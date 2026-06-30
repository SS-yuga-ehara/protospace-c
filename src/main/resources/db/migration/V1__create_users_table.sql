CREATE TABLE IF NOT EXISTS users (
    id          SERIAL       NOT NULL,
    name        VARCHAR(255) NOT NULL,
    password    VARCHAR(128) NOT NULL,
    email       VARCHAR(128) NOT NULL UNIQUE,
    profile     VARCHAR(255) NOT NULL,
    affiliation VARCHAR(255) NOT NULL,
    position    VARCHAR(255) NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    PRIMARY KEY (id)
);