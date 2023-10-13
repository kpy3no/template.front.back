create table CITY (
                      id bigint auto_increment,
                      NAME VARCHAR(100) NOT NULL,
                      STATUS VARCHAR(100) NOT NULL,
                      ORGANIZATION VARCHAR(100) NOT NULL

);

insert into CITY(NAME, STATUS, ORGANIZATION)
values
    ('Novosibirsk', 'NEW', 'org1'),
    ('Tomsk', 'NEW', 'org1'),
    ('Ekaterinburg', 'NEW', 'org1'),
    ('Moscow', 'NEW', 'org1'),
    ('Omsk', 'SEND', 'org1');