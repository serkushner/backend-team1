-- liquibase formatted sql

-- changeset 20210514.[BE-17]-fill-role-table

INSERT INTO role(id,role_name)
VALUES ('1','ROLE_ADMIN'),
       ('2','ROLE_SUPERADMIN')