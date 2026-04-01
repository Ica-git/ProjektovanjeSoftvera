/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
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

    private final ServerskaForma serverskaForma;
    private Socket s;
    
    public ObradaKlijentskihZahteva(Socket s, ServerskaForma serverskaForma) {
        this.s = s;
        this.serverskaForma = serverskaForma;
    }

    @Override
    public void run() {
        while (true) {
            KlijentskiZahtev kz = primiZahtev();
            ServerskiOdgovor so = new ServerskiOdgovor();
            switch (kz.getOperacija()) {
                case Operacije.POVEZI_SE:
                    String korisnik = (String) kz.getParametar();
                    if (korisnik != null) {
                        korisnik = korisnik.trim();
                    }
                    
                    if (korisnik == null || korisnik.isEmpty()) {
                        so.setPoruka("Unesite korisnicko ime");
                        so.setOdgovor(false);
                    } else if (!Kontroler.getInstance().daLiJeIspravnoIme(korisnik)) {
                        so.setPoruka("Korisnik nema pravo pristupa sistemu");
                        so.setOdgovor(false);
                    } else if (Kontroler.getInstance().daLiJeUlogovan(korisnik)) {
                        so.setPoruka("Korisnik je vec pristupio sistemu!");
                        so.setOdgovor(false);
                    } else {
                        Kontroler.getInstance().dodajUlogovanogKorisnika(korisnik);
                        Kontroler.getInstance().dodajKorisnkia(s);
                        so.setPoruka("Uspesno ulogovan korisnik");
                        so.setOdgovor(true);
                    }
                    
                    break;
                    
                case Operacije.IZBACI:
                    
                    int pozicija = (int) kz.getParametar();
                    String poruka = serverskaForma.odradiPosaoKojiTreba(pozicija, kz.getKorisnickoIme());
                    so.setPoruka(poruka);
                    so.setOdgovor(true);
                    
                    
                    
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
