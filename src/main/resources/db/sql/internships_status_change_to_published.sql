-- liquibase formatted sql

-- changeset 20210421.[BE-52]-change-status-to-published-in-internships-in-db

UPDATE internship SET published = 'VisibleForInterns' WHERE published = 'VisibleForAdmins'