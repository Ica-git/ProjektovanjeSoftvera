/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Radnik;
import domen.Ucinak;
import domen.VrstaPosla;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class DBBroker {

    public ArrayList<Object> vrati() {
        ArrayList<Object> lista = new ArrayList<>();
        String upit = "";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public boolean cuvajIzmeniBrisi() throws Exception {
        String naredba = "";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<VrstaPosla> vratiSveVrstePoslova() throws Exception{
        
        ArrayList<VrstaPosla> lista = new ArrayList<>();
        String upit = "SELECT * FROM vrstaposla ORDER BY Naziv DESC;";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
                int vrstaposlaID = rs.getInt("vrstaPoslaID");
                String naziv = rs.getString("Naziv");
                
                VrstaPosla vp = new VrstaPosla(vrstaposlaID, naziv);
                
                lista.add(vp);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }

        return lista;
        
    }

    public ArrayList<Radnik> vratiSveradnike() throws Exception {
        
        ArrayList<Radnik> lista = new ArrayList<>();
        String upit = "SELECT * FROM radnik ORDER BY Prezime ASC;";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                
                int radnikID = rs.getInt("RadnikID");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                String specijalizacija = rs.getString("Specijalizacija");
                
                Radnik r = new Radnik(radnikID, ime, prezime, specijalizacija);
                
                lista.add(r);

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }

        return lista;
        
    }

    public boolean sacuvajUcinke(ArrayList<Ucinak> lista) throws Exception {

        String naredba = "INSERT INTO Ucinak (BrojSati, Datum, VrstaPoslaID, RadnikID) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);
            
            for (Ucinak u : lista) {
                ps.setInt(1, u.getBrojSati());
                ps.setDate(2, new Date(u.getDatum().getTime()));
                ps.setInt(3, u.getVrstaPosla().getVrstaPoslaID());
                ps.setInt(4, u.getRadnik().getRadnikID());
                ps.executeUpdate();
            }
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
        
    }

}
