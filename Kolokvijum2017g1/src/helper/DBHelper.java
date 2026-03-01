/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author PC
 */
public class DBHelper {
    
    Properties property;

    public DBHelper() throws FileNotFoundException, IOException {
        property = new Properties();
        FileInputStream fis = new FileInputStream("db.conf");
        property.load(fis);
    }
    
    public String vratiVrednost(String kljuc){
        return property.getProperty(kljuc);
    }
    
    
}
