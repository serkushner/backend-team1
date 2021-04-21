-- liquibase formatted sql

-- 20210421.[BE-64]-change-interview-traineeid-can-be-null


ALTER TABLE interview
    DROP FOREIGN KEY trainee_interview @@

ALTER TABLE interview
    CHANGE COLUMN trainee_id trainee_id BIGINT (20) NULL @@

ALTER TABLE interview
    ADD CONSTRAINT trainee_interview
        FOREIGN KEY (trainee_id)
            REFERENCES trainee(id) @@