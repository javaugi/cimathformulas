/*
hsqldb
CREATE TABLE IF NOT EXISTS category (id INTEGER IDENTITY PRIMARY KEY, name VARCHAR(100), description VARCHAR(2000), age_group VARCHAR(20), created DATETIME, inserted BIGINT);
CREATE TABLE IF NOT EXISTS Lego_Set (id INTEGER, name VARCHAR(100), min_Age INTEGER, max_Age INTEGER);
CREATE TABLE IF NOT EXISTS Handbuch (handbuch_id INTEGER, author VARCHAR(100), text CLOB);
CREATE TABLE IF NOT EXISTS Model (name VARCHAR(100), description CLOB, lego_set INTEGER);
*/
/* H2 DB */
CREATE TABLE IF NOT EXISTS category (
    id INTEGER PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(100), 
    description VARCHAR(2000), 
    age_group VARCHAR(20), 
    created TIMESTAMP, 
    inserted BIGINT
);
CREATE TABLE IF NOT EXISTS Lego_Set (id INTEGER, name VARCHAR(100), min_Age INTEGER, max_Age INTEGER);
CREATE TABLE IF NOT EXISTS Handbuch (handbuch_id INTEGER, author VARCHAR(100), text CLOB);
CREATE TABLE IF NOT EXISTS Model (name VARCHAR(100), description CLOB, lego_set INTEGER);

/*
CREATE TABLE IF NOT EXISTS Person  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    firstName VARCHAR(20),
    lastName VARCHAR(20)
);
*/