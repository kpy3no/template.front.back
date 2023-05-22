create table CITY (
                      id bigint auto_increment,
                      NAME VARCHAR(100) NOT NULL
);

insert into CITY(NAME)
values
    ('Novosibirsk'),
    ('Tomsk'),
    ('Ekaterinburg'),
    ('Moscow'),
    ('Omsk');