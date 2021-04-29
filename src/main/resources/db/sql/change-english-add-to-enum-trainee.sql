-- liquibase formatted sql

-- changeset 20210421.[BE-67]-add-english-enum-to-trainee

ALTER TABLE additional_info
CHANGE COLUMN additional_info english ENUM('A1', 'A2', 'B1', 'B2', 'C1', 'C2') NULL DEFAULT NULL

