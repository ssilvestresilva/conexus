ALTER TABLE resultado DROP COLUMN IF EXISTS last_data_base;
ALTER TABLE resultado ADD COLUMN last_data_base DATE NOT NULL;