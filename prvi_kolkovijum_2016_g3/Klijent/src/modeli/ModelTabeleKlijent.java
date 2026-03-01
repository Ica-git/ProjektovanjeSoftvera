/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Ucinak;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleKlijent extends AbstractTableModel {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    ArrayList<Ucinak> lista;
    String[] kolone = {"Vrsta posla", "Ime i prezime", "Broj sati", "Datum"};

    public ModelTabeleKlijent() {
        lista = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return kolone[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ucinak u = lista.get(rowIndex);

        switch (columnIndex) {
            case 0: return u.getVrstaPosla();
            case 1: return u.getRadnik();
            case 2: return u.getBrojSati();
            case 3: return sdf.format(u.getDatum());

            default:
                return "return!";
        }
    }

    public void dodajUcinak(Ucinak u) {
        lista.add(u);
        fireTableDataChanged();
    }

    public void obrisi(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

    public ArrayList<Ucinak> getLista() {
        return lista;
    }
    
    

}
