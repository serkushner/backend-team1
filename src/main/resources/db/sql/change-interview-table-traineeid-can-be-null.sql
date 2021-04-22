-- liquibase formatted sql

-- 20210421.[BE-64]-change-interview-traineeid-can-be-null

ALTER TABLE interview
    MODIFY COLUMN trainee_id BIGINT (20) NULL