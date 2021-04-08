-- liquibase formatted sql

-- changeset 20210406.[BE-41]-set-name-not-null

ALTER TABLE internship MODIFY COLUMN name VARCHAR(256) NOT null;