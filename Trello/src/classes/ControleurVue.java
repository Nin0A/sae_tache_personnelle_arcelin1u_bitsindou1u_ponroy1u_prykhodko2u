package classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class ControleurVue implements Controleur<ActionEvent> {



    private Tableau tableau;

    private Vue vue;
/*
    ControleurVue(Tableau t){
        this.tableau=t;
        this.vue=new Vue(t);
    }

*/
    /**
     * Méthode handle
     * @param e action
     */
    public void handle(ActionEvent e){
        ComboBox<String> cb = (ComboBox<String>) e.getSource();

        this.vue.changerVue(cb.getValue());
        tableau.notifierObservateur();
    }
}