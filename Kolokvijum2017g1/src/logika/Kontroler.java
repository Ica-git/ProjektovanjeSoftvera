/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logika;

import db.DBBroker;
import domen.Advokat;
import domen.Klijent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Kontroler {
    
    private static Kontroler instanca;
    ArrayList<Advokat> listaAdvokata;
    DBBroker db;

    public Kontroler() {
        
        db = new DBBroker();
        listaAdvokata = new ArrayList<>();
        
        Advokat a = new Advokat("Ica", "Smekerica", 
                "ivanika", "curpekpro99", "Vanparicni postupak");
        Advokat a1 = new Advokat("Neda", "Rados", 
                "nedanecki", "neda123", "Izvrsni postupak");
        Advokat a2 = new Advokat("Gile", "Gankster", 
                "gile", "gile24", "Upravni Spor");
        
        listaAdvokata.add(a);
        listaAdvokata.add(a1);
        listaAdvokata.add(a2);
        
    }

    public static Kontroler getInstanca() {
        if (instanca == null) {
            instanca = new Kontroler();
        }
        return instanca;
    }

    public Advokat proveriAdvokata(String username, String password) {
        
        for (Advokat advokat : listaAdvokata) {
            if (advokat.getUsername().equals(username) &&
                    advokat.getPassword().equals(password)) {
                return advokat;
            }
        }
        return null;
    }

    public ArrayList<Klijent> dajMiListuKlijenata() {
        
        ArrayList<Klijent> lista = new ArrayList<>();
        
        try {
            db.ucitajDriver();
            db.otvoriKonekciju();
            
            lista = db.vratiKlijente();
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            try {
                db.zatvoriKonekciju();
            } catch (SQLException ex) {
                Logger.getLogger(Kontroler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }
    
    
    
    
}
