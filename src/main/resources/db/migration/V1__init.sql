BEGIN;
create table users (
    id                      bigserial primary key,
    username                varchar(30) not null unique,
    password                varchar(80) not null,
    email                   varchar(80) unique,
    telephone               varchar(80) unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

create table roles (
    id                      bigserial primary key,
    name                    varchar(50) not null unique,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_roles (
          user_id                 bigint not null references users (id),
          role_id                 bigint not null references roles (id),
          primary key (user_id, role_id)
);

create table addresses (
    id                         bigserial primary key,
    address                    varchar(50) not null ,
    created_at              timestamp default current_timestamp,
    updated_at              timestamp default current_timestamp
);

CREATE TABLE users_addresses (
          user_id                    bigint not null references users (id),
          address_id                 bigint not null references addresses (id),
          primary key (user_id, address_id)
);

insert into roles (name)
values
('ROLE_USER'),
('ROLE_ADMIN');

insert into users (username, password, email)
values
('user', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'bob_johnson@gmail.com'),
('admin', '$2y$12$4g1SOm4vGFSF/CbT84nOzOyygKSuTtRshecj7HYOCC1xUPjhkVPWG', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);

CREATE table categories (
    id                    bigserial primary key,
    title                 varchar(255),
    created_at            timestamp default current_timestamp,
    updated_at            timestamp default current_timestamp
 );

insert into categories (title) values ('Food');

CREATE table products (
    id                  bigserial primary key,
    title               varchar(255),
    price               numeric(8,2),
    category_id         bigint references categories(id),
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);


INSERT INTO products(title, price, category_id) VALUES
('Tea', 300, 1),
('Coffee', 400, 1),
('Cheese', 340, 1),
('Bread', 34.40, 1),
('Milk1', 56, 1),
('Tea1', 300, 1),
('Coffee1', 400, 1),
('Cheese1', 340, 1),
('Bread1', 34.20, 1),
('Milk2', 56.80, 1),
('Tea2', 300, 1),
('Coffee2', 400, 1),
('Cheese2', 340, 1),
('Bread2', 38.90, 1),
('Milk3', 56, 1),
('Tea3', 300, 1),
('Coffee3', 400, 1),
('Cheese3', 340, 1),
('Bread3', 34, 1),
('Milk4', 56.70, 1),
('Tea4', 300, 1),
('Coffee4', 400, 1),
('Cheese4', 340, 1),
('Bread4', 34, 1),
('Milk5', 56, 1);

create table orders (
    id                              bigserial primary key,
    user_id                         bigint references users (id),
    price                           numeric (8, 2),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp
);

CREATE table order_items (
    id                              bigserial primary key,
    order_id                        bigint references orders (id),
    product_id                      bigint references products(id),
    quantity                        int,
    price_per_product               numeric(8,2),
    price                           numeric(8,2),
    created_at                      timestamp default current_timestamp,
    updated_at                      timestamp default current_timestamp
);

COMMIT;