package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ervan on 16/09/16.
 */
public class Model{

    private String titre;
    List<Item> lItem = new ArrayList<Item>();



    public Model(List<Item> lItem) {
        this.lItem = lItem;
    }

    public Item getItem(int i){
        return this.lItem.get(i);
    }
    public List<Item> getItem() { return lItem;}


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
