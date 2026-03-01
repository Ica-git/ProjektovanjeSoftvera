/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

/**
 *
 * @author PC
 */
public class vrstaPostupka {
    
    private int vrstaPostupkaID;
    private String naziv;

    public vrstaPostupka() {
    }

    public vrstaPostupka(int vrstaPostupkaID, String naziv) {
        this.vrstaPostupkaID = vrstaPostupkaID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrstaPostupkaID() {
        return vrstaPostupkaID;
    }

    public void setVrstaPostupkaID(int vrstaPostupkaID) {
        this.vrstaPostupkaID = vrstaPostupkaID;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
