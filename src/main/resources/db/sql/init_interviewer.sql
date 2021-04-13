-- liquibase formatted sql

-- changeset 20210406.[BE-20]-addition_to_interviewer

INSERT INTO interviewer(name,surname,email,phone,type,skype)
VALUES ('Viktor','Petrov','vpetr@gmail.com','80551458526','HR','Vik25'),
       ('Angelina','Kvok','kvoka@mail.ru','80566357485','TECH','Angel_teh'),
       ('Dmitry','Korogoda','d.kor@yandex.by','80554558748','TECH','Kor85'),
       ('Vlad','Vorobei','bird80@gmail.com','80569658574','TECH','VladBird'),
       ('Artem','Dyimin','diman95@mail.ru','80563335674','TECH','Artemdan'),
       ('Alexander','Korgunov','a.korgik@gmail.com','80567786341','HR','KorgikSuper'),
       ('Sergei','Plotnikov','serg.plot@mail.ru','80562228512','TECH','Serega90'),
       ('Marina','Orlova','mary.orlova@yandex.by','80543658974','TECH','Masha95'),
       ('Pavel','Kirilyuk','p.kirill@gmail.com','80567899254','TECH','kiruha35'),
       ('Petr','Klimov','p.klim85@gmail.com','80557416677','TECH','CosmoKlim')