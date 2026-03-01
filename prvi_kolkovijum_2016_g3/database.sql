-- Database for prvi_kolkovijum_2016_g3

CREATE DATABASE IF NOT EXISTS prvi_kolkovijum_2016_g3;
USE prvi_kolkovijum_2016_g3;

-- ============================================
-- Table: VrstaPosla
-- ============================================
DROP TABLE IF EXISTS Ucinak;
DROP TABLE IF EXISTS VrstaPosla;
DROP TABLE IF EXISTS Radnik;

CREATE TABLE VrstaPosla (
    VrstaPoslaID INT NOT NULL AUTO_INCREMENT,
    Naziv VARCHAR(100) NOT NULL,
    PRIMARY KEY (VrstaPoslaID)
);

INSERT INTO VrstaPosla (Naziv) VALUES
('Projekcija'),
('Montaza'),
('Sinhronizacija'),
('Editovanje'),
('Color korekcija');

-- ============================================
-- Table: Radnik
-- ============================================
CREATE TABLE Radnik (
    RadnikID INT NOT NULL AUTO_INCREMENT,
    Ime VARCHAR(50) NOT NULL,
    Prezime VARCHAR(50) NOT NULL,
    Specijalizacija VARCHAR(100) NOT NULL,
    PRIMARY KEY (RadnikID)
);

INSERT INTO Radnik (Ime, Prezime, Specijalizacija) VALUES
('Marko', 'Markovic', 'Montazer'),
('Ana', 'Anic', 'Sinhronizator'),
('Petar', 'Petrovic', 'Editor'),
('Jelena', 'Jovanovic', 'Colorist'),
('Stefan', 'Stojanovic', 'Projekcionista');

-- ============================================
-- Table: Ucinak
-- ============================================
CREATE TABLE Ucinak (
    UcinakID INT NOT NULL AUTO_INCREMENT,
    BrojSati INT NOT NULL,
    Datum DATE NOT NULL,
    VrstaPoslaID INT NOT NULL,
    RadnikID INT NOT NULL,
    PRIMARY KEY (UcinakID),
    FOREIGN KEY (VrstaPoslaID) REFERENCES VrstaPosla(VrstaPoslaID),
    FOREIGN KEY (RadnikID) REFERENCES Radnik(RadnikID)
);

INSERT INTO Ucinak (BrojSati, Datum, VrstaPoslaID, RadnikID) VALUES
(8, '2026-01-15', 1, 5),
(6, '2026-01-20', 2, 1),
(4, '2026-01-25', 3, 2),
(10, '2026-02-10', 4, 3),
(5, '2026-02-01', 5, 4);
