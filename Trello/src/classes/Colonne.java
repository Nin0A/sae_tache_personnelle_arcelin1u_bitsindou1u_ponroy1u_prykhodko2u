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
    public void modifierDureeTache(int duree, String nomTache){
        for (Composant c : this.liste){
            if (c.nom.equals(nomTache)){

            }
        }
    }


    public ArrayList<Tache> getTaches() {
        return liste;
    }

}
