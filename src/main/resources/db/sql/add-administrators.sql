-- liquibase formatted sql

-- changeset 20210414.[BE-24]-addition_to_administrator

INSERT INTO administrator(login, password, name,surname,email,phone,role,skype)
VALUES ('pearson','1234','Matthew','McConaughey','atthew@gmail.com','8053438526','ADMIN','atthew_west'),
       ('madonna','1111','Guy','Ritchie','madonna@gmail.com','4444446','SUPERADMIN','madonna_best'),
       ('raymond','333333','Charlie','Hunnam','hunnam@gmail.com','123345566','ADMIN','charlie_dog'),
       ('coach','wee223','Colin','Farrell','coach@gmail.com','7655446','ADMIN','bee'),
       ('fletcher','222333','Hugh','Grant','hughgrant@gmail.com','33334556','ADMIN','b_jones'),
       ('rosalind','cccccc','Michelle','Dockery','michelleckery@gmail.com','5544436','SUPERADMIN','michelle_dockery'),
       ('dryeye','reeeee','Henry','Golding','golding@gmail.com','3333445','ADMIN','henry_olding'),
       ('ernie','eeee','Bugzy','Malone','malone@gmail.com','2344555','ADMIN','bugzy_alone')