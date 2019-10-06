-- *for Windows
-- \! chcp 1251

-- *for Linux
-- sudo -i -u postgres
-- psql

-- *new password for user postgres
-- ALTER USER postgres PASSWORD 'admin';

-- DROP SCHEMA IF EXISTS as_terra_led CASCADE;
-- CREATE SCHEMA as_terra_led;
--
-- SET search_path TO as_terra_led;

DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id         bigserial,
    username   varchar(50) NOT NULL,
    password   varchar(80) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    phone      VARCHAR(15) NOT NULL,
    PRIMARY KEY (id)
);
-- SELECT * FROM users;

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id   serial,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);
-- SELECT * FROM roles;

DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles
(
    user_id INT NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id)
        REFERENCES users (id),
    FOREIGN KEY (role_id)
        REFERENCES roles (id)
);
-- SELECT * FROM users_roles;

INSERT INTO roles (name)
VALUES ('ROLE_USER'),
       ('ROLE_MANAGER'),
       ('ROLE_ADMIN');

INSERT INTO users (username, password, first_name, last_name, email, phone)
VALUES ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'Admin', 'Admin', 'admin@gmail.com',
        '+79881111111');

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2),
       (1, 3);