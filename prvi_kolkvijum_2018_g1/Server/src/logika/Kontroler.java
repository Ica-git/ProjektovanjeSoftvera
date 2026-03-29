/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Korisnik;
import domen.Osiguranje;
import domen.Vozilo;
import domen.VrstaOsiguranja;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Kontroler {
    
    ArrayList<Korisnik> listaKorisnika;
    ArrayList<VrstaOsiguranja> listaVrsteOsiguranja;

    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
        listaKorisnika = new ArrayList<>();
        listaKorisnika.add(new Korisnik("Ivan Radosavljevic", "ica", "ica"));
        listaKorisnika.add(new Korisnik("Gile Radosavljevic", "gica", "gica"));
        listaKorisnika.add(new Korisnik("Neda Radosavljevic", "neda", "neda"));
        
        listaVrsteOsiguranja = new ArrayList<>();
        listaVrsteOsiguranja.add(new VrstaOsiguranja(1, "Obavezno osiguranje", 100));
        listaVrsteOsiguranja.add(new VrstaOsiguranja(2, "Mini kasno osiguranje", 150));
        listaVrsteOsiguranja.add(new VrstaOsiguranja(3, "Kasko osiguranje", 200));
        listaVrsteOsiguranja.add(new VrstaOsiguranja(4, "Pomoc na putu - Republika Srbija", 250));
        listaVrsteOsiguranja.add(new VrstaOsiguranja(5, "Pomoc na putu - ostale drzave", 300));
        
        
        
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<Korisnik> getListaKorisnika() {
        return listaKorisnika;
    }
    
    public Korisnik login(Korisnik korisnikSaForme){
        for (Korisnik korisnik : listaKorisnika) {
            if (korisnik.equals(korisnikSaForme)) {
                return korisnik;
            }
        }
        return null;
    }
    
    public ArrayList<Vozilo> vratiSvaVozila() throws Exception{
        return dbb.vratiSvaVozila();
    }

    public ArrayList<VrstaOsiguranja> getListaVrsteOsiguranja() {
        return listaVrsteOsiguranja;
    }
    
    public boolean sacuvajOsiguranje(Osiguranje o) throws Exception{
        return dbb.sacuvajOsiguranje(o);
    }
    
    public ArrayList<Osiguranje> vratiSvaOsiguranja(int sifraVozila) throws Exception {
        return dbb.vratiSvaOsiguranja(sifraVozila);
    }
    
    

}
