create table users (
    id              bigserial primary key,
    username        varchar(30) not null unique,
    password        varchar(80) not null,
    email           varchar(80) unique,
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