package classes;

import java.util.ArrayList;

public class TacheMere extends Tache {
    private ArrayList<Tache> sousTaches;

    TacheMere(String desc, int duree) {
        super(desc,duree);
    }
    public void ajouterSousTache(Tache t){
        this.sousTaches.add(t);
    }
    public void supprimerSousTache(Tache t){
        this.sousTaches.remove(t);
    }

}
