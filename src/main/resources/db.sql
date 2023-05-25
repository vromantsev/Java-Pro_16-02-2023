create table postgres.product (
    id bigserial primary key,
    name varchar(255) not null,
    price numeric not null,
    created_at timestamp default now()
);

create table postgres.order (
    id bigserial primary key,
    user_id bigint not null,
    created_at timestamp default now(),
    constraint user_id_fk foreign key (user_id) references postgres.user(id)
);

create table postgres.user (
    id bigserial primary key,
    full_name varchar(255) not null,
    country varchar(255)
);

-- where
select id, name, price
    from postgres.product
    where
        price < 200 or name like '%wine';

-- limit


-- order by
select id, full_name, country
    from postgres.user
    order by country desc; -- asc - ascending (від меншого до більшого), desc - descending (від більшого до меншого)

-- count()
select count(*) as all_orders from postgres.order;

-- sum()
select sum(price) as total_products_sum from postgres.product;

-- max()/min()
-- subquery
select id, name, price
    from postgres.product
    where price = (select max(price) from postgres.product);

-- having
select id, name, price
    from postgres.product
    group by id, name, price
    having max(price) > 200
    order by price desc limit 1;

-- inner join
select u.id, u.full_name, u.country
    from postgres.user u -- left
    inner join postgres.order o -- right
        on u.id = o.user_id;

-- left join
select *
    from postgres.user u
    left join postgres.order o
        on o.user_id = u.id;


-- right join
select *
    from postgres.order o
    right join postgres.user u
        on o.user_id = u.id;

-- full join
select *
    from postgres.order o
    full join postgres.user u
        on o.user_id = u.id;

select *
    from postgres.user u
    full join postgres.order o
        on o.user_id = u.id;

-- DATA
insert into postgres.product (name, price) values ('red wine', 100),
                                                  ('white wine', 120),
                                                  ('sugar', 30),
                                                  ('cheese', 250),
                                                  ('water', 15),
                                                  ('fish', 200),
                                                  ('shrimps', 600),
                                                  ('sweets', 150);


insert into postgres.user (full_name, country) values ('Ilya Ivanov', 'USA'),
                                                      ('Serhii Borovyk', 'USA'),
                                                      ('Oleksandr Gromovyi', 'UA'),
                                                      ('Stanislav Isakov', 'UA'),
                                                      ('Roman Grets', 'UA'),
                                                      ('Dmytro Hrailyvyi', 'PL'),
                                                      ('Alina Kotova', 'PL');

insert into postgres.order (user_id) values (2), (4), (6);


1 order can have at least 1 row from user table -> 1 order => many users
1 user can have at least 1 row from order table -> 1 user => many orders

order | user
\    /
  user_orders => order_id, user_id
       order_id = 1, user_id (2,3,4,5)
       user_id = 1, order_id (2,3,4,5)