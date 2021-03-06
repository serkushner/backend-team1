-- liquibase formatted sql

-- changeset 20210505.[BE-82]-add-email-not-confirm

ALTER TABLE additional_info
    CHANGE COLUMN status_trainee status_trainee ENUM ('EMAIL_NOT_CONFIRM','REGISTERED', 'RECRUITER_INTERVIEW_PENDING', 'RECRUITER_INTERVIEW_REJECTED', 'RECRUITER_INTERVIEW_ACCEPTED', 'RECRUITER_INTERVIEW_PASSED', 'TECHNICAL_INTERVIEW_PENDING', 'TECHNICAL_INTERVIEW_REJECTED', 'TECHNICAL_INTERVIEW_ACCEPTED', 'REJECTED', 'ACCEPTED') NULL DEFAULT NULL