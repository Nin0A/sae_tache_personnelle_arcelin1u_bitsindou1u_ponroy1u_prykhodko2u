package classes;

import java.util.ArrayList;

public class Tableau extends Composant implements Sujet {


    private ArrayList<Observateur> obsTab;

    private Colonne archive;

    //    super
    private ArrayList<Colonne> colonnes;

    private String nom;

    Tableau(String n) {
        super(n);
        liste = new ArrayList<Colonne>();
        obsTab = new ArrayList<>();
        archive = new Colonne("Archive");
        colonnes = new ArrayList<Colonne>();
    }

    //Colonne depart, Colonne arriv / tache att colonne courante
    public void deplacerTache(TacheMere t, Colonne c) {
    }

    public void archiverTache(TacheMere t) {
    }

    @Override
    public void enregistrerObservateur(Observateur o) {
        obsTab.add(o);
    }

    @Override
    public void supprimerObservateur(Observateur o) {
        obsTab.remove(o);
    }

    @Override
    public void notifierObservateur(Observateur o) {
        for (Observateur obs : obsTab) {
            obs.actualiser();
        }
    }
}
