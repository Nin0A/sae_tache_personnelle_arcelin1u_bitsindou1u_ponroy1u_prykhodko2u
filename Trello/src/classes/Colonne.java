package classes;

import java.util.ArrayList;

//Classe Colonne
public class Colonne extends Composant<Tache> {

    //constructeur
    public Colonne(String n) {
        super(n);
    }

    /**
     * Méthode ajouterTache qui ajoute une tache à la liste de taches de la colonne
     * @param t tache à ajouter à la liste
     */
    public void ajouterTache(Tache t){
        this.liste.add(t);
    }

    /**
     * Méthode supprimerTache qui supprimer une tache à la liste de taches de la colonne
     * @param t tache à ajouter à la liste
     */
    public void supprimerTache(Tache t){

        // On vérifie si la tache est une TacheMere pour supprimer ses sous-taches si il n'y en a
        if (t instanceof TacheMere){
            TacheMere tM = (TacheMere) t;

            // On récupère la liste des sous-taches de la tache mère
            // On les supprime une par une de la colonne
            for (Tache sousTache : tM.getSousTaches()){
                ((TacheMere) t).supprimerSousTache(sousTache);
            }
        }

        // On supprime la tache de la liste de taches de la colonne
        this.liste.remove(t);
        //supprimer les dépendances de la tache supprimée
        for (Tache tache : this.liste){
            if (tache.avoirAntecedent(t)){
                tache.supprimerAntecedent(t);
            }
        }
    }

    /**
     * Méthode modifierDureeTache qui modifie la durée d'une tache
     * @param duree durée de la tache en jours
     * @param nomTache nom de la tache à modifier
     */
    public void modifierDureeTache(int duree, String nomTache){
        for (Composant c : this.liste){
            if (c.nom.equals(nomTache)){

            }
        }
    }

    /**
     * Méthode getTaches qui retourne la liste de toutes les tâches de la colonne this
     * @return la liste de taches de la colonne
     */
    public ArrayList<Tache> getTaches() {
        return liste;
    }

}
