-- liquibase formatted sql

-- 20210406.[BE-41]-change-internship-description-varchar-2560

ALTER TABLE internship MODIFY COLUMN description VARCHAR(2560) NOT NULL;