-- Osiguranje vozila – baza po zadatku (vozila, vrste osiguranja, osiguranje, stavke, korisnici)
-- MySQL / SQLyog – UTF-8 za srpski tekst

CREATE DATABASE IF NOT EXISTS osiguranje_vozila
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE osiguranje_vozila;

SET NAMES utf8mb4;

-- Drop (redosled zbog FK)
DROP TABLE IF EXISTS StavkaOsiguranja;
DROP TABLE IF EXISTS Osiguranje;
DROP TABLE IF EXISTS Vozilo;
DROP TABLE IF EXISTS VrstaOsiguranja;
DROP TABLE IF EXISTS Korisnik;

-- ============================================
-- Korisnik (prijava + ko je kreirao osiguranje)
-- ============================================
CREATE TABLE Korisnik (
    KorisnikID      INT NOT NULL AUTO_INCREMENT,
    ImePrezime      VARCHAR(120) NOT NULL,
    KorisnickoIme   VARCHAR(50)  NOT NULL,
    Lozinka         VARCHAR(100) NOT NULL,
    PRIMARY KEY (KorisnikID),
    UNIQUE KEY uq_korisnicko (KorisnickoIme)
);

INSERT INTO Korisnik (ImePrezime, KorisnickoIme, Lozinka) VALUES
('Ivan Radosavljevic', 'ica', 'ica'),
('Gile Radosavljevic', 'gica', 'gica'),
('Neda Radosavljevic', 'neda', 'neda'),
('Marko Petrovic', 'marko', 'marko123'),
('Ana Jovanovic', 'ana', 'ana123');

-- ============================================
-- Vozilo (sifra, registracija, godina, vlasnik – ime/prezime odvojeno za sort)
-- ============================================
CREATE TABLE Vozilo (
    VoziloID            INT NOT NULL AUTO_INCREMENT,
    SifraVozila         VARCHAR(20) NOT NULL,
    RegistarskiBroj     VARCHAR(20) NOT NULL,
    GodinaProizvodnje   INT NOT NULL,
    ImeVlasnika         VARCHAR(60) NOT NULL,
    PrezimeVlasnika     VARCHAR(60) NOT NULL,
    PRIMARY KEY (VoziloID),
    UNIQUE KEY uq_sifra_vozila (SifraVozila)
);

INSERT INTO Vozilo (SifraVozila, RegistarskiBroj, GodinaProizvodnje, ImeVlasnika, PrezimeVlasnika) VALUES
('VZ-001', 'BG 123-AB', 2018, 'Petar', 'Nikolic'),
('VZ-002', 'NS 456-CD', 2015, 'Jelena', 'Markovic'),
('VZ-003', 'NI 789-EF', 2020, 'Stefan', 'Ilic'),
('VZ-004', 'KG 321-GH', 2012, 'Milica', 'Stojanovic'),
('VZ-005', 'SU 654-IJ', 2019, 'Nikola', 'Pavlovic');

-- ============================================
-- Vrsta osiguranja (5 obaveznih iz zadatka)
-- ============================================
CREATE TABLE VrstaOsiguranja (
    VrstaOsiguranjaID INT NOT NULL AUTO_INCREMENT,
    Sifra             VARCHAR(10) NOT NULL,
    Naziv             VARCHAR(200) NOT NULL,
    Cena              DECIMAL(12, 2) NOT NULL,
    PRIMARY KEY (VrstaOsiguranjaID),
    UNIQUE KEY uq_vrsta_sifra (Sifra)
);

INSERT INTO VrstaOsiguranja (Sifra, Naziv, Cena) VALUES
('VO-01', 'Obavezno osiguranje', 8500.00),
('VO-02', 'Mini kasko osiguranje', 12000.00),
('VO-03', 'Kasko osiguranje', 35000.00),
('VO-04', 'Pomoc na putu - Republika Srbija', 4500.00),
('VO-05', 'Pomoc na putu - ostale drzave', 7800.00);

