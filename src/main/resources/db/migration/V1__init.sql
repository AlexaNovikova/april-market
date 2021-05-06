BEGIN;

CREATE table categories (id bigserial primary key, title varchar(255));
insert into categories (title) values ('Food');

CREATE table products (id bigserial primary key, title varchar(255), price int, category_id bigint references
categories(id));
INSERT INTO products(title, price, category_id) VALUES
('Tea', 300, 1),
('Coffee', 400, 1),
('Cheese', 340, 1),
('Bread', 34, 1),
('Milk1', 56, 1),
('Tea1', 300, 1),
('Coffee1', 400, 1),
('Cheese1', 340, 1),
('Bread1', 34, 1),
('Milk2', 56, 1),
('Tea2', 300, 1),
('Coffee2', 400, 1),
('Cheese2', 340, 1),
('Bread2', 34, 1),
('Milk3', 56, 1),
('Tea3', 300, 1),
('Coffee3', 400, 1),
('Cheese3', 340, 1),
('Bread3', 34, 1),
('Milk4', 56, 1),
('Tea4', 300, 1),
('Coffee4', 400, 1),
('Cheese4', 340, 1),
('Bread4', 34, 1),
('Milk5', 56, 1);
COMMIT;