create table users (
    id              bigserial primary key,
    username        varchar(30) not null unique,
    password        varchar(80) not null,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table roles (
    id              bigserial primary key,
    name            varchar(50) not null unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

CREATE TABLE users_roles (
    user_id         bigint not null references users (id),
    role_id         bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values
('ROLE_CHILD'),
('ROLE_PARENT');

insert into users (username, password, email)
values
('child', '111'),
('parent', '000');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2);