package classes;

import java.util.ArrayList;

public class Tableau extends ComposantAListe implements Sujet {


    private ArrayList<Observateur> obsTab;

    private Colonne archiv;

//    super
//    private ArrayList<Colonne> colonnes;

    private String nom;

    Tableau(String n){
        super(n);
        obsTab = new ArrayList<>();
    }

    //Colonne depart, Colonne arriv / tache att colonne courante
   public void deplacerTache(Tache t, Colonne c){}

    public void archiverTache(Tache t){}

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
        for (Observateur obs: obsTab ) {
            obs.actualiser();
        }
    }
}
