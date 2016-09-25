package Model;

import java.util.List;

/**
 * Created by ervan on 16/09/16.
 */
public class Adapter implements IModel {


    private Model model;


    public Adapter(Model model) {
        this.model = model;
    }


    public Item getItems(int i){
        return model.getItems(i);
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

    public List<Item> getItems(){return model.getItems();}
}
