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
public class Ucinak implements Serializable{
    
    private int ucinakID;
    private int brojSati;
    private Date datum;
    private Radnik radnik;
    private VrstaPosla vrstaPosla;

    public Ucinak() {
    }

    public Ucinak(int ucinakID, int brojSati, Date datum, Radnik radnik, VrstaPosla vrstaPosla) {
        this.ucinakID = ucinakID;
        this.brojSati = brojSati;
        this.datum = datum;
        this.radnik = radnik;
        this.vrstaPosla = vrstaPosla;
    }

    public VrstaPosla getVrstaPosla() {
        return vrstaPosla;
    }

    public void setVrstaPosla(VrstaPosla vrstaPosla) {
        this.vrstaPosla = vrstaPosla;
    }

    public int getUcinakID() {
        return ucinakID;
    }

    public void setUcinakID(int ucinakID) {
        this.ucinakID = ucinakID;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Radnik getRadnik() {
        return radnik;
    }

    public void setRadnik(Radnik radnik) {
        this.radnik = radnik;
    }
    
    
    
}
