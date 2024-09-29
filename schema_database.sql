-- Database: Informazioni

DROP DATABASE IF EXISTS Informazioni;

CREATE DATABASE Informazioni
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Italian_Italy.1252'
    LC_CTYPE = 'Italian_Italy.1252'
    LOCALE_PROVIDER = 'libc'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

\c informazioni

CREATE TABLE persone (
    nome TEXT NOT NULL,
    cognome TEXT NOT NULL,
    indirizzo TEXT NOT NULL,
    telefono TEXT NOT NULL PRIMARY KEY,
    eta INT NOT NULL
);

CREATE TABLE utenti (
    username TEXT NOT NULL,
    password TEXT NOT NULL,
    CONSTRAINT unique_username_password UNIQUE (username, password)
);