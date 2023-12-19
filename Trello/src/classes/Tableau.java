package classes;

import java.util.ArrayList;
import java.util.Collections;

//Classe Tableau

public class Tableau extends Composant<Colonne> implements Sujet {

    //attributs
    private ArrayList<Observateur> obsTab; //liste des observateurs

    private Colonne archive; //Colonne des archives


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
        //on vérifie que la colonne à ajouter n'est pas null
        if (c == null) {
            throw new IllegalArgumentException("La colonne à ajouter est null");
        }

        //on vérifie que la colonne n'est pas déjà dans la liste
        if (!this.liste.contains(c)) {
            this.liste.add(c);
        } else {
            //Sinon on genere une exception
            throw new IllegalArgumentException("La colonne existe déjà");
        }
    }

    /**
     * Méthode supprimerColonne qui supprime  une colonne d'un tableau
     * @param c colonne à supprimer
     */
    public void supprimerColonne(Colonne c){

        //on vérifie que la colonne à supprimer n'est pas null
        if (c == null) {
            throw new IllegalArgumentException("La colonne à supprimer est null");
        }

        //on vérifie que la colonne est dans la liste des colonnes du tableau
        if (!this.liste.contains(c)) {
            throw new IllegalArgumentException("La colonne n'existe pas");
        }

        this.liste.remove(c);
    }

    /**
     * Méthode deplacerTache qui deplace une tache d'une colonne à une autre
     * @param t tache à déplacer
     * @param depart colonne courante de la tache
     * @param arrivee colonne où on veut déplacer la tache
     */
    public void deplacerTache(Tache t, Colonne depart, Colonne arrivee) {

        //on vérifie que la tache à déplacer n'est pas null
        if (t == null) {
            throw new IllegalArgumentException("La tache à déplacer est null");
        }
        //on vérifie que la colonne de départ n'est pas null
        if (depart == null) {
            throw new IllegalArgumentException("La colonne de départ est null");
        }
        //on vérifie que la colonne d'arrivée n'est pas null
        if (arrivee == null) {
            throw new IllegalArgumentException("La colonne d'arrivée est null");
        }
        //on vérifie que la colonne de départ est dans la liste des colonnes du tableau
        if (!this.liste.contains(depart)) {
            throw new IllegalArgumentException("La colonne de départ n'existe pas");
        }
        //on vérifie que la colonne d'arrivée est dans la liste des colonnes du tableau
        if (!this.liste.contains(arrivee)) {
            throw new IllegalArgumentException("La colonne d'arrivée n'existe pas");
        }
        //on vérifie que la tache est dans la liste des taches de la colonne de départ
        if (!depart.liste.contains(t)) {
            throw new IllegalArgumentException("La tache n'existe pas dans la colonne de départ");
        }
        //on vérifie que la tache n'est pas dans la liste des taches de la colonne d'arrivée
        if (arrivee.liste.contains(t)) {
            throw new IllegalArgumentException("La tache existe déjà dans la colonne d'arrivée");
        }
        //si la tache est une sous tache
        if (t instanceof SousTache) {
            throw new IllegalArgumentException("La tache est une sous tache");
        }

        ArrayList<Tache> sousTaches = new ArrayList<>();

        //on recupere la liste des sous tasches de la tache si elle est une tache mere
        if (t instanceof TacheMere) {
           sousTaches = ((TacheMere) t).getSousTaches();
        }

        //on retire la tache de la colonne de depart
        depart.liste.remove(t);
        //on retire aussi ses sous taches de la colonne de depart
        for (Tache sousTache : sousTaches) {
            depart.liste.remove(sousTache);
        }
        //on ajoute la tache à la colonne d'arrivée
        arrivee.liste.add(t);

        //on ajoute aussi ses sous taches à la colonne d'arrivée
        arrivee.liste.addAll(sousTaches);
    }

    /**
     * Méthode archiverTache qui permet d'archiver la tache en parametre
     * @param t tache que l'on veut archiver
     * @param depart colonne courante de la tache
     */
    public void archiverTache(Tache t, Colonne depart) {
        //on vérifie que la tache à archiver n'est pas null
        if (t == null) {
            throw new IllegalArgumentException("La tache à archiver est null");
        }
        //on vérifie que la colonne de départ n'est pas null
        if (depart == null) {
            throw new IllegalArgumentException("La colonne de départ est null");
        }
        //on vérifie que la colonne de départ est dans la liste des colonnes du tableau
        if (!this.liste.contains(depart)) {
            throw new IllegalArgumentException("La colonne de départ n'existe pas");
        }
        //on vérifie que la tache est dans la liste des taches de la colonne de départ
        if (!depart.liste.contains(t)) {
            throw new IllegalArgumentException("La tache n'existe pas dans la colonne de départ");
        }

        //Liste des sous taches de la tache
        ArrayList<Tache> sousTaches = new ArrayList<>();

        //on recupere la liste des sous tasches de la tache si elle est une tache mere
        if (t instanceof TacheMere) {
            sousTaches = ((TacheMere) t).getSousTaches();
        }

        //on retire la tache de la colonne de depart
        depart.liste.remove(t);

        //on retire aussi ses sous taches de la colonne de depart
        for (Tache sousTache : sousTaches) {
            depart.liste.remove(sousTache);
        }

        //on ajoute la tache à la colonne des archives
        archive.liste.add(t);

        //on ajoute aussi ses sous taches à la colonne des archives
        archive.liste.addAll(sousTaches);
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
     */
    @Override
    public void notifierObservateur() {
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

    public void  afficher() {
        System.out.println(this.getNom()+" :");
        for (Colonne c : this.getColonnes()) {
            c.afficher();
        }
    }
    @Override
    public String toString() {
        return this.nom;
    }

}
