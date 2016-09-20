package Model;

/**
 * Created by ervan on 16/09/16.
 */
public class Item {

    private String titre;
    private String description;
    private double valeur;


    public Item(String titre, String description, double valeur) {
        this.titre = titre;
        this.description = description;
        this.valeur = valeur;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }

    public String toString(){
        String ret ="";
        ret +=  "La portion "+ this.titre +", à pour valeur : "+ this.valeur+ ". Description: "+ this.description;
        return ret;
    }

}
