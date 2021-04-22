-- liquibase formatted sql
-- changeset 20210421.[BE-65]-add-interviewer-subject

CREATE TABLE exadel_internships.interviewer_subject
(
    `subject_id` BIGINT(20) NOT NULL,
    `interviewer_id` BIGINT(20) NOT NULL,
    PRIMARY KEY (`subject_id`, `interviewer_id`))@@
INSERT INTO exadel_internships.interviewer_subject (`subject_id`, `interviewer_id`)
VALUES ('1', '2'),
       ('2', '3'),
       ('3', '4'),
       ('4', '5'),
       ('1', '7'),
       ('2', '8'),
       ('3', '9'),
       ('4', '10')
