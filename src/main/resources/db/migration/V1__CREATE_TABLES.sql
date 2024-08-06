CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE roles (
                        id SERIAL PRIMARY KEY,
                        role VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE users (
                        id UUID PRIMARY KEY,
                        login VARCHAR(32) NOT NULL UNIQUE,
                        password VARCHAR(64) NOT NULL,
                        role_id INTEGER NOT NULL,
                        FOREIGN KEY (role_id) REFERENCES roles(id)
);
INSERT INTO roles (role) VALUES ('ADMIN_ROLE');
INSERT INTO roles (role) VALUES ('USER_ROLE');
