/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;

/**
 *
 * @author PC
 */
public class NitSat extends Thread{
    
    JLabel labelaSat;

    public NitSat(JLabel labelaSat) {
        this.labelaSat = labelaSat;
    }

    @Override
    public void run() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while (true) {            
            labelaSat.setText("Sada je: " + sdf.format(new Date()));
        }
        
    }
    
    
    
    
}
