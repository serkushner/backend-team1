-- liquibase formatted sql

-- changeset 20210402.[BE-40]-addition_to_internship_subject

INSERT INTO internship_subject(subject_id, internship_id)
VALUES('1','1'),('1','2')