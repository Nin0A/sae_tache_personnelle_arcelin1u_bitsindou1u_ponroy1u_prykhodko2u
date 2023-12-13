package classes;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Tache extends Composant<Tache> {

    //constructeurs
    private double duree; //durée de la tache
    private ArrayList<Tache> antecedents; //liste des antécedents de la tache

    /**
     * Constructeur
     * @param desc nom de la tache cree
     * @param duree duree de la tache cree
     */
    Tache(String desc, int duree) {
        super(desc);
        this.duree = duree;
        this.liste = new ArrayList<Tache>();
    }


    /**
     * Méthode ajouterAntecedent qui va permettre d'ajouter une tache
     * à la liste des antécedents
     * @param t la tache antécédente à ajouter à la liste
     */
    public void  ajouterAntecedent(Tache t){
        this.antecedents.add(t);
    }

    /**
     * Méthode supprimerAntecedent qui va permettre de supprimer une tache
     * à la liste des antécedents
     * @param t la tache antécédente à supprimer de la liste
     */
    public void supprimerAntecedent(Tache t){
        this.antecedents.remove(t);
    }

    /**
     * Méthide toString qui affiche le nom de la tache et sa duree
     * @return l'affichage
     */
    @Override
    public String toString() {
        return this.nom + " "+duree+ " ";
    }

}
