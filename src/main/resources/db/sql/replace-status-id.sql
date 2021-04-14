-- liquibase formatted sql

-- 20210413.[BE-23]-replace-trainee-status-id-to-additional-info

ALTER TABLE trainee DROP FOREIGN KEY fk_deletion_set_null_status @@

ALTER TABLE trainee DROP FOREIGN KEY status @@

ALTER TABLE trainee DROP status_id @@

ALTER TABLE additional_info ADD status_trainee enum('REGISTERED', 'RECRUITER_INTERVIEW_PENDING', 'RECRUITER_INTERVIEW_PASSED', 'TECHNICAL_INTERVIEW_PENDING', 'TECHNICAL_INTERVIEW_PASSED', 'REJECTED', 'ACCEPTED', 'PENDING_FOR_DECISION') @@

ALTER TABLE interview_period MODIFY COLUMN day_of_week enum('MONDAY', 'TUESDAY', 'WEDNESDAY', 'THURSDAY', 'FRIDAY', 'SATURDAY', 'SUNDAY') @@

DROP TABLE trainee_status @@