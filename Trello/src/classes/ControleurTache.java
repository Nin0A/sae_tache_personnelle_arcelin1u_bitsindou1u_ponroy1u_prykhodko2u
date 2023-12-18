package MVC;

import classes.Tableau;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.EventObject;

public class ControleurTache implements Controleur{

    //attributs
    private Tableau modele;

    //constructeur
    public ControleurTache(Tableau modele){
        this.modele = modele;
    }

    /**
     * Méthode handle qui gère les évènements d'une tache
     * @param event évènement à gérer
     */
    @Override
    public void handle(EventObject event) {



    }

}
