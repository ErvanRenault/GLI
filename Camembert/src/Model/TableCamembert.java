package Model;

import Controlleur.IControlleur;

import javax.swing.table.AbstractTableModel;

/**
 * Created by ervan on 21/09/16.
 */
public class TableCamembert extends AbstractTableModel {

    private String titre[] = {"Titre", "Description", "Montant"};
    private Model m;
    private IControlleur ctr;

    public TableCamembert (Model m, IControlleur ctr){
        super();
        this.m = m;
        this.ctr = ctr;
    }

    @Override
    public int getRowCount() {
        return m.getItems().size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1){
            case 0:return m.getItems().get(i).getTitre();
            case 1:return m.getItems().get(i).getDescription();
            case 2:return m.getItems().get(i).getValeur();
        }

        return null;
    }

    @Override
    public void setValueAt(Object o, int i, int i1) {
        switch (i1){
            case 0:m.getItems().get(i).setTitre(o.toString());break;
            case 1:m.getItems().get(i).setDescription(o.toString());break;
            case 2:m.getItems().get(i).setValeur(Integer.parseInt(o.toString()));break;
        }
        ctr.update();
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
