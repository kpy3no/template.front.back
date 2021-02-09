--liquibase formatted sql

--changeset kpy3no:dml-example context:dev
insert into CITY(NAME)
values
    ('Novosibirsk'),
    ('Tomsk'),
    ('Ekaterinburg'),
    ('Moscow'),
    ('Omsk'),
    ('Saint Petersburg'),
    ('Novokuznetsk'),
    ('Kemerovo'),
    ('Saratov'),
    ('Sochi'),
    ('Vladivostok'),
    ('Kazan'),
    ('Norislk'),
    ('Volgograd'),
    ('Murmansk'),
    ('Tumen'),
    ('Vladimir'),
    ('Perm'),
    ('Chelyabinsk'),
    ('Habarovsk'),
    ('Magadan');

--rollback delete from CITY;
--rollback select 1;
