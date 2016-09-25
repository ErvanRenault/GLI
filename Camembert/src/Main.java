import Controlleur.Controlleur;
import Controlleur.IControlleur;
import Model.Adapter;
import Model.Model;
import Model.IModel;
import View.Vue;
import Model.Item;
import Model.TableCamembert;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static String titreAppli = "Camembert Interactif Application";
    public static void main(String[] args) {

        List<Item> list = new ArrayList<Item>();

        Item i1 = new Item("Loyer", "Mensuel", 476.55);
        list.add(i1);
        Item i2 = new Item("FF14", "Abonnement jeux video", 10);
        list.add(i2);
        Item i3 = new Item("Femmes de joies", "Loisir personel", 200);
        list.add(i3);
        Item i4 = new Item("Course", "Nourriture du mois", 80);
        list.add(i4);

        Model model = new Model(list);

        IModel adapter = new Adapter(model);
        IControlleur ctr = new Controlleur();
        Vue vue = new Vue(adapter, ctr);
        TableCamembert tc = new TableCamembert(model, ctr);
        ctr.setVue(vue);


        Border eBorder = BorderFactory.createEtchedBorder();


        JFrame jf = new JFrame();
        JButton button = new JButton();
        JButton button2 = new JButton("Remove");
        JTable tableau = new JTable(tc);
        JPanel tabPart = new JPanel();


        jf.setTitle(titreAppli);
        jf.getContentPane().add(vue);

        tabPart.add(new JScrollPane(tableau));
        tabPart.add(button, BorderLayout.SOUTH);
        tabPart.add(button2, BorderLayout.SOUTH);
        jf.add((tabPart), BorderLayout.EAST);
        jf.setSize(new Dimension(1200, 500));


        // Bouton pour ajout d'une donnée
        button.setText("Add");
        button.addActionListener(e -> {
            Item i = new Item("New Titre", "New Description", 100);
            model.addItem(i);
            vue.init();
            vue.initCamembert();
            tc.fireTableRowsInserted(model.getItems().size() - 1, model.getItems().size() - 1);
            vue.repaint();

        });


        button2.addActionListener(e -> {
            int[] selected = (tableau.getSelectedRows());
            for (int i = 0; i < selected.length; i++) {
                ctr.removeItem(selected[0]);
            }
            tc.fireTableRowsInserted(model.getItems().size() - 1, model.getItems().size() - 1);
            tableau.clearSelection();

        });

        //Fermeture de l'application + fermeture programme
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setResizable(false);
        jf.setVisible(true);


    }

}
