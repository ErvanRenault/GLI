package Controlleur;

import View.Vue;

import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by ervan on 16/09/16.
 */
public class Controlleur implements IControlleur {

    private Vue vue;


    public Controlleur(){

    }
    public Controlleur(Vue vue){
        this.vue = vue;
    }

    @Override
    public void addItem() {

    }

    @Override
    public void removeItem(int i) {

        vue.model.getItems().remove(i); update();

    }

    @Override
    public void inClick(Shape shape, int indice) {

        Arc2D arc = (Arc2D) shape;
        vue.shapes[indice] = new Arc2D.Double(50, 100, 300, 300, arc.getAngleStart(), arc.getAngleExtent(), Arc2D.PIE);

        vue.valeur = vue.model.getItems(indice).getValeur();
        vue.mTexte ="Description: " + vue.model.getItems(indice).getDescription();
        vue.titre  ="Titre: "+  vue.model.getItems(indice).getTitre();

    }

     public void update(){


         vue.init();
         vue.initCamembert();
         vue.valeur = 0;
         vue.mTexte ="Selectionnez une portion !";
         vue.titre  ="";
         vue.repaint();


     }
     public void setVue(Vue vue){
         this.vue = vue;
     }


}
