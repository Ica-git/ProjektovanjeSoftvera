/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import domen.Osiguranje;
import domen.Vozilo;
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
    
    public ArrayList<Vozilo> vratiSvaVozila() throws Exception {
        ArrayList<Vozilo> lista = new ArrayList<>();
        String upit = "SELECT * FROM vozilo";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {
                int sifra = rs.getInt("sifraVozila");
                String regBroj = rs.getString("regBroj");
                int god = rs.getInt("godinaProizvodnje");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                lista.add(new Vozilo(sifra, regBroj, god, ime, prezime));
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }

        return lista;
    }
    
    
    public boolean sacuvajOsiguranje(Osiguranje o) throws Exception {
        String naredba = "INSERT INTO osiguranje (sifraVozila, datumPocetka, datumUnosa, imePrezime, ukupnaPremija) "
                + "VALUES (?,?,?,?,?)";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(naredba);
            
            ps.setInt(1, o.getVozilo().getSifraVozila());
            ps.setDate(2, new Date(o.getDatumPocetka().getTime()));
            ps.setDate(3, new Date(o.getDatumUnosa().getTime()));
            ps.setString(4, o.getImePrezime());
            ps.setDouble(5, o.getUkupnaPremija());

            ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();

            return true;

        } catch (SQLException ex) {
            Konekcija.getInstance().getConnection().rollback();
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
    }
    
    public ArrayList<Osiguranje> vratiSvaOsiguranja(int sifraVozila) throws Exception {
        ArrayList<Osiguranje> lista = new ArrayList<>();
        String upit = "SELECT o.osiguranjeID, o.sifraVozila, o.datumPocetka, o.datumUnosa, o.imePrezime, o.ukupnaPremija, "
                + "v.regBroj, v.godinaProizvodnje, v.ime, v.prezime "
                + "FROM osiguranje o "
                + "JOIN vozilo v ON o.sifraVozila = v.sifraVozila "
                + "WHERE v.sifraVozila = ? "
                + "ORDER BY o.datumPocetka DESC";
        try {
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            ps.setInt(1, sifraVozila);   // umesto: ... = " + vozilo.getSifraVozila()
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int oid = rs.getInt("osiguranjeID");
                int sifra = rs.getInt("sifraVozila");
                String reg = rs.getString("regBroj");
                int god = rs.getInt("godinaProizvodnje");
                String ime = rs.getString("ime");
                String prez = rs.getString("prezime");
                Vozilo v = new Vozilo(sifra, reg, god, ime, prez);
                java.util.Date dp = rs.getDate("datumPocetka");
                java.util.Date du = rs.getDate("datumUnosa");
                String ip = rs.getString("imePrezime");
                double prem = rs.getDouble("ukupnaPremija");
                lista.add(new Osiguranje(oid, v, dp, du, ip, prem));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            throw new Exception(ex.getMessage());
        }
        return lista;
    }
    
    

}
