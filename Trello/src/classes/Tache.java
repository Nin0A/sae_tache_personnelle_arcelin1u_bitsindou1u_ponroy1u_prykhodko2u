package classes;

import java.util.ArrayList;

public abstract class Tache extends Composant {
    private double duree;

    Tache(String desc, int duree) {
        super(desc);
        this.duree = duree;
    }
    private ArrayList<TacheMere> antecedents;
    public void  ajouterAntecedent(TacheMere t){
        this.antecedents.add(t);
    }
    public void supprimerAntecedent(TacheMere t){
        this.antecedents.remove(t);
    }
    @Override
    public String toString() {
        return this.nom + " "+duree+ " ";
    }
}
