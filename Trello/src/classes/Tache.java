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
    protected int idTache;
    protected double duree; //durée de la tache en jours
    protected LocalDate dateDebut; //date de début de la tache

    protected Colonne colonneOrigine; //colonne d'origine de la tache

   // private ArrayList<Tache> antecedents; //liste des antécedents de la tache

    //constructeur
    /**
     * Constructeur de Tache
     * @param desc description de la tache
     * @param colCourante colonne de la tache
     * @param duree durée de la tache
     * @param jourDebut jour de la tache
     * @param moisDebut mois de la tache
     * @param anneeDebut année de la tache
     */
    public Tache(String desc,Colonne colCourante, double duree, int jourDebut, int moisDebut ,int anneeDebut) {
        super(desc);
        this.duree = duree;
        this.dateDebut = LocalDate.of(anneeDebut, moisDebut, jourDebut);
        nbTaches ++;
        idTache = nbTaches;
        this.colonneOrigine = colCourante;
    }
    /**
     * Constructeur de Tache avec un id défini
     * @param desc description de la tache
     * @param colCourante colonne de la tache
     * @param duree durée de la tache
     * @param jourDebut jour de la tache
     * @param moisDebut mois de la tache
     * @param anneeDebut année de la tache
     * @param id id de la tache
     */
    public Tache(String desc,Colonne colCourante, double duree, int jourDebut, int moisDebut ,int anneeDebut,int id) {
        super(desc);
        this.duree = duree;
        this.dateDebut = LocalDate.of(anneeDebut, moisDebut, jourDebut);
        idTache = id;
        this.colonneOrigine = colCourante;
    }


    /**
     * Méthode ajouterAntecedent qui va permettre d'ajouter une tache
     * à la liste des antécedents
     * @param t la tache antécédente à ajouter à la liste
     */
    public void ajouterAntecedent(Tache t){
        //on vérifie que la tache n'est pas déjà dans la liste
        //on verifie que la tache (this) n'est pas déjà dans la liste des antécédents de la tache t
        if (!this.liste.contains(t)&&!t.etreAntecedent(this)&&t.getDateFin().isBefore(this.getDateDebut())) {
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
     * Méthode supprimerAntecedents qui supprime toutes les taches de la liste des antécedents
     */
    public void supprimerAntecedents(){
        this.liste.clear();
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
    public Tache tacheById(int id){
       if(this.idTache==id){
           return this;
       }else{
           return null;
       }
    }
    /**
     * Méthode afficher qui affiche la tache
     */
    public void afficher() {
        System.out.println("\t\t- " + this.nom);
    }
    /**
     * Méthode setDuree qui modifie la durée de la tache
     * @param duree la durée de la tache
     */
    public void setDuree(double duree) {
        this.duree = duree;
    }

    /**
     * Méthode setColonneOrigine qui modifie la colonne d'origine de la tache
     * @param colonneOrigine la colonne d'origine de la tache
     */
    public void setColonneOrigine(Colonne colonneOrigine) {
        this.colonneOrigine = colonneOrigine;
    }
    /**
     * Méthode setDate qui modifie la date de début de la tache
     * @param date la date de début de la tache
     */
    public void setDate(LocalDate date){
        this.dateDebut=date;

    }

    /**
     * Méthode etreAntecedent qui retourne true si la tache est un antécédent de la tache en paramètre
     * @param t la tache à tester
     * @return true si la tache est un antécédent de la tache en paramètre, false sinon
     */
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

    /**
     * Méthode getDuree qui retourne la durée de la tache
     * @return la durée de la tache
     */
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

    /**
     * Méthode getColonneOrigine qui retourne la colonne d'origine de la tache
     * @return la colonne d'origine de la tache
     */
    public Colonne getColonneOrigine() {
        return colonneOrigine;
    }
    /**
     * Méthode getId qui retourne l'id de la tache
     * @return l'id de la tache
     */
    public int getId() {
        return idTache;
    }


    /**
     * Méthode equals qui compare deux taches
     * @param o la tache à comparer
     * @return true si les deux taches sont égales, false sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        Tache tache = (Tache) o;
        return tache.idTache==this.idTache;
    }


}
