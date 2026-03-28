/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Vozilo implements Serializable{
    
    private int sifraVozila;
    private String regBroj;
    private int godinaProizvodnje;
    private String ime;
    private String prezime;

    public Vozilo() {
    }

    public Vozilo(int sifraVozila, String regBroj, int godinaProizvodnje, String ime, String prezime) {
        this.sifraVozila = sifraVozila;
        this.regBroj = regBroj;
        this.godinaProizvodnje = godinaProizvodnje;
        this.ime = ime;
        this.prezime = prezime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getSifraVozila() {
        return sifraVozila;
    }

    public void setSifraVozila(int sifraVozila) {
        this.sifraVozila = sifraVozila;
    }

    public String getRegBroj() {
        return regBroj;
    }

    public void setRegBroj(String regBroj) {
        this.regBroj = regBroj;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    @Override
    public String toString() {
        return regBroj;
    }
    
    
    
}
