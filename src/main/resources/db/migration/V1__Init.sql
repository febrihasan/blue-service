-- Create Table User
CREATE TABLE USERS (
    ID                  BIGINT PRIMARY KEY NOT NULL,
    USER_ID             VARCHAR(255) NOT NULL,
    USERNAME            VARCHAR(50) NOT NULL,
    PASSWORD            VARCHAR(50) NOT NULL,
    FIRST_NAME          VARCHAR(50) NOT NULL,
    LAST_NAME           VARCHAR(50) NOT NULL,
    EMAIL               VARCHAR(100) NOT NULL
);

-- Create Index User
CREATE INDEX idx_users ON USERS USING btree(ID);