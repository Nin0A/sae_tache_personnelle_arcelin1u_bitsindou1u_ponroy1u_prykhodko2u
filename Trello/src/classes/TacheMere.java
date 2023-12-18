package classes;

import java.util.ArrayList;

//Classe TacheMere

public class TacheMere extends Tache {

    //attributs
    private ArrayList<Tache> sousTaches = new ArrayList<>(); //liste de sous-taches

    //constructeur
    public TacheMere(String desc, int duree) {
        super(desc,duree);
    }

    /**
     * Méthode ajouterSousTache qui ajoute une tache à la liste de sous-taches
     * @param t tache à ajouter à la liste de sous-tache
     */
    public void ajouterSousTache(Tache t){
        //on vérifie que la sous tache n'est pas déjà dans la liste
        if (!this.sousTaches.contains(t)&& t!=null) {
            this.sousTaches.add(t);
        }
    }

    /**
     * Méthode supprimerSousTache qui supprimer une tache de la liste de sous-taches
     * @param t tache à supprimer de la liste de sous-tache
     */
    public void supprimerSousTache(Tache t){
        this.sousTaches.remove(t);
    }

    /**
     * Méthode getSousTaches qui retourne la liste de toutes les sous-tâches de la tache
     * @return la liste de sous-taches de la tache
     */
    public ArrayList<Tache> getSousTaches() {
        return sousTaches;
    }

    @Override
    public void afficher() {
        super.afficher();
        for (Tache t : sousTaches) {
            System.out.print("\t");
            t.afficher();
        }
    }
}
