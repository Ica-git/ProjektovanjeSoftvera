/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import java.util.HashMap;
import java.util.Map;
import transfer.ServerskiOdgovor;

/**
 *
 * @author USER
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;
    HashMap<String, String> mapa;

    private Kontroler() {
        dbb = new DBBroker();
        mapa = new HashMap<>();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ServerskiOdgovor vratiJunaka(String ime) {
        ServerskiOdgovor so = new ServerskiOdgovor();
        String junak = mapa.get(ime);
        
        if (junak == null) {
            so.setOdgovor(false);
            so.setPoruka("Nema junaka sa tim imenkom");
        }else{
            so.setOdgovor(true);
            so.setPoruka("Junakovo pravo ime je " + junak);
        }
        return so;
    }

    public String vratiSve() {
        String nalepi = "";
        
        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            nalepi += entry.getKey() + " - " + entry.getValue() + System.lineSeparator();
        }
        return nalepi;
    }

    public ServerskiOdgovor sacuvajJunaka(String[] podaci) {
        mapa.put(podaci[0], podaci[1]);
        ServerskiOdgovor so = new ServerskiOdgovor();
        so.setOdgovor(true);
        so.setPoruka("Uspesno dodat junak "+ podaci[0]);
        
        return so;
    }

}
