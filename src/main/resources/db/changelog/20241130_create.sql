-- liquibase formatted sql

-- changeset publisher_table:20241130-10
CREATE TABLE IF NOT EXISTS publisher
(
    id serial4 NOT NULL,
    username varchar(200) NOT NULL,
    password varchar(200) NOT NULL,
    full_name varchar(200) NOT NULL,
    address varchar(200) NOT NULL,
    CONSTRAINT publisher_pk PRIMARY KEY (id)
);

-- changeset garment_Table:20241130-20
CREATE TABLE IF NOT EXISTS garment
(
    id serial4 NOT NULL,
    publisher_username varchar(200) NOT NULL,
    type varchar(50) NOT NULL,
    size varchar(10) NOT NULL,
    description varchar(500) NOT NULL,
    price numeric(10, 2) NOT NULL,
    CONSTRAINT garment_pk PRIMARY KEY (id)
);
