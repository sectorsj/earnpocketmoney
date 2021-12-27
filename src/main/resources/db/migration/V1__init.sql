

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
(99),
(101);

drop table  if exists people_groups;
create table people_groups (
                id                          bigserial primary key,
                name                        varchar(30) not null unique
);
insert into people_groups (name) values
('Abramov'),
('Yakovlev');

drop table if exists statuses;
create table statuses (
                id                          bigserial primary key,
                title                       varchar(30) not null unique
);

insert into statuses (title) values
('do_not_accepted'),
('accepted'),
('executed'),
('expired');

drop table  if exists users;
create table users (
                id                          bigserial primary key,
                login                       varchar(30) not null unique,
                password                    varchar(80) not null,
                name                        varchar(80) ,
                id_role                     integer not null REFERENCES roles(id),
                id_wallet                   integer  REFERENCES wallets(id),
                id_people_groups            integer  REFERENCES people_groups(id)
);

insert into users (login, password,id_wallet, id_role, id_people_groups) values
--*пароль у всех один - '100'*--
('parent1', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 1, 1, 1),
('children1', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 2, 2, 1),
('parent2', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 1, 1, 2),
('children2', '$2a$10$VH3WAg6iuGwMvBAFM1CNUOqJiw8MYT5oQF3rZqsf.gAA441m91sgy', 2, 2, 2);


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
                task_text                   varchar(80),
                created_at                  timestamp default current_timestamp,
                updated_at                  timestamp,
                id_user_creating_task       integer not null REFERENCES users(id),
                id_user_executing_task      integer REFERENCES users(id),
                id_status                   integer REFERENCES statuses(id),
                wages                       bigint not null
);

insert into tasks (title, task_text , id_user_creating_task , id_user_executing_task, id_status , wages)
values
('Тестовая задача 1', 'First task description! attention! attention!',1, 2, 1, 15),
('Brush teeth', 'Second task description! attention!', 1, 3, 2, 44),
('Trow out the trash', 'Third task description! attention! attention!', 2, 3, 3, 21);


insert into bonuses (title, id_user_creating_bonus , price) values
('bicycle', 1, 15),
('gun', 2, 99);

commit;