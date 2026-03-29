/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author PC
 */
public class Osiguranje implements Serializable{
    
    private int osiguranjeID;
    private Vozilo vozilo;
    private Date datumPocetka;
    private Date datumUnosa;
    private String imePrezime;
    private double ukupnaPremija;

    public Osiguranje() {
    }

    public Osiguranje(int osiguranjeID, Vozilo vozilo, Date datumPocetka, Date datumUnosa, String imePrezime, double ukupnaPremija) {
        this.osiguranjeID = osiguranjeID;
        this.vozilo = vozilo;
        this.datumPocetka = datumPocetka;
        this.datumUnosa = datumUnosa;
        this.imePrezime = imePrezime;
        this.ukupnaPremija = ukupnaPremija;
    }

    public double getUkupnaPremija() {
        return ukupnaPremija;
    }

    public void setUkupnaPremija(double ukupnaPremija) {
        this.ukupnaPremija = ukupnaPremija;
    }

    public int getOsiguranjeID() {
        return osiguranjeID;
    }

    public void setOsiguranjeID(int osiguranjeID) {
        this.osiguranjeID = osiguranjeID;
    }

    public Vozilo getVozilo() {
        return vozilo;
    }

    public void setVozilo(Vozilo vozilo) {
        this.vozilo = vozilo;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public Date getDatumUnosa() {
        return datumUnosa;
    }

    public void setDatumUnosa(Date datumUnosa) {
        this.datumUnosa = datumUnosa;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }
    
    
    
}
