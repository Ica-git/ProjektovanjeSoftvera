/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modeli;

import domen.Osiguranje;
import domen.VrstaOsiguranja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModelTabelaOsiguranja extends AbstractTableModel {

    ArrayList<Osiguranje> lista;
    String[] kolone = {"Vozilo", "Ime Prezime", "Ukupna premija", "Pocetak vazenja osiguranja"};
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public ModelTabelaOsiguranja() {
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
        Osiguranje o = lista.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return o.getVozilo();
            case 1:
                return o.getImePrezime();
            case 2:
                return o.getUkupnaPremija();
            case 3:
                return sdf.format(o.getDatumPocetka());

            default:
                return "return!";
        }
    }

    public ArrayList<Osiguranje> getLista() {
        return lista;
    }
    
    public void dodaj(Osiguranje vo){
        if (!lista.contains(vo)) {
                lista.add(vo);
                fireTableDataChanged();
        }
        
    }

    public void obrisi(int red) {
        lista.remove(red);
        fireTableDataChanged();
    }

}
