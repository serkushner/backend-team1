-- liquibase formatted sql

-- 20210503.[BE-79]-change-country-code-can-be-null

ALTER TABLE country
    MODIFY COLUMN code INT NULL