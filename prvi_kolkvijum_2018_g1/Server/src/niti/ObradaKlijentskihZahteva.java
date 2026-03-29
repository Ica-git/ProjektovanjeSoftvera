/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Korisnik;
import domen.Osiguranje;
import domen.Vozilo;
import domen.VrstaOsiguranja;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import konstante.Operacije;
import logika.Kontroler;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author USER
 */
public class ObradaKlijentskihZahteva extends Thread {

    private Socket s;

    public ObradaKlijentskihZahteva(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.LOGIN:
                    try {
                        Korisnik kredencijali = (Korisnik) kz.getParametar();
                        Korisnik ulogovan = Kontroler.getInstance().login(kredencijali);
                        so.setOdgovor(ulogovan);
                        if (ulogovan == null) {
                            so.setPoruka("Pogresno korisnicko ime ili lozinka!");
                        }
                } catch (Exception e) {
                    so.setOdgovor(null);
                    so.setPoruka(e.getMessage());
                } break;
                case Operacije.VRATI_SVA_VOZILA:
                    try {
                        ArrayList<Vozilo> lista = Kontroler.getInstance().vratiSvaVozila();
                        so.setOdgovor(lista);
                } catch (Exception e) {
                    so.setOdgovor(null);
                    so.setPoruka(e.getMessage());
                }break;
                case Operacije.VRATI_SVA_OSIGURANJA:
                    try {
                        ArrayList<VrstaOsiguranja> lista = Kontroler.getInstance().getListaVrsteOsiguranja();
                        so.setOdgovor(lista);
                } catch (Exception e) {
                    so.setOdgovor(null);
                    so.setPoruka(e.getMessage());
                }break;
                case Operacije.SACUVAJ_OSIGURANJA:
                    try {
                        Osiguranje o = (Osiguranje) kz.getParametar();
                        boolean ok = Kontroler.getInstance().sacuvajOsiguranje(o);
                        so.setOdgovor(ok);
                } catch (Exception e) {
                    so.setOdgovor(false);
                    so.setPoruka(e.getMessage());
                }break;
                case Operacije.VRATI_SACUVANA_OSIGURANJA:
                    try {
                        Vozilo v = (Vozilo) kz.getParametar();
                        ArrayList<Osiguranje> lista = Kontroler.getInstance().vratiSvaOsiguranja(v.getSifraVozila());
                        so.setOdgovor(lista);
                    } catch (Exception e) {
                        so.setOdgovor(null);
                        so.setPoruka(e.getMessage());
                    }break;
                    

            }
            posaljiOdgovor(so);
        }
    }

    private KlijentskiZahtev primiZahtev() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (KlijentskiZahtev) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("KLIJENT SE ODVEZAO (UGASIO/LA SI KLIJENTSKU APLIKACIJU),"
                    + " ZATO SE DESIO OVAJ EXCEPTION, NE BRINI SE NISTA! ARI");
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void posaljiOdgovor(ServerskiOdgovor so) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(so);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
