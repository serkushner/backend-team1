-- liquibase formatted sql

-- changeset 20210410.[BE-23]-fill-trainee_status

INSERT INTO trainee_status (name) VALUES ('registered'), ('recruiter interview pending'),
('recruiter interview passed'), ('technical interview pending'),
('technical interview passed'), ('rejected'), ('accepted'), ('pending for a decision')