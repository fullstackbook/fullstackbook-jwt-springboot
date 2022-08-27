create table users (
    id serial primary key,
    username text not null,
    email text not null,
    password text
);
