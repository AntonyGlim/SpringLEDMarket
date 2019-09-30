DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial, title varchar(255), cost integer, PRIMARY KEY (id));
-- \d products;
INSERT INTO products (title, cost) VALUES
('milk', 32),
('bread', 18),
('cheese', 99),
('tomato', 47),
('lime', 32),
('butter', 87),
('apple', 98),
('asparagus', 18),
('eggs', 60),
('banana', 44),
('beef', 55),
('beet', 78),
('berry', 200),
('biscuits', 120),
('bream', 150),
('cabbage', 60),
('cake', 35),
('carrot', 78),
('cherry', 250),
('sold', 12);
-- SELECT * FROM products;

DROP TABLE IF EXISTS orders;
CREATE TABLE orders
(
    id      bigserial PRIMARY KEY,
    user_id bigint,
    price   numeric(8, 2),
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