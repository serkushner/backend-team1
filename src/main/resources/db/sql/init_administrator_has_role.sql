-- liquibase formatted sql

-- changeset 20210514.[BE-17]-fill-administrator-has-role-table

INSERT INTO administrator_has_role(administrator_id,role_id)
VALUES ('1','2'),
       ('2','1'),
       ('3','1'),
       ('4','1'),
       ('5','2'),
       ('6','1'),
       ('7','2'),
       ('8','1')
