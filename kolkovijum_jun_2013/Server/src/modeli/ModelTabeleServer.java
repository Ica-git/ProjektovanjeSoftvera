/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import pomoc.TabelaKlasa;

public class ModelTabeleServer extends AbstractTableModel {

    
    public static final int BROJ_KOLONA = 10;
    
    ArrayList<TabelaKlasa> red;

    public ModelTabeleServer() {
        red = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return red.size() >= BROJ_KOLONA ? 1 : 0;
    }

    @Override
    public int getColumnCount() {
        return BROJ_KOLONA;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return String.valueOf(columnIndex + 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (rowIndex != 0 || columnIndex < 0 || columnIndex >= BROJ_KOLONA) {
            return null;
        }
        if (red.size() != BROJ_KOLONA) {
            return null;
        }
        return red.get(columnIndex).getBrojVrednost();
        
    }
    
    public void postaviRed(ArrayList<TabelaKlasa> noviRed) {
        red.clear();
        if (noviRed != null) {
            red.addAll(noviRed);
        }
        fireTableDataChanged();
    }
    
    public void postaviVrednostUKoloni(int indeksKolone, int novaVrednost) {
        if (red.size() != BROJ_KOLONA || indeksKolone < 0 || indeksKolone >= BROJ_KOLONA) {
            return;
        }
        red.get(indeksKolone).setBrojVrednost(novaVrednost);
        fireTableCellUpdated(0, indeksKolone);
    }
    
    

}
