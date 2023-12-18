package classes;

import java.util.ArrayList;

//Classe Système

public class Systeme implements Sujet {

    //attributs
    private Tableau tab; //tableau contenu
    private ArrayList<Observateur> obsTab; //liste des observateurs du tableau

    //constructeur
    Systeme(Tableau t) {
        tab = t;
        obsTab = new ArrayList<>();
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
     * Méthode setTab qui remplace le tableau par le paramètre
     * @param tab tableau par lequel remplacé
     */
    public void setTab(Tableau tab) {
        this.tab = tab;
    }

    /**
     * Méthode getTab qui retourne l'attribut le tableau
     * @return le tableau en question
     */
    public Tableau getTab() {
        return tab;
    }

}
