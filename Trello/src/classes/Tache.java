package classes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Date;
import java.util.Objects;


//Classe Tache

public abstract class Tache extends Composant<Tache> {


    //attributs
    static int nbTaches = 0;
    int idTache;
    private double duree; //durée de la tache en jours
    private LocalDate dateDebut; //date de début de la tache

    private Colonne colonneOrigine; //colonne d'origine de la tache

   // private ArrayList<Tache> antecedents; //liste des antécedents de la tache

    //constructeur
    public Tache(String desc, double duree, int jourDebut, int moisDebut ,int anneeDebut) {
        super(desc);
        this.duree = duree;
        this.dateDebut = LocalDate.of(anneeDebut, moisDebut, jourDebut);
        nbTaches ++;
        idTache = nbTaches;
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

    /**
     * Méthode tacheExiste le nom est utilisée
     * @param id id de la tache recherché
     * @return true si le nom est utilisé, false sinon
     */
    public boolean tacheExiste(int id){
        return this.idTache == id;
    }

    public void afficher() {
        System.out.println("\t\t- " + this.nom);
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }


    public void setColonneOrigine(Colonne colonneOrigine) {
        this.colonneOrigine = colonneOrigine;
    }

    public void setDate(LocalDate date){
        this.dateDebut=date;

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


    public double getDuree() {
        return duree;
    }

    /**
     * Méthode getDateDebut qui retourne la date de début de la tache
     * @return la date de début de la tache
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Méthode getDateFin qui retourne la date de fin de la tache
     * @return la date de fin de la tache
     */
    public  LocalDate getDateFin() {
        //on additionne la durée de la tache à la date de début, puis on soustrait 1 jour pour avoir la date de fin
        return dateDebut.plusDays((long) duree-1);
    }


    public Colonne getColonneOrigine() {
        return colonneOrigine;
    }

    public int getId() {
        return idTache;

    }

    public void reinitialiser(){}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        Tache tache = (Tache) o;
        return tache.idTache==this.idTache;
    }



}
