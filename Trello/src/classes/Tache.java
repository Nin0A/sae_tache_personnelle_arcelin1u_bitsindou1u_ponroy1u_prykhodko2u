package classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

//Classe Tache

public abstract class Tache extends Composant<Tache> {

    //attributs
    private double duree; //durée de la tache en jours
    private Date dateDebut; //date de début de la tache

   // private ArrayList<Tache> antecedents; //liste des antécedents de la tache

    //constructeur
    public Tache(String desc, int duree) {
        super(desc);
        this.duree = duree;
        //this.antecedents = new ArrayList<Tache>();
    }


    /**
     * Méthode ajouterAntecedent qui va permettre d'ajouter une tache
     * à la liste des antécedents
     * @param t la tache antécédente à ajouter à la liste
     */
    public void ajouterAntecedent(Tache t){
        //on vérifie que la tache n'est pas déjà dans la liste
        if (!this.liste.contains(t)) {
            this.liste.add(t);
        }
    }

    /**
     * Méthode supprimerAntecedent qui va permettre de supprimer une tache
     * à la liste des antécedents
     * @param t la tache antécédente à supprimer de la liste
     */
    public void supprimerAntecedent(Tache t){
        this.liste.remove(t);
    }

    /**
     * Méthode getAntecedents qui retourne la liste des antécedents d'une tache
     * @return la liste des antécedents
     */
    public ArrayList<Tache> getAntecedent() {
        return liste;
    }

    /**
     * Méthode toString qui affiche le nom de la tache et sa duree
     * @return l'affichage
     */
    @Override
    public String toString() {
        return this.nom + " "+duree+ " ";
    }
    public boolean avoirAntecedent(Tache t){
        return this.liste.contains(t);
    }

    public void afficher() {
        System.out.println("\t\t- " + this.nom);
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }

    public void ajoutSupprAntecedent(Tache t){
        if (etreAntecedent(t))
            liste.remove(t);
        else
            liste.add(t);
    }

    public boolean etreAntecedent(Tache t){
        boolean res = false;
        for (Tache tache : this.liste ) {
            if (tache.equals(t)){
                res = true;
                break;
            }
        }
        return res;

    }

    public void reinitialiser(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tache tache = (Tache) o;
        return tache.nom.equals(this.nom);
    }

}
