package Controlleur;

import View.Vue;

import java.awt.*;

/**
 * Created by ervan on 16/09/16.
 */
public interface IControlleur{

        public void addItem();
        public void removeItem();
        public void inClick(Shape shape, int indice);
        public void setVue(Vue vue);




}
