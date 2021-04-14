-- liquibase formatted sql

-- 20210413.[BE-23]-replace-trainee-status-id-to-additional-info

ALTER TABLE trainee DROP FOREIGN KEY fk_deletion_set_null_status @@

ALTER TABLE trainee DROP FOREIGN KEY status @@

ALTER TABLE trainee DROP status_id @@

ALTER TABLE additional_info ADD status_id bigint @@

ALTER TABLE additional_info ADD CONSTRAINT Fk_trainee_status FOREIGN KEY (status_id) REFERENCES trainee_status(id) @@

ALTER TABLE interview_period MODIFY COLUMN day_of_week enum('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') @@