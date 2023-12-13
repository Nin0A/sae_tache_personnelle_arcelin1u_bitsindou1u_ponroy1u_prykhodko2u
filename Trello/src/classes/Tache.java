package classes;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Tache extends Composant {
    private double duree;

    Tache(String desc, int duree) {
        super(desc);
        this.duree = duree;
        this.liste = new ArrayList<Tache>();
    }

    public void  ajouterAntecedent(TacheMere t){
        this.liste.add(t);
    }
    public void supprimerAntecedent(TacheMere t){
        this.liste.remove(t);
    }
    @Override
    public String toString() {
        return this.nom + " "+duree+ " ";
    }

}
