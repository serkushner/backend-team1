-- liquibase formatted sql

-- changeset 20210419.[BE-52]-add-blended-format

ALTER TABLE internship MODIFY `format` ENUM('ONLINE','OFFLINE','BLENDED')