-- Skema kao u phpMyAdmin primeru: vozilo + osiguranje
-- MySQL / SQLyog

CREATE DATABASE IF NOT EXISTS osiguranje_db
  CHARACTER SET utf8
  COLLATE utf8_unicode_ci;

USE osiguranje_db;

SET NAMES utf8;

DROP TABLE IF EXISTS osiguranje;
DROP TABLE IF EXISTS vozilo;

-- ============================================
-- vozilo (sifraVozila = primarni kljuc, int)
-- ============================================
CREATE TABLE vozilo (
    sifraVozila       INT NOT NULL,
    regBroj           VARCHAR(50) NOT NULL,
    godinaProizvodnje INT NOT NULL,
    ime               VARCHAR(60) NOT NULL,
    prezime           VARCHAR(60) NOT NULL,
    PRIMARY KEY (sifraVozila)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO vozilo (sifraVozila, regBroj, godinaProizvodnje, ime, prezime) VALUES
(1, 'BG 27812 TX', 2012, 'Mirko', 'Mirkic'),
(2, 'BG 27821 GD', 2017, 'Mile', 'Milojkovic'),
(3, 'UE 66666 GF', 2015, 'Marko', 'Markovic'),
(4, 'NS 323 HI', 2018, 'Marko', 'Milovic'),
(5, 'KG 555 AB', 2020, 'Jelena', 'Jovanovic');

-- ============================================
-- osiguranje
-- ============================================
CREATE TABLE osiguranje (
    osiguranjeID   INT NOT NULL AUTO_INCREMENT,
    sifraVozila    INT NOT NULL,
    datumPocetka   DATE NOT NULL,
    datumUnosa     DATE NOT NULL,
    imePrezime     VARCHAR(255) NOT NULL,
    ukupnaPremija  DOUBLE NOT NULL,
    PRIMARY KEY (osiguranjeID),
    CONSTRAINT fk_osig_vozilo FOREIGN KEY (sifraVozila) REFERENCES vozilo (sifraVozila)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO osiguranje (sifraVozila, datumPocetka, datumUnosa, imePrezime, ukupnaPremija) VALUES
(1, '2025-03-01', '2025-02-28', 'Mirko Mirkic', 125000.50),
(2, '2025-06-15', '2025-06-10', 'Mile Milojkovic', 98000),
(3, '2024-11-20', '2024-11-18', 'Marko Markovic', 210500.75),
(4, '2025-01-10', '2025-01-05', 'Marko Milovic', 87500),
(5, '2025-08-01', '2025-07-25', 'Jelena Jovanovic', 156300.25);
