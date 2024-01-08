package classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurColonne implements Controleur {
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
                VuePopUpModifierColonne vpmc= new VuePopUpModifierColonne(modele,colonne);
                vpmc.actualiser(modele);
                break;
            case "Supprimer":
                modele.supprimerColonne(colonne);
                break;
            case "Ajouter Colonne":
                System.out.println("existe tu ?");
               VuePopUpAjouterColonne vpac = new VuePopUpAjouterColonne(modele);
               vpac.actualiser(modele);

                break;
        }
    }
}
