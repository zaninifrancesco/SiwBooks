-- Popolamento tabella Autore
INSERT INTO autore (nome, cognome, data_nascita, nazionalita, foto) VALUES ('J.R.R.', 'Tolkien', '1892-01-03', 'Britannica', 'images/tolkien.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('George', 'Orwell', '1903-06-25', '1950-01-21', 'Britannica', 'images/orwell.jpg');
INSERT INTO autore (nome, cognome, data_nascita, nazionalita, foto) VALUES ('Isaac', 'Asimov', '1920-01-02', 'Russa', 'images/asimov.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Jane', 'Austen', '1775-12-16', '1817-07-18', 'Inglese', 'images/austen.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Gabriel', 'García Márquez', '1927-03-06', '2014-04-17', 'Colombiana', 'images/marquez.jpg');
INSERT INTO autore (nome, cognome, data_nascita, nazionalita, foto) VALUES ('Stephen', 'King', '1947-09-21', 'Americana', 'images/king.jpg');
INSERT INTO autore (nome, cognome, data_nascita, nazionalita, foto) VALUES ('Haruki', 'Murakami', '1949-01-12', 'Giapponese', 'images/murakami.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Virginia', 'Woolf', '1882-01-25', '1941-03-28', 'Britannica', 'images/woolf.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Ernest', 'Hemingway', '1899-07-21', '1961-07-02', 'Americana', 'images/hemingway.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Franz', 'Kafka', '1883-07-03', '1924-06-03', 'Ceca', 'images/kafka.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Fyodor', 'Dostoevsky', '1821-11-11', '1881-02-09', 'Russa', 'images/dostoevsky.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Leo', 'Tolstoy', '1828-09-09', '1910-11-20', 'Russa', 'images/tolstoy.jpg');
INSERT INTO autore (nome, cognome, data_nascita, data_morte, nazionalita, foto) VALUES ('Mark', 'Twain', '1835-11-30', '1910-04-21', 'Americana', 'images/twain.jpg');

-- Popolamento tabella Utente
INSERT INTO users (username, password, ruolo) VALUES ('admin', '$2a$12$LxWlyrBE5zkx0lVDURftsOIxpWjAOoT9n6ODf527UQjkSc4Fi0eIu', 'ADMIN');
INSERT INTO users (username, password, ruolo) VALUES ('user', '$2a$12$LxWlyrBE5zkx0lVDURftsOIxpWjAOoT9n6ODf527UQjkSc4Fi0eIu', 'USER');
INSERT INTO users (username, password, ruolo) VALUES ('mario.rossi', '$2a$12$LxWlyrBE5zkx0lVDURftsOIxpWjAOoT9n6ODf527UQjkSc4Fi0eIu', 'USER');
INSERT INTO users (username, password, ruolo) VALUES ('anna.bianchi', '$2a$12$LxWlyrBE5zkx0lVDURftsOIxpWjAOoT9n6ODf527UQjkSc4Fi0eIu', 'USER');
INSERT INTO users (username, password, ruolo) VALUES ('luca.verdi', '$2a$12$LxWlyrBE5zkx0lVDURftsOIxpWjAOoT9n6ODf527UQjkSc4Fi0eIu', 'ADMIN');

-- Popolamento tabella Libro
INSERT INTO libro (id, titolo, anno) VALUES (1, 'Il Signore degli Anelli', 1954);
INSERT INTO libro (id, titolo, anno) VALUES (2, '1984', 1949);
INSERT INTO libro (id, titolo, anno) VALUES (3, 'Io, Robot', 1950);
INSERT INTO libro (id, titolo, anno) VALUES (4, 'Orgoglio e Pregiudizio', 1813);
INSERT INTO libro (id, titolo, anno) VALUES (5, 'Cent\'anni di solitudine', 1967);
INSERT INTO libro (id, titolo, anno) VALUES (6, 'IT', 1986);
INSERT INTO libro (id, titolo, anno) VALUES (7, 'Norwegian Wood', 1987);
INSERT INTO libro (id, titolo, anno) VALUES (8, 'Gita al faro', 1927);
INSERT INTO libro (id, titolo, anno) VALUES (9, 'Il vecchio e il mare', 1952);
INSERT INTO libro (id, titolo, anno) VALUES (10, 'La metamorfosi', 1915);
INSERT INTO libro (id, titolo, anno) VALUES (11, 'Delitto e castigo', 1866);
INSERT INTO libro (id, titolo, anno) VALUES (12, 'Guerra e pace', 1869);
INSERT INTO libro (id, titolo, anno) VALUES (13, 'Le avventure di Huckleberry Finn', 1884);
INSERT INTO libro (id, titolo, anno) VALUES (14, 'Lo Hobbit', 1937);
INSERT INTO libro (id, titolo, anno) VALUES (15, 'La fattoria degli animali', 1945);


-- Popolamento tabella di join libro_autori
-- Tolkien (id 1)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (1, 1); -- Il Signore degli Anelli - Tolkien
INSERT INTO libro_autori (libri_id, autori_id) VALUES (14, 1); -- Lo Hobbit - Tolkien

-- Orwell (id 2)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (2, 2); -- 1984 - Orwell
INSERT INTO libro_autori (libri_id, autori_id) VALUES (15, 2); -- La fattoria degli animali - Orwell

-- Asimov (id 3)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (3, 3); -- Io, Robot - Asimov

-- Austen (id 4)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (4, 4); -- Orgoglio e Pregiudizio - Austen

-- García Márquez (id 5)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (5, 5); -- Cent'anni di solitudine - García Márquez

-- King (id 6)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (6, 6); -- IT - King

-- Murakami (id 7)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (7, 7); -- Norwegian Wood - Murakami

-- Woolf (id 8)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (8, 8); -- Gita al faro - Woolf

-- Hemingway (id 9)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (9, 9); -- Il vecchio e il mare - Hemingway

-- Kafka (id 10)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (10, 10); -- La metamorfosi - Kafka

-- Dostoevsky (id 11)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (11, 11); -- Delitto e castigo - Dostoevsky

-- Tolstoy (id 12)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (12, 12); -- Guerra e pace - Tolstoy

-- Twain (id 13)
INSERT INTO libro_autori (libri_id, autori_id) VALUES (13, 13); -- Le avventure di Huckleberry Finn - Twain

-- Popolamento tabella Recensione
-- Recensioni per "Il Signore degli Anelli" (id 1)
