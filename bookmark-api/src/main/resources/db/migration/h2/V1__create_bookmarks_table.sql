create sequence id_seq start with 1 increment by 50;
create table bookmarks (
    id bigint default nextval('id_seq') not null,
    created_at timestamp not null,
    title varchar(255) not null,
    url varchar(255) not null,
    primary key (id)
);