package classes;

import MVC.Observateur;
import MVC.Sujet;

//Classe VueListe
public class VueListe implements Observateur {

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet sous forme de liste
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tab = (Tableau) sujet;
        System.out.println("VueListe : " + tab.getNom());
        for (Composant c : tab.getColonnes()) {
            System.out.println(" -->" + c.getNom()+" :");
            for (Tache tache :((Colonne)c).getTaches()) {
                System.out.println("         - " + tache.getNom());
            }
        }
    }
}
