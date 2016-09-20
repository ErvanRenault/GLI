import Controlleur.Controlleur;
import Controlleur.IControlleur;
import Model.Adapter;
import Model.Model;
import Model.IModel;
import View.Vue;
import Model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;




public class Main {

    private static String titreAppli = "Camembert Interactif Application";
    public static void main(String[] args) {


        List<Item> list = new ArrayList<Item>();

        Item i1 = new Item("Loyer", "Mensuel", 476.55); list.add(i1);
        Item i2 = new Item("FF14", "Abonnement", 10); list.add(i2);
        Item i3 = new Item("Femmes de joies", "Loisir", 200); list.add(i3);

        Model model     = new Model(list);
        IModel adapter  = new Adapter(model);
        IControlleur ctr = new Controlleur();
        Vue vue         = new Vue(adapter, ctr);


        JFrame jf = new JFrame();
        jf.setTitle(titreAppli);
        jf.getContentPane().add(vue);
        jf.setSize(new Dimension(800, 600));



        //Fermeture de l'application + fermeture programme
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setResizable(false);
        jf.setVisible(true);

        /**jf.getContentPane().add(new Vue());
        jf.setSize(300, 200);
        jf.setVisible(true);**/

    }
}
