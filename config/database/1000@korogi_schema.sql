CREATE TABLE KOROGI.USERS (
  id BIGINT NOT NULL,
  email VARCHAR(128) NOT NULL,
  username VARCHAR(128) NOT NULL,
  password VARCHAR(128) NOT NULL,
  activation_code CHAR(36),
  activated BOOLEAN NOT NULL,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_users PRIMARY KEY(id)
);

CREATE SEQUENCE KOROGI.SEQ_USER START WITH 1;

CREATE TABLE KOROGI.ROLES (
  id BIGINT NOT NULL,
  role_type VARCHAR(32) NOT NULL,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_roles PRIMARY KEY(id)
);

CREATE SEQUENCE KOROGI.SEQ_ROLE START WITH 1;

CREATE TABLE KOROGI.ANIME (
  id BIGINT NOT NULL,
  anime_type VARCHAR(32) NOT NULL,
  name_en VARCHAR(512) NOT NULL,
  name_ro VARCHAR(512) NOT NULL,
  start_air DATE,
  end_air DATE,
  synopsis TEXT NOT NULL,
  prequal_id BIGINT,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_anime PRIMARY KEY(id),

  CONSTRAINT fk_anime_prequal FOREIGN KEY(prequal_id) REFERENCES KOROGI.ANIME(id)
);

CREATE SEQUENCE KOROGI.SEQ_ANIME START WITH 1;