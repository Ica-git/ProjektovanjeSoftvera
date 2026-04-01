/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logika;

import baza.DBBroker;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author USER
 */
public class Kontroler {

    private static Kontroler instance;
    private DBBroker dbb;
    
    ArrayList<String> listaMogucih = new ArrayList<>();
    String[] nizMogucih = {"admin1", "admin2", "user1", "user2"};
    ArrayList<String> ulogovani = new ArrayList<>();
    ArrayList<Socket> listaKorisnika = new ArrayList<>();
    

    private Kontroler() {
        
        for (int i = 0; i < nizMogucih.length; i++) {
            listaMogucih.add(nizMogucih[i]);
        }
        
        dbb = new DBBroker();
    }

    public static Kontroler getInstance() {
        if (instance == null) {
            instance = new Kontroler();
        }
        return instance;
    }
    
    public void dodajKorisnkia(Socket s){
        listaKorisnika.add(s);
    }

    public ArrayList<Socket> getListaKorisnika() {
        return listaKorisnika;
    }
    
    public void dodajUlogovanogKorisnika(String korisnickoIme){
        ulogovani.add(korisnickoIme);
    }
    
    public boolean daLiJeUlogovan(String korisnickoIme){
        return ulogovani.contains(korisnickoIme);
    }

    public ArrayList<String> getListaMogucih() {
        return listaMogucih;
    }

    public ArrayList<String> getUlogovani() {
        return ulogovani;
    }
    
    public boolean daLiJeIspravnoIme(String korisnickoIme){
        return listaMogucih.contains(korisnickoIme);
    }
    

}
