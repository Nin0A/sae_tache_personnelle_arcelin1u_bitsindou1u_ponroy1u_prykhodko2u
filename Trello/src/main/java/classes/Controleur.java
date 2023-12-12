package classes;

import javafx.event.ActionEvent;

public abstract class Controleur {

    private Tableau tab;

    Controleur(Tableau t){
        tab = t;
    }

    public abstract void handle(ActionEvent e);
}
