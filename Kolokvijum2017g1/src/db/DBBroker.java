/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import domen.Klijent;
import helper.DBHelper;
import helper.Konstante;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class DBBroker {
    
    java.sql.Connection konekcija;
    DBHelper dbHelper;

    public DBBroker() {
        try {
            dbHelper = new DBHelper();
        } catch (IOException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    public void ucitajDriver() throws ClassNotFoundException{
        Class.forName(dbHelper.vratiVrednost(Konstante.DRIVER));
    }
    
    public void otvoriKonekciju() throws SQLException{
        String url = dbHelper.vratiVrednost(Konstante.URL);
        String username = dbHelper.vratiVrednost(Konstante.USERNAME);
        String password = dbHelper.vratiVrednost(Konstante.PASSWORD);
        
        konekcija = DriverManager.getConnection(url,username,password);
        
        konekcija.setAutoCommit(false);
    }
    
    public void zatvoriKonekciju() throws SQLException{
        konekcija.close();
    }
    
    public void commit() throws SQLException{
        konekcija.commit();
    }
    
    public void rollback() throws SQLException{
        konekcija.rollback();
    }

    public ArrayList<Klijent> vratiKlijente() throws SQLException {
        
        ArrayList<Klijent> lista = new ArrayList<>();
        String upit = "SELECT * FROM klijent ORDER BY Prezime ASC;";
        Statement s = konekcija.createStatement();
        
        ResultSet rs = s.executeQuery(upit);
        
        while (rs.next()) {            
            int klijentID = rs.getInt("KlijentID");
            String ime = rs.getString("Ime");
            String prezime = rs.getString("Prezime");
            String telefon = rs.getString("Telefon");
            String elPosta = rs.getString("ElPosta");
            String adresa = rs.getString("Adresa");
            
            Klijent k = new Klijent(klijentID, ime, prezime, telefon, elPosta, adresa);
            lista.add(k);
        }
        rs.close();
        s.close();
        
        return lista;
    }
    
    
    
}
