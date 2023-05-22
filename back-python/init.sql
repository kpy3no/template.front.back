create table CITY (
    ID INTEGER PRIMARY KEY AUTOINCREMENT,
    NAME VARCHAR(100) NOT NULL
);

insert into CITY(NAME)
values
    ('Novosibirsk'),
    ('Tomsk'),
    ('Ekaterinburg'),
    ('Moscow'),
    ('Omsk');
COMMIT;