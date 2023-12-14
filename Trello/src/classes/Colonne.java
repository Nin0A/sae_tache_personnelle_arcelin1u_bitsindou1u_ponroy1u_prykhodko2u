package classes;

import java.util.ArrayList;

//Classe Colonne
public class Colonne extends Composant<Tache> {

    //constructeur
    Colonne(String n) {
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
        this.liste.remove(t);
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
