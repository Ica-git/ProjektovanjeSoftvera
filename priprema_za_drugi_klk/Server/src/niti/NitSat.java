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
    
    JLabel labela;

    public NitSat(JLabel labela) {
        this.labela = labela;
    }
    
    
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

    @Override
    public void run() {
        while (true) {            
            labela.setText(sdf.format(new Date()));
        }
    }
    
    
    
}
