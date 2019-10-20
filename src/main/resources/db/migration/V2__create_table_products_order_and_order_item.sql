DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial, title varchar(255), price numeric(8, 2), PRIMARY KEY (id));
-- \d products;
INSERT INTO products (title, price) VALUES
('АС-Аврора-16 (mono)', 11250),
('АС-Аврора-32 (mono)', 12250),
('АС-Аврора-50 (mono)', 13250),
('АС-Аврора-65 (mono)', 14250),
('АС-Аврора-16 (RGB)', 15500),
('АС-Аврора-32 (RGB)', 16500),
('АС-Аврора-50 (RGB)', 17500),
('АС-Аврора-65 (RGB)', 18500),
('АС-Спектрум-105', 19500),
('АС-Спектрум-210', 20500);
-- SELECT * FROM products;

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id          bigserial PRIMARY KEY,
    user_id     bigint,
    price       numeric(8, 2),
    phone      varchar(15),
    address    varchar(255),
    status      varchar(255),
    created_at  timestamp,
    updated_at  timestamp,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

DROP TABLE IF EXISTS order_items;
CREATE TABLE order_items
(
    id          bigserial PRIMARY KEY,
    order_id    bigint,
    product_id  bigint,
    quantity    int,
    item_price  numeric(8, 2),
    total_price numeric(8, 2),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);

DROP TABLE IF EXISTS products_images;
CREATE TABLE products_images
(
    id         bigserial PRIMARY KEY,
    product_id bigint,
    path       varchar(255),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
INSERT INTO products_images (product_id, path)
VALUES (1, 'title.png'),
       (2, 'title.png'),
       (3, 'title.png'),
       (4, 'title.png'),
       (5, 'title.png'),
       (6, 'title.png'),
       (7, 'title.png'),
       (8, 'title.png'),
       (9, 'title.png'),
       (10, 'title.png');