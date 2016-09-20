import Controlleur.Controlleur;
import Controlleur.IControlleur;
import Model.Adapter;
import Model.Model;
import Model.IModel;
import View.Vue;
import Model.Item;
import Model.TableCamembert;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        TableCamembert tc = new TableCamembert(model);
        IModel adapter = new Adapter(model);
        IControlleur ctr = new Controlleur();
        Vue vue = new Vue(adapter, ctr);
        ctr.setVue(vue);




        JFrame jf = new JFrame();
        JButton button = new JButton();
        JTable tableau = new JTable(tc);
        JPanel camPart = new JPanel();
        JPanel tabPart = new JPanel();
        camPart.add(vue);


        jf.add(camPart);
        jf.setTitle(titreAppli);
        jf.getContentPane().add(vue);
        button.setText("Add");

        tabPart.add(new JScrollPane(tableau));
        tabPart.add(button, BorderLayout.SOUTH);
        jf.add((tabPart), BorderLayout.EAST);
        jf.setSize(new Dimension(1200, 900));



        // Bouton pour ajout d'une donnée
        /** JButton addData = new JButton();
         addData.setText("Add");
         addData.setPreferredSize(new Dimension(70,0));
         jf.getContentPane().add(addData, BorderLayout.EAST);**/


        //Fermeture de l'application + fermeture programme
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setResizable(false);
        jf.setVisible(true);

    }
}
