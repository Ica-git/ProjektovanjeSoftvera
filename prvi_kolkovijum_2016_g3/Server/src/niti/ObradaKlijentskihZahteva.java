/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import domen.Radnik;
import domen.Ucinak;
import domen.VrstaPosla;
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
                
                case Operacije.VRATI_SVE_VRSTE_POSLOVA:
                    try {
                        ArrayList<VrstaPosla> lista = Kontroler.getInstance().vratiSveVrstePoslova();
                        so.setOdgovor(lista);
                    
                } catch (Exception e) {
                    so.setOdgovor(null);
                    so.setPoruka(e.getMessage());
                }break;
                
                case Operacije.VRATI_SVE_RADNIKE:
                    try {
                        ArrayList<Radnik> lista = Kontroler.getInstance().vratiSveRadnike();
                        so.setOdgovor(lista);
                        
                } catch (Exception e) {
                    so.setOdgovor(null);
                    so.setPoruka(e.getMessage());
                }break;
                
                case Operacije.SACUVAJ_UCINAK:
                    try {
                        ArrayList<Ucinak> lista = (ArrayList<Ucinak>) kz.getParametar();
                        boolean uspesno = Kontroler.getInstance().sacuvajUcinke(lista);
                        so.setOdgovor(uspesno);
                } catch (Exception e) {
                    so.setOdgovor(false);
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
