package classes;

import classes.Tableau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ControleurTache implements Controleur<ActionEvent>{

    //attributs
    private Tableau modele;
    private Tache tache;

    //constructeur
    public ControleurTache(Tableau modele, Tache tache){
        this.modele = modele;
        this.tache = tache;
    }



    /**
     * Méthode handle qui gère les évènements d'une tache
     * @param actionEvent évènement à gérer
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Button boutton = (Button) actionEvent.getSource();
        switch (boutton.getText()){
            case "Modifier":
                //pop la fenetre de modification
                break;
            case "Supprimer":
                modele.supprimerTache(tache);
                break;
            case "Archiver":
                modele.archiverTache(tache);
                break;
            case "Ajouter une tâche":
                VBox vboxtmp1 = (VBox)boutton.getParent();
                VBox vboxtmp2 = (VBox) vboxtmp1.getChildren().get(0);
                Label labeltmp = (Label) vboxtmp2.getChildren().get(0);
                System.out.println("LA COLONNE : "+modele.getColonneByName(labeltmp.getText()));
                VuePopUp vpu = new VuePopUp(modele, modele.getColonneByName(labeltmp.getText()));
                vpu.actualiser(modele);

                break;
        }
    }

}
