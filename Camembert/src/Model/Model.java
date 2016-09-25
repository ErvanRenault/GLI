package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by ervan on 16/09/16.
 */
public class Model extends Observable implements  IModel{

    private String titre;
    List<Item> lItem = new ArrayList<Item>();


    public Model(List<Item> lItem) {
        this.lItem = lItem;
    }

    public Item getItems(int i){
        return this.lItem.get(i);
    }
    public List<Item> getItems() { return lItem;}


    public void addItem(Item i){
        lItem.add(i);
    }

    public String getTitre(Item i){
        return this.titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void removeItem(Item i){
        this.lItem.remove(i);
    }


}
