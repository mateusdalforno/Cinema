CREATE DATABASE cinema;
USE cinema;

CREATE TABLE filme (
    idFilme int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo varchar(50),
    tempo int,
    imagem3d tinyint,
    dublado tinyint,
    sinopse varchar(500),
    categoria varchar(20)
);
