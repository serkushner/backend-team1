-- liquibase formatted sql

-- 20210406.[BE-41]-change-internship-additional_info-varchar-2560

ALTER TABLE internship MODIFY COLUMN additional_info VARCHAR(2560);