DROP TABLE IF EXISTS KOROGI.USERS;
CREATE TABLE KOROGI.USERS (
  id BIGINT NOT NULL,
  username VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,

  creation_date DATETIME NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date DATETIME,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_users PRIMARY KEY(id)
);

CREATE SEQUENCE KOROGI.SEQ_USER START WITH 1;