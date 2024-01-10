package classes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurTableau implements Controleur<ActionEvent>{
    /**
     * Méthode handle qui gère les actions sur les boutons
     * @param actionEvent action sur un bouton
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Button boutton = (Button) actionEvent.getSource();
        switch (boutton.getText()){
            case "Modifier":
                //change le nom du tableau
                break;
            case "Supprimer":
                //avec système
                break;
        }
    }
}
