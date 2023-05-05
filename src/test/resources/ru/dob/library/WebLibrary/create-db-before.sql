delete from library_db_test.public.book;
delete from library_db_test.public.staff;
delete from library_db_test.public.person;

ALTER TABLE staff
ALTER COLUMN id
        RESTART WITH 1;
ALTER TABLE person
    ALTER COLUMN id
        RESTART WITH 1;
ALTER TABLE book
    ALTER COLUMN id
        RESTART WITH 1;

insert into staff(username,password,full_name,role) values
    ('user1','$2a$10$HQ4LXgMh.woHfEoLPaFeVOQVkC8mDfcFl8wZM1t.DVSME1wANGmJy','Жил Федор Петрович','ROLE_ADMIN');
insert into staff(username,password,full_name,role) values
    ('user2','$2a$10$HQ4LXgMh.woHfEoLPaFeVOQVkC8mDfcFl8wZM1t.DVSME1wANGmJy','Жилинский Иван Сергеевич','ROLE_STAFF');
insert into person(full_name,year_of_birth,number) values
    ('Жилинский Иван Сергеевич','1990',79961034522);
insert into book(name,year,person_id,author,taken_at) values
    ('Горе от ума','1825',1,'Грибоедов Александр Сергеевич',CURRENT_DATE);