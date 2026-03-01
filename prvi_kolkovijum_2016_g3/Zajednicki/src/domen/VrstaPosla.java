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
public class VrstaPosla implements Serializable{
    
    private int vrstaPoslaID;
    private String naziv;

    public VrstaPosla() {
    }

    public VrstaPosla(int vrstaPoslaID, String naziv) {
        this.vrstaPoslaID = vrstaPoslaID;
        this.naziv = naziv;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getVrstaPoslaID() {
        return vrstaPoslaID;
    }

    public void setVrstaPoslaID(int vrstaPoslaID) {
        this.vrstaPoslaID = vrstaPoslaID;
    }

    @Override
    public String toString() {
        return naziv;
    }
    
    
    
}
