package Model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.Observable;

/**
 * Created by ervan on 21/09/16.
 */
public class TableCamembert extends AbstractTableModel {

    private String titre[] = {"Titre", "Description", "Montant"};
    private Model m;


    public TableCamembert (Model m){
        super();
        this.m = m;
    }

    @Override
    public int getRowCount() {
        return m.getItem().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1){
            case 0:return m.getItem().get(i).getTitre();
            case 1:return m.getItem().get(i).getDescription();
            case 2:return m.getItem().get(i).getValeur();
        }
        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        switch (i1){
            case 0:m.getItem().get(i).setTitre(o.toString());break;
            case 1:m.getItem().get(i).setDescription(o.toString());break;
            case 2:m.getItem().get(i).setValeur(Integer.parseInt(o.toString()));break;
        }
    }

    @Override
    public String getColumnName(int i) {
        return titre[i];
    }

    @Override
    public boolean isCellEditable(int i, int i1) {
        return true;
    }


}
