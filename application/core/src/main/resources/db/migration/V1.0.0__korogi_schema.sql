CREATE EXTENSION pg_trgm;

CREATE FUNCTION LIKE_SIMILARITY(columnName TEXT, expectedValue TEXT) RETURNS BOOLEAN AS
$$
BEGIN
    RETURN expectedValue % ANY (STRING_TO_ARRAY(columnName, ' '));
END
$$ LANGUAGE plpgsql;


CREATE TABLE USERS (
    id                BIGINT       NOT NULL CONSTRAINT pk_users PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    provider_id       VARCHAR(64)  NOT NULL,
    email             VARCHAR(128) NOT NULL,
    username          VARCHAR(128) NOT NULL,
    biography         TEXT         NOT NULL,
    salt              BYTEA        NOT NULL,
    activation_code   CHAR(36),
    activated         BOOLEAN      NOT NULL,

    creation_date     TIMESTAMP    NOT NULL,
    created_by        VARCHAR(128) NOT NULL,
    modification_date TIMESTAMP,
    modified_by       VARCHAR(128),
    version           BIGINT       NOT NULL
);

CREATE INDEX idx_users_provider_id ON USERS (provider_id);
CREATE INDEX idx_users_username ON USERS (username);


CREATE TABLE ROLES (
    id                BIGINT       NOT NULL CONSTRAINT pk_roles PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    role_type         VARCHAR(32)  NOT NULL,

    creation_date     TIMESTAMP    NOT NULL,
    created_by        VARCHAR(128) NOT NULL,
    modification_date TIMESTAMP,
    modified_by       VARCHAR(128),
    version           BIGINT       NOT NULL
);


CREATE TABLE ANIME (
    id                BIGINT       NOT NULL CONSTRAINT pk_anime PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    anime_type        VARCHAR(32)  NOT NULL,
    name_en           VARCHAR(512) NOT NULL,
    name_ro           VARCHAR(512) NOT NULL,
    start_air         DATE,
    end_air           DATE,
    synopsis          TEXT         NOT NULL,
    prequal_id        BIGINT CONSTRAINT fk_anime_prequal REFERENCES ANIME (id),
    backdrop_url      VARCHAR(512) NOT NULL,
    poster_url        VARCHAR(512) NOT NULL,

    creation_date     TIMESTAMP    NOT NULL,
    created_by        VARCHAR(128) NOT NULL,
    modification_date TIMESTAMP,
    modified_by       VARCHAR(128),
    version           BIGINT       NOT NULL
);

CREATE INDEX idx_anime_prequal_id ON ANIME (prequal_id);


CREATE TABLE EPISODES (
    id                  BIGINT       NOT NULL CONSTRAINT pk_episodes PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    anime_id            BIGINT       NOT NULL CONSTRAINT fk_episodes_anime REFERENCES ANIME (id),
    name                VARCHAR(128) NOT NULL,
    synopsis            TEXT         NOT NULL,
    duration_in_minutes SMALLINT     NOT NULL,
    air_date            DATE         NOT NULL,

    creation_date       TIMESTAMP    NOT NULL,
    created_by          VARCHAR(128) NOT NULL,
    modification_date   TIMESTAMP,
    modified_by         VARCHAR(128),
    version             BIGINT       NOT NULL
);

CREATE INDEX idx_episodes_anime_id ON EPISODES (anime_id);


CREATE TABLE PERSONAGES (
    id                BIGINT       NOT NULL CONSTRAINT pk_personages PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    first_name        VARCHAR(128) NOT NULL,
    last_name         VARCHAR(128) NOT NULL,
    photo_url         VARCHAR(512),

    creation_date     TIMESTAMP    NOT NULL,
    created_by        VARCHAR(128) NOT NULL,
    modification_date TIMESTAMP,
    modified_by       VARCHAR(128),
    version           BIGINT       NOT NULL
);


CREATE TABLE ANIME_PERSONAGES (
    anime_id     BIGINT NOT NULL CONSTRAINT fk_anime_personages_anime REFERENCES ANIME (id),
    personage_id BIGINT NOT NULL CONSTRAINT fk_anime_personages_personages REFERENCES PERSONAGES (id),

    CONSTRAINT pk_anime_personages PRIMARY KEY (anime_id, personage_id)
);

CREATE INDEX idx_anime_personages_anime_id ON ANIME_PERSONAGES (anime_id);
CREATE INDEX idx_anime_personages_personage_id ON ANIME_PERSONAGES (personage_id);