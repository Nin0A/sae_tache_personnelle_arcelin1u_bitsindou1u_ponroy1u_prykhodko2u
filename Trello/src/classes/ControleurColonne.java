package classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurColonne implements Controleur {
    @Override
    public void handle(ActionEvent actionEvent) {
        Button boutton = (Button) actionEvent.getSource();
        switch (boutton.getText()){
            case "Modifier":
                break;
            case "Supprimer":
                break;
            case "Ajouter Colonne":
                break;
        }
    }
}
