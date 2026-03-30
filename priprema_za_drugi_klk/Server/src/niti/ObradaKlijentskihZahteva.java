/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
            System.out.println("Operacija je: " + kz.getOperacija());
            switch (kz.getOperacija()) {
                case Operacije.UNESI_JUNAKA:
                    try {
                        String[] podaci = (String[]) kz.getParametar();
                        so = Kontroler.getInstance().sacuvajJunaka(podaci);
                    } catch (Exception e) {
                        so.setOdgovor(false);
                        so.setPoruka(e.getMessage());
                    }
                    break;
                case Operacije.VRATI_JUNAKA:
                    try {
                        String ime = (String) kz.getParametar();
                        so = Kontroler.getInstance().vratiJunaka(ime);
                    } catch (Exception e) {
                        so.setOdgovor(false);
                        so.setPoruka(e.getMessage());
                    }
                    break;
                case Operacije.VRATI_SVE:
                    try {
                        String tekst = Kontroler.getInstance().vratiSve();
                        so.setOdgovor(tekst);
                    } catch (Exception e) {
                        so.setOdgovor(null);
                        so.setPoruka(e.getMessage());
                    }
                    break;
                        

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
