/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import domen.Radnik;
import domen.Ucinak;
import domen.VrstaPosla;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;

    private Kontroler() {
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }

    public ArrayList<VrstaPosla> vratiSveVrstePoslova() throws Exception{
        return dbb.vratiSveVrstePoslova();
    }

    public ArrayList<Radnik> vratiSveRadnike() throws Exception {
        return dbb.vratiSveradnike();
    }

    public boolean sacuvajUcinke(ArrayList<Ucinak> lista) throws Exception {
        return dbb.sacuvajUcinke(lista);
    }

}
