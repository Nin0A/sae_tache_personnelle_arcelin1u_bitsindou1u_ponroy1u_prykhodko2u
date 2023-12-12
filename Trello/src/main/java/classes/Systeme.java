package classes;

import java.util.ArrayList;

public class Systeme implements Sujet{

    private Tableau tab;
    private ArrayList<Observateur> obsTab;
    Systeme(Tableau t){
        tab = t;
        obsTab = new ArrayList<>();
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
        for (Observateur obs: obsTab ) {
            obs.actualiser();
        }
    }

    public void setTab(Tableau tab) {
        this.tab = tab;
    }

    public Tableau getTab() {
        return tab;
    }

}
