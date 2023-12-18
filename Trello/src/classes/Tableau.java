package classes;

import java.util.ArrayList;
import java.util.Collections;

//Classe Tableau

public class Tableau extends Composant<Colonne> implements Sujet {

    //attributs
    private ArrayList<Observateur> obsTab; //liste des observateurs

    private Colonne archive; //Colonne des archives

    //    super
    //private ArrayList<Colonne> colonnes;

    private String nom;

    //constructeur
    public Tableau(String n) {
        super(n);
        obsTab = new ArrayList<>();
        archive = new Colonne("Archive");
    }

    /**
     * Méthode ajouterColonne qui ajoute une colonne à un tableau
     * @param c colonne à ajouter
     */
    public void ajouterColonne(Colonne c){
        this.liste.add(c);
    }

    /**
     * Méthode supprimerColonne qui supprime  une colonne d'un tableau
     * @param c colonne à supprimer
     */
    public void supprimerColonne(Colonne c){
        this.liste.remove(c);
    }

    /**
     * Méthode deplacerTache qui deplace une tache d'une colonne à une autre
     * @param t tache à déplacer
     * @param depart colonne courante de la tache
     * @param arrivee colonne où on veut déplacer la tache
     */
    public void deplacerTache(Tache t, Colonne depart, Colonne arrivee) {
        depart.liste.remove(t);
        arrivee.liste.add(t);
    }

    /**
     * Méthode archiverTache qui permet d'archiver la tache en parametre
     * @param t tache que l'on veut archiver
     * @param depart colonne courante de la tache
     */
    public void archiverTache(Tache t, Colonne depart) {
        depart.liste.remove(t);
        archive.liste.add(t);
    }

    /**
     * Méhtode enregistrerObservateur qui ajoute l'observateur
     * observé en paramètre à la liste d'observateurs
     * @param o observateur observé à ajouter
     */
    @Override
    public void enregistrerObservateur(Observateur o) {
        obsTab.add(o);
    }

    /**
     * Méhtode supprimerObservateur qui supprime l'observateur
     * observé en paramètre de la liste d'observateurs
     * @param o observateur observé à supprimer
     */
    @Override
    public void supprimerObservateur(Observateur o) {
        obsTab.remove(o);
    }

    /**
     * Méhtode notifierObservateur qui met à jour l'observateur
     * observé en paramètre de la liste d'observateurs
     * @param o observateur observé à mettre à jour
     */
    @Override
    public void notifierObservateur(Observateur o) {
        for (Observateur obs : obsTab) {
            obs.actualiser(this);
        }
    }

    /**
     * Méthode getColonnes qui permet de retourner la liste de toutes les colonnes du tableau
     * @return la liste de toutes les colonnes du tableau
     */
    public ArrayList<Colonne> getColonnes() {
        return liste;
    }

}
