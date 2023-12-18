package MVC;

//Interface Observateur

import MVC.Sujet;

public interface Observateur {

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet
     * @param sujet sujet à actualiser
     */
    public void actualiser(Sujet sujet);
}
