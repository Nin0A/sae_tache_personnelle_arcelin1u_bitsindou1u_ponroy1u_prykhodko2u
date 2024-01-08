package classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurColonne implements Controleur<ActionEvent> {
    private Tableau modele;
    private Colonne colonne;
    ControleurColonne(Tableau modele, Colonne colonne){
        this.modele = modele;
        this.colonne = colonne;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button boutton = (Button) actionEvent.getSource();
        switch (boutton.getText()){
            case "Modifier":
                break;
            case "Supprimer":
                modele.supprimerColonne(colonne);
                break;
            case "Ajouter Colonne":
                modele.ajouterColonne(new Colonne("Nouvelle colonne"));
                //fenetre pop up
                break;
        }
    }
}
