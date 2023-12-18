package classes;

import classes.Tableau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurTache implements EventHandler<ActionEvent>, Controleur{

    //attributs
    private Tableau modele;

    //constructeur
    public ControleurTache(Tableau modele){
        this.modele = modele;
    }



    /**
     * Méthode handle qui gère les évènements d'une tache
     * @param actionEvent évènement à gérer
     */
    @Override
    public void handle(ActionEvent actionEvent) {

    }

}