-- ============================================
-- Osiguranje (zaglavlje: kreiranje, premija, vozilo, korisnik, pocetak/kraj +1 godina)
-- Samo jedno osiguranje po vozilu po kalendarskoj godini (datum pocetka vazenja)
-- ============================================
CREATE TABLE Osiguranje (
    OsiguranjeID         INT NOT NULL AUTO_INCREMENT,
    VoziloID             INT NOT NULL,
    KorisnikID           INT NOT NULL,
    DatumKreiranja       DATE NOT NULL,
    DatumPocetkaVazenja  DATE NOT NULL,
    DatumZavrsetka       DATE NOT NULL,
    GodinaVazenja        INT NOT NULL COMMENT 'Ista godina kao YEAR(DatumPocetkaVazenja); za UNIQUE',
    UkupnaPremija        DECIMAL(12, 2) NOT NULL,
    PRIMARY KEY (OsiguranjeID),
    UNIQUE KEY uq_vozilo_godina (VoziloID, GodinaVazenja),
    CONSTRAINT fk_osig_vozilo   FOREIGN KEY (VoziloID)   REFERENCES Vozilo(VoziloID),
    CONSTRAINT fk_osig_korisnik FOREIGN KEY (KorisnikID) REFERENCES Korisnik(KorisnikID)
);

-- DatumZavrsetka = tacno 1 godina od pocetka (isti dan sledece godine u MySQL: DATE_ADD)
-- UkupnaPremija = suma (Cena * starost_auta) po stavkama – ovde uneta kao izracunata vrednost
-- Starost u 2026: npr. vozilo 2018 -> 8 godina (moze i floor(current_year - godina) u aplikaciji)

INSERT INTO Osiguranje (
    VoziloID, KorisnikID, DatumKreiranja, DatumPocetkaVazenja, DatumZavrsetka, GodinaVazenja, UkupnaPremija
) VALUES
-- Vozilo 1, 2025; starost 7 (2025-2018): stavke VO-01+VO-04 -> (8500+4500)*7 = 91000
(1, 1, '2025-03-10', '2025-04-01', '2026-04-01', 2025, 91000.00),
-- Vozilo 2, 2025; starost 10: VO-01+VO-02 -> (8500+12000)*10 = 205000
(2, 2, '2025-05-15', '2025-06-01', '2026-06-01', 2025, 205000.00),
-- Vozilo 3, 2025; starost 5: samo obavezno 8500*5
(3, 3, '2025-01-20', '2025-02-15', '2026-02-15', 2025, 42500.00),
-- Vozilo 4 (god. 2012), 2024, starost 12; stavke mini kasko + pomoc RS: (12000+4500)*12 = 198000
(4, 1, '2024-11-01', '2024-12-01', '2025-12-01', 2024, 198000.00),
-- Vozilo 5 (god. 2019), 2025, starost 6; stavke VO-01,02,04,05: (8500+12000+4500+7800)*6 = 196800
(5, 4, '2025-07-01', '2025-08-01', '2026-08-01', 2025, 196800.00);

-- Jos jedno osiguranje da imamo 6 redova (opciono) – ili ostavimo 5. User asked at least 5.
-- Dodajmo 6. red: vozilo 3 u 2024 godini (druga polisa za isto vozilo, druga godina)
INSERT INTO Osiguranje (
    VoziloID, KorisnikID, DatumKreiranja, DatumPocetkaVazenja, DatumZavrsetka, GodinaVazenja, UkupnaPremija
) VALUES
-- Vozilo 3 (god. 2020), 2024, starost 4; samo obavezno: 8500*4 = 34000
(3, 5, '2024-09-01', '2024-10-01', '2025-10-01', 2024, 34000.00);

-- ============================================
-- Stavka osiguranja (koje vrste ulaze u polisu)
-- ============================================
CREATE TABLE StavkaOsiguranja (
    StavkaOsiguranjaID INT NOT NULL AUTO_INCREMENT,
    OsiguranjeID       INT NOT NULL,
    VrstaOsiguranjaID  INT NOT NULL,
    PRIMARY KEY (StavkaOsiguranjaID),
    UNIQUE KEY uq_osig_vrsta (OsiguranjeID, VrstaOsiguranjaID),
    CONSTRAINT fk_stavka_osig FOREIGN KEY (OsiguranjeID)      REFERENCES Osiguranje(OsiguranjeID),
    CONSTRAINT fk_stavka_vrsta FOREIGN KEY (VrstaOsiguranjaID) REFERENCES VrstaOsiguranja(VrstaOsiguranjaID)
);

INSERT INTO StavkaOsiguranja (OsiguranjeID, VrstaOsiguranjaID) VALUES
(1, 1), (1, 4),
(2, 1), (2, 2),
(3, 1),
(4, 2), (4, 4),
(5, 1), (5, 2), (5, 4), (5, 5),
(6, 1);

-- Ukupno 10 stavki (>5); ako zelis tacno 5 redova u stavci, obrisi neke – zadatak trazi min 5 po tabeli
