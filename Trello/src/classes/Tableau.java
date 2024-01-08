package classes;

import java.util.ArrayList;
import java.util.Collections;

//Classe Tableau

public class Tableau extends Composant<Colonne> implements Sujet {

    //attributs
    private ArrayList<Observateur> obsTab; //liste des observateurs

    private Colonne archive; //Colonne des archives


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
            notifierObservateur();
        } else {
            //Sinon, on génère une exception
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
        notifierObservateur();
    }

    public Colonne getColonneByName(String s){
        Colonne res =null;
        for(int i=0; i<this.liste.size();i++) {
            if (this.liste.get(i).getNom().equals(s))
                res = this.liste.get(i);
        }

        return res;
    }

    /**
     * Méthode deplacerTache qui deplace une tache d'une colonne à une autre
     * @param t tache à déplacer
     * @param arrivee colonne où on veut déplacer la tache
     */
    public void deplacerTache(Tache t, Colonne arrivee) {

        Colonne depart = chercherColonne(t);

        //on vérifie que la colonne de départ est utilisable
        verifierColonne(depart);

        //on verifie que la colonne d'arrivée est utilisable
        verifierColonne(arrivee);

        //on vérifie que la tache est utilisable
        verifierTache(t);

        //on vérifie que la tache est dans la liste des taches de la colonne de départ
        if (!depart.liste.contains(t)) {
            throw new IllegalArgumentException("La tache n'existe pas dans la colonne de départ");
        }
        //on vérifie que la tache n'est pas dans la liste des taches de la colonne d'arrivée
        if (arrivee.liste.contains(t)) {
            throw new IllegalArgumentException("La tache existe déjà dans la colonne d'arrivée");
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

        notifierObservateur();

    }

    /**
     * Méthode archiverTache qui permet d'archiver la tache en parametre
     * @param t tache que l'on veut archiver
     */
    public void archiverTache(Tache t) {
        Colonne depart = chercherColonne(t);
        t.setColonneOrigine(depart);

        //Liste des sous taches de la tache
        //ArrayList<Tache> sousTaches = new ArrayList<>();

        //on recupere la liste des sous taches de la tache si elle est une tache mere
        /*if (t instanceof TacheMere) {
            sousTaches = ((TacheMere) t).getSousTaches();
        }*/

        //si la tache est une tache mere et sous tache d'une autre tache mere, on en

        //on vérifie que la tache n'est pas deja archivée
        if (!verifTacheArchive(t)) {
            archive.liste.add(t);
            //on retire la tache de la colonne de depart
            depart.liste.remove(t);

            /*for (Tache sousTache : sousTaches) {
                archiverTache(sousTache);
                //on retire aussi ses sous taches de la colonne de depart
                depart.liste.remove(sousTache);
            }*/
        }

        notifierObservateur();

    }



    /**
     * Méthode verifTacheArchive qui verifie si la tache n'est pas deja archivée
     * @param t tache à vérifier
     * @return true si la tache est deja archivée, false sinon
     */
    private boolean verifTacheArchive(Tache t) {
        boolean res = false;
        for (Tache tache : archive.liste) {
            if (tache.tacheExiste(t.getId())) {
                res = true;
            }
        }
        return res;
    }


    /**
     * Méthode desarchiverTache qui permet de désarchiver la tache en parametre et la remettre dans la colonne de départ
     * @param t tache que l'on veut désarchiver
     */
    public void desarchiverTache(Tache t) {
        Colonne depart = t.getColonneOrigine();
        t.setColonneOrigine(depart);

        //Liste des sous taches de la tache
        ArrayList<Tache> sousTaches = new ArrayList<>();

        //on recupere la liste des sous taches de la tache si elle est une tache mere
        if (t instanceof TacheMere) {
            sousTaches = ((TacheMere) t).getSousTaches();
        }

        //on retire la tache de la colonne de depart
        archive.liste.remove(t);

        //on retire aussi ses sous taches de la colonne de depart
        for (Tache sousTache : sousTaches) {
            archive.liste.remove(sousTache);
        }

        //on ajoute la tache à la colonne des archives
        depart.liste.add(t);

        //on ajoute aussi ses sous taches à la colonne des archives si il y en a
        depart.liste.addAll(sousTaches);

        notifierObservateur();
    }


    /**
     * Méthode verifierTache qui permet de vérifier si une tache est utilisable
     * @param t tache à vérifier
     */
    public void verifierTache (Tache t){
        //on vérifie que la tache n'est pas null
        if (t == null) {
            throw new IllegalArgumentException("La tache est null");
        }

    }

    /**
     * Méthode verifierColonne qui permet de vérifier si une colonne est utilisable
     * @param c colonne à vérifier
     */
    public void verifierColonne (Colonne c) {
        //on vérifie que la colonne n'est pas null
        if (c == null) {
            throw new IllegalArgumentException("La colonne  est null");
        }

        //on vérifie que la colonne n'est pas dans la liste des colonnes du tableau
        if (!this.liste.contains(c)) {
            throw new IllegalArgumentException("La colonne n'existe pas");
        }

    }

    /**
     * Méthode supprierTache qui permet de supprimer une tache
     * @param tache tache à supprimer
     */
    public void supprimerTache(Tache tache){
       Colonne colonne = chercherColonne(tache);
       colonne.supprimerTache(tache);
       notifierObservateur();
    }


    /**
     * Méthode ajouterTache qui permet de créer une tache
     * @param colonne colonne où l'on veut créer la tache
     * @param tache tache à créer
     */
    public void ajouterTache(Colonne colonne, Tache tache){
        if(chercherColonne(tache)==null) {
            colonne.ajouterTache(tache);
            System.out.println("la date de la tache ajouter : "+tache.getDateDebut());
        }
        notifierObservateur();
    }

/*
    /**
     * Méthode verifierNom qui verifie que le nom d'une tache ou sous-tache soit unique dans tout le tableau
     * @param nom nom de la tache ou sous-tache
     * @return true si le nom est unique, false sinon
     /
    public boolean verifierNom(String nom) {
        boolean res = true;
        for (Colonne colonne : liste) {
            for (Tache tache : colonne.getTaches()) {
                if (tache.tacheExiste(nom)) {
                    res = false;
                    break;
                }
            }
        }
        return res;
    }
*/
    /////////////////////////////////////////////////////////
    /**
     * Méthode chercherColonne qui permet de chercher la colonne d'une tache ou soustache
     * @param t tache dont on veut chercher la colonne
     * @return la colonne de la tache
     */
    public Colonne chercherColonne(Tache t){
        Colonne colonne = null;
        for(Colonne coltmp : liste){
            for (Tache tachetmp : coltmp.getTaches()){
                if(tachetmp.tacheExiste(t.getId())){
                    colonne = coltmp;
                    break;
                }
            }
        }
        for (Tache tache : archive.getTaches()){
            if(tache.tacheExiste(t.getId())){
                colonne = archive;
                break;
            }
        }
        return colonne;
    }
    public void supprimerOcurance(Tache tache){
        for (Colonne colonne : liste){
            for(Tache tachetmp : colonne.getTaches()){
                tachetmp.supprimerAntecedent(tache);

            }
        }
    }
///////////////////////////////////////////////////////////////
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

    public void setColonnes(ArrayList<Colonne> list){
        liste = list;
    }


    /**
     * Méthode getArchive qui permet de retourner la colonne des archives
     * @return la colonne des archives
     */
    public Colonne getArchive() {
        return archive;
    }

    /**
     * Méthode getNom qui permet de retourner le nom du tableau et ses colonnes
     * @return le nom du tableau et ses colonnes
     */
    public void  afficher() {
        System.out.println(this.getNom()+" :");
        for (Colonne c : this.getColonnes()) {
            c.afficher();
        }
        System.out.println("Archive :");
        this.getArchive().afficher();
    }

    /**
     * Méhtode toString qui permet de retourner le nom du tableau
     * @return le nom du tableau
     */
    @Override
    public String toString() {
        return this.nom;
    }

}
