CREATE SCHEMA IF NOT EXISTS users;

CREATE TABLE IF NOT EXISTS users.users (
  id                         BIGINT PRIMARY KEY ,
  username                   VARCHAR(255) NOT NULL,
  email                      VARCHAR(255) NOT NULL,
  password                   TEXT,
  first_name                 VARCHAR(255) NOT NULL,
  last_name                  VARCHAR(255) NOT NULL,
  state                      VARCHAR(100) NOT NULL,
  creation_date              TIMESTAMP,
  is_account_non_expired     BOOLEAN      NOT NULL,
  is_credentials_non_expired BOOLEAN      NOT NULL,
  is_account_non_locked      BOOLEAN      NOT NULL
);

CREATE TABLE IF NOT EXISTS users.user_user_profile (
  user_id                    BIGINT  NOT NULL,
  user_profile_id            BIGINT  NOT NULL
);

CREATE TABLE IF NOT EXISTS users.roles (
  id                         BIGINT PRIMARY KEY,
  type                       VARCHAR(64) NOT NULL
);

CREATE SCHEMA IF NOT EXISTS oauth2;

CREATE TABLE IF NOT EXISTS oauth_client_details (
  client_id               VARCHAR(255) PRIMARY KEY,
  resource_ids            VARCHAR(255),
  client_secret           VARCHAR(255),
  scope                   VARCHAR(255),
  authorized_grant_types  VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities             VARCHAR(255),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(255)
);


INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types,
 web_server_redirect_uri, authorities, access_token_validity,
 refresh_token_validity, additional_information, autoapprove)
VALUES
  ('my-trusted-client', '{noop}secret', 'read,write',
   'password,refresh_token', NULL, NULL, 86400, 2592000, NULL, TRUE);

CREATE TABLE IF NOT EXISTS oauth_access_token (
  token_id          VARCHAR(255),
  token             BYTEA,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name         VARCHAR(255),
  client_id         VARCHAR(255),
  authentication    BYTEA,
  refresh_token     VARCHAR(255)
);

 CREATE TABLE IF NOT EXISTS oauth_refresh_token (
  token_id       VARCHAR(255),
  token          BYTEA,
   authentication BYTEA
);


CREATE SEQUENCE hibernate_sequence
  START WITH 1
  INCREMENT BY 1;

