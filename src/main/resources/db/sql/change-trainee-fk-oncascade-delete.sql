-- liquibase formatted sql

-- changeset 20210421.[BE-62]-add-trainee-foreign-key -in-additional-info-ondelete-cascade

ALTER TABLE additional_info 
DROP FOREIGN KEY additional_info_trainee @@
ALTER TABLE additional_info
ADD CONSTRAINT additional_info_trainee
  FOREIGN KEY (trainee_id)
  REFERENCES trainee (id)
  ON DELETE CASCADE
  ON UPDATE RESTRICT @@