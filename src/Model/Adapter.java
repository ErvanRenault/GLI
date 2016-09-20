package Model;

import Controlleur.Controlleur;
import View.Vue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ervan on 16/09/16.
 */
public class Adapter implements IModel {


    private Model model;


    public Adapter(Model model) {
        this.model = model;
    }


    public Item getItem(int i){
        return model.getItem(i);
    }

    public void addItem(Item i){
        model.addItem(i);
    }


    public String getTitre(Item i){
        return model.getTitre(i);
    }

    public void removeItem(Item i){
        model.removeItem(i);
    }

    public List<Item> getItem(){return model.getItem();}
}
