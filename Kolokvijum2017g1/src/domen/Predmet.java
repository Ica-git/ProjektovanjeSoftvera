/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Predmet {
    
    private int predmetID;
    private String naziv;
    private String problem;
    private Date datum;
    private Advokat advokat;
    private Klijent k;
    private vrstaPostupka vp;

    public Predmet() {
    }

    public Predmet(int predmetID, String naziv, String problem, Date datum, Advokat advokat, Klijent k, vrstaPostupka vp) {
        this.predmetID = predmetID;
        this.naziv = naziv;
        this.problem = problem;
        this.datum = datum;
        this.advokat = advokat;
        this.k = k;
        this.vp = vp;
    }

    public vrstaPostupka getVp() {
        return vp;
    }

    public void setVp(vrstaPostupka vp) {
        this.vp = vp;
    }

    public int getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(int predmetID) {
        this.predmetID = predmetID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Advokat getAdvokat() {
        return advokat;
    }

    public void setAdvokat(Advokat advokat) {
        this.advokat = advokat;
    }

    public Klijent getK() {
        return k;
    }

    public void setK(Klijent k) {
        this.k = k;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
    
}
