package classes;

import java.util.ArrayList;

public class Tableau extends Composant<Colonne> implements Sujet {


    private ArrayList<Observateur> obsTab;

    private Colonne archive;

    //    super
    //private ArrayList<Colonne> colonnes;

    private String nom;

    Tableau(String n) {
        super(n);
        obsTab = new ArrayList<>();
        archive = new Colonne("Archive");
    }

    public void deplacerTache(TacheMere t, Colonne depart, Colonne arrivee) {
        depart.liste.remove(t);
        arrivee.liste.add(t);
    }

    public void archiverTache(TacheMere t, Colonne depart) {
        depart.liste.remove(t);
        archive.liste.add(t);
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
            obs.actualiser(this);
        }
    }

    public ArrayList<Colonne> getColonnes() {
        return liste;
    }
}
