package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ervan on 16/09/16.
 */
public interface IModel {


    public Item getItems(int i);
    public void addItem(Item i);
    public String getTitre(Item i);
    public void removeItem(Item i);
    public List<Item> getItems();



}
