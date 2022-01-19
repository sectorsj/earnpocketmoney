

drop table if exists roles;
create table roles (
                id                          bigserial primary key,
                role                        varchar(30) not null unique
);


insert into roles (role) values
('ROLE_PARENT'),
('ROLE_CHILDREN');

drop table  if exists wallets;
create table wallets (
                id                          bigserial primary key,
                value                       bigint
);


insert into wallets (value) values
(100),
(5),
(101),
(17),
(1);

drop table  if exists people_groups;
create table people_groups (
                id                          bigserial primary key,
                name                        varchar(30) not null unique
);
insert into people_groups (name) values
('Ivanov'),
('Petrov');

drop table if exists statuses;
create table statuses (
                id                          bigserial primary key,
                title                       varchar(30) not null unique
);

insert into statuses (title) values
('не принято'),
('принято'),
('выполнено'),
('подтверждено выполнение'),
('просрочено');

drop table  if exists users;
create table users (
                id                          bigserial primary key,
                login                       varchar(30) not null unique,
                password                    varchar(80) not null,
                email                       varchar (80) unique,
                username                    varchar(80),
                id_role                     integer not null REFERENCES roles(id),
                id_wallet                   integer  REFERENCES wallets(id),
                id_people_groups            integer  REFERENCES people_groups(id)
);

insert into users (login, password,id_wallet, id_role, id_people_groups) values
--*пароль у всех один - '100'*--
('Pavel', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 1, 1, 1),
('Masha', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 2, 2, 1),
('Petr', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 3, 1, 2),
('Dasha', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 4, 2, 2),
('Sasha', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 5, 2, 1);


drop table  if exists bonuses;
create table bonuses (
                id                          bigserial primary key,
                title                       varchar(80) not null,
                bonus_text                  varchar(80),
                created_at                  timestamp default current_timestamp,
                updated_at                  timestamp,
                id_user_creating_bonus      integer not null REFERENCES users(id),
                id_user_getting_bonus       integer REFERENCES users(id),
                price                       bigint not null,
                getting_status              boolean
);

drop table  if exists tasks;
create table tasks (
                id                          bigserial primary key,
                title                       varchar(30) not null,
                task_text                   varchar(300),
                created_at                  timestamp default current_timestamp,
                updated_at                  timestamp,
                id_user_creating_task       integer not null REFERENCES users(id),
                id_user_executing_task      integer REFERENCES users(id),
                id_status                   integer REFERENCES statuses(id),
                wages                       bigint not null
);


insert into tasks (title, task_text , id_user_creating_task, id_user_executing_task, id_status , wages)
values
('Вынести мусор', 'Вынести мусор с кухни до вечера', 1, 2, 1, 15),
('Сделать уроки', 'Сделать ДЗ по математике, геометрии и дипломную работу в ГикБрэйнс', 1, 2, 1, 24),
('Выгулять собаку', 'Выгулять собаку на площадке за домом', 1, 2, 2, 1),
('Сходить в магазин', 'Купить: хлеб, молоко, масло, яйца и сыр, шоколадку себе на сдачу', 1, 5, 2, 1),
('Провести уборку', 'Убрать игрушки в детской, пропылесосить и помыть полы', 3, 4, 3, 14),
('Постирать кроссовки', 'Замочить в тазу свои кроссовки с порошком на 20 минут, после чего постирать, прополоскать и высушить', 3, 4, 3, 10);


insert into bonuses (title, id_user_creating_bonus , price) values
('Велосипед', 1, 15),
('Ноутбук', 1, 19),
('Коньки', 1, 35),
('Пистолет (пока что водяной)', 2, 99);

commit;