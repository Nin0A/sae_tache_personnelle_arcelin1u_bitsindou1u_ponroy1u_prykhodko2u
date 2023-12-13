package classes;

import java.util.ArrayList;

//Classe TacheMere

public class TacheMere extends Tache {

    //attributs
    private ArrayList<Tache> sousTaches; //liste de sous-taches

    //constructeur
    TacheMere(String desc, int duree) {
        super(desc,duree);
    }

    /**
     * Méthode ajouterSousTache qui ajoute une tache à la liste de sous-taches
     * @param t tache à ajouter à la liste de sous-tache
     */
    public void ajouterSousTache(Tache t){
        this.sousTaches.add(t);
    }

    /**
     * Méthode supprimerSousTache qui supprimer une tache de la liste de sous-taches
     * @param t tache à supprimer de la liste de sous-tache
     */
    public void supprimerSousTache(Tache t){
        this.sousTaches.remove(t);
    }

}
