/*
Music Collection - BESD Final Project 
Schema MySQL Code
Written by: Joseph Falzini 
*/

DROP TABLE IF EXISTS collection_albums;
DROP TABLE IF EXISTS albums;
DROP TABLE IF EXISTS collections;
DROP TABLE IF EXISTS users;

-- #1: Create the tables in the schema (user, collection, collection_album, album
CREATE TABLE users (
	user_id int unsigned NOT NULL AUTO_INCREMENT,
	collection_id int unsigned, 
	first_name varchar(20) NOT NULL,
	last_name varchar(25) NOT NULL,
	PRIMARY KEY (user_id)
);

CREATE TABLE collections (
	collection_id int unsigned NOT NULL AUTO_INCREMENT,
	user_id int unsigned NOT NULL, 
	collection_name varchar(255) NOT NULL,
	PRIMARY KEY (collection_id),
	FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);

CREATE TABLE albums (
	album_id int unsigned NOT NULL AUTO_INCREMENT,
	artist varchar(255) NOT NULL, 
	title varchar(255) NOT NULL, 
	num_songs int unsigned NOT NULL, 
	release_year int NOT NULL CHECK (release_year BETWEEN 0000 and 2100),
	PRIMARY KEY (album_id)
);

CREATE TABLE collection_albums (
	collection_id int unsigned NOT NULL,
	album_name int unsigned NOT NULL,
	FOREIGN KEY (collection_id) REFERENCES collections (collection_id) ON DELETE CASCADE,
	FOREIGN KEY (album_id) REFERENCES albums (album_id) ON DELETE CASCADE
);

/*
STRETCH GOAL - Add tables for artists and songs 

CREATE TABLE artist ();
CREATE TABLE song ();
*/

-- #2: Add some albums into the Database
-- NOTE: the ability to add an album to the DB will be availalbe in the application (Stretch-goal). 
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Pink Floyd', 'The Wall', 26, 1979);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Pink Floyd', 'The Dark Side of the Moon', 10, 1973);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Alter Bridge', 'Fortress', 12, 2013);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Shinedown', 'Planet Zero', 20, 2022);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Metallica', 'Master of Puppets', 8, 1986);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('System of a Down', 'Toxicity', 15, 2001);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('The Beatles', 'Magical Mystery Tour', 11, 1967);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Guns N'' Roses', 'Appetite For Destruction', 12, 1987);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Elton John', 'Madman Across The Water', 9, 1971);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Greta Van Fleet', 'Anthem of The Peaceful Army', 11, 2018);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Three Days Grace', 'Three Days Grace', 12, 2003);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Alice in Chains', 'Dirt', 13, 1992);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Ghost', 'Impera', 12, 2022);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Korn', 'Follow The Leader', 14, 1998);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Green Day', 'American Idiot', 12, 2004);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Queen', 'Sheer Heart Attack', 13, 1974);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Foo Fighters', 'The Colour and The Shape', 14, 1997);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Slipknot', 'We Are Not Your Kind', 14, 2019);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Avatar', 'The Black Waltz', 11, 2012);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Breaking Benjamin', 'Phobia', 13, 2006);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Roger Waters', 'Is This The Life We Really Want?', 12, 2017);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Lacuna Coil', 'Comalies', 13, 2002);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('TOOL', '10,000 Days', 11, 2006);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Meat Loaf', 'Bat Out Of Hell', 7, 1977);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('I Prevail', 'TRUE POWER', 15, 2022);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Metallica', 'Hardwired...To Self-Destruct', 12, 2016);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('The Eagles', 'Hotel California', 9, 1976);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Disturbed', 'Immortalized', 13, 2015);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Starset', 'Vessels', 15, 2017);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Stone Sour', 'House of Gold & Bones, Part 1', 11, 2012);
INSERT INTO albums (artist, title, num_songs, release_year) VALUES ('Motionless in White', 'Scoring the End of the World', 13, 2022);

-- #3: Create an exisiting user for initial creation/usage of the application.
INSERT INTO users (first_name, last_name) VALUES ('Joseph', 'Falzini');

ALTER TABLE users ADD CONSTRAINT FOREIGN KEY (collection_id) REFERENCES collections (collection_id) ON DELETE CASCADE;

