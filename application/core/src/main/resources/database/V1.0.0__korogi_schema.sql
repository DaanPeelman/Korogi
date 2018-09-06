CREATE TABLE USERS (
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

CREATE SEQUENCE SEQ_USER START WITH 1;

CREATE TABLE ROLES (
  id BIGINT NOT NULL,
  role_type VARCHAR(32) NOT NULL,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_roles PRIMARY KEY(id)
);

CREATE SEQUENCE SEQ_ROLE START WITH 1;

CREATE TABLE ANIME (
  id BIGINT NOT NULL,
  anime_type VARCHAR(32) NOT NULL,
  name_en VARCHAR(512) NOT NULL,
  name_ro VARCHAR(512) NOT NULL,
  start_air DATE,
  end_air DATE,
  synopsis TEXT NOT NULL,
  prequal_id BIGINT,
  backdrop_url VARCHAR(512) NOT NULL,
  poster_url VARCHAR(512) NOT NULL,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_anime PRIMARY KEY(id),

  CONSTRAINT fk_anime_prequal FOREIGN KEY(prequal_id) REFERENCES ANIME(id)
);

CREATE SEQUENCE SEQ_ANIME START WITH 1;

CREATE TABLE EPISODES (
  id BIGINT NOT NULL,
  anime_id BIGINT NOT NULL,
  name VARCHAR(128) NOT NULL,
  synopsis TEXT NOT NULL,
  duration_in_minutes SMALLINT NOT NULL,
  air_date DATE NOT NULL,

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_episodes PRIMARY KEY(id),

  CONSTRAINT fk_episodes_anime FOREIGN KEY(anime_id) REFERENCES ANIME(id)
);

CREATE SEQUENCE SEQ_EPISODE START WITH 1;

CREATE TABLE PERSONAGES (
  id BIGINT NOT NULL,
  first_name VARCHAR(128) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  photo_url VARCHAR(512),

  creation_date TIMESTAMP NOT NULL,
  created_by VARCHAR(128) NOT NULL,
  modification_date TIMESTAMP,
  modified_by VARCHAR(128),
  version BIGINT NOT NULL,

  CONSTRAINT pk_personages PRIMARY KEY(id)
);

CREATE SEQUENCE SEQ_PERSONAGE START WITH 1;

CREATE TABLE ANIME_PERSONAGES(
  anime_id BIGINT NOT NULL,
  personage_id BIGINT NOT NULL,

  CONSTRAINT pk_anime_personages PRIMARY KEY(anime_id, personage_id),

  CONSTRAINT fk_anime_personages_anime FOREIGN KEY(anime_id) REFERENCES ANIME(id),
  CONSTRAINT fk_anime_personages_personages FOREIGN KEY(personage_id) REFERENCES PERSONAGES(id)
)