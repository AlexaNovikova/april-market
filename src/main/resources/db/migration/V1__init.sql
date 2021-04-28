BEGIN;
CREATE table products (id bigserial primary key, title varchar(255), price int);
INSERT INTO products(title, price) VALUES
('Tea', 300),
('Coffee', 400),
('Cheese', 340),
('Bread', 34),
('Milk', 56);
COMMIT;