/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package niti;

import forme.ServerskaForma;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author USER
 */
public class PokreniServer extends Thread {
    
    private ServerSocket serverskiSoket;
    
    private final ServerskaForma serverskaForma;
    
    public PokreniServer(ServerskaForma serverskaForma) {
        this.serverskaForma = serverskaForma;
    }
    
    
    public boolean pokreniAkoMoze() {
        try {
            serverskiSoket = new ServerSocket(9000);
            this.start();
            return true;
        } catch (IOException | IllegalThreadStateException ex) {
            return false;
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Cekanje klijenta...");
                Socket s = serverskiSoket.accept();
                System.out.println("Klijent se povezao!");
                ObradaKlijentskihZahteva nit = new ObradaKlijentskihZahteva(s, serverskaForma);
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

}
