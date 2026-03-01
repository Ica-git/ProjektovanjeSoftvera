/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.*;
import java.sql.*;

/**
 *
 * @author PC
 */
public class Klijent {
    
    private int klijentID;
    private String ime;
    private String prezime;
    private String telefon;
    private String elPosta;
    private String adresa;

    public Klijent() {
    }

    public Klijent(int klijentID, String ime, String prezime, String telefon, String elPosta, String adresa) {
        this.klijentID = klijentID;
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.elPosta = elPosta;
        this.adresa = adresa;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public int getKlijentID() {
        return klijentID;
    }

    public void setKlijentID(int klijentID) {
        this.klijentID = klijentID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getElPosta() {
        return elPosta;
    }

    public void setElPosta(String elPosta) {
        this.elPosta = elPosta;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    
    
}
