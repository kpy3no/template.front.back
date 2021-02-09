--liquibase formatted sql

--changeset kpy3no:ddl-example

create table CITY (
    ID bigserial not null
        constraint PERSONAL_PARAM_PK
            primary key,
    NAME VARCHAR(100) NOT NULL
        constraint UQ_PERSONAL_PARAM_CODE
            unique
);

comment on table CITY is 'Cities';
comment on column CITY.ID is 'Identifier';
comment on column CITY.NAME is 'City name';

--rollback drop table CITY;