package classes;

import MVC.Observateur;
import MVC.Sujet;

//Classe VueGantt
public class VueGantt implements Observateur {

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tab = (Tableau) sujet;
        System.out.println("VueGaant : " + tab.getNom());
        //TODO

    }
}
