-- liquibase formatted sql

-- changeset 20210420.[BE-52]-change-status-to-published-in-internships-in-db

UPDATE internship SET published = true WHERE published = false