package classes;

import javafx.event.ActionEvent;
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

                VuePopUpModifier vpm= new VuePopUpModifier(modele,(TacheMere) tache);
                vpm.actualiser(modele);
                break;
            case "Supprimer":
                modele.supprimerTache(tache);
                break;
            case "Archiver":
                modele.archiverTache(tache);
                break;
            case "Désarchiver":
                modele.desarchiverTache(tache);
                break;
            case "Ajouter une tâche":
                VBox vboxtmp1 = (VBox)boutton.getParent();
                VBox vboxtmp2 = (VBox) vboxtmp1.getChildren().get(0);
                System.out.println("vboxtmp2.getChildren().get(1)"+vboxtmp2.getChildren());
                Label labeltmp = (Label) vboxtmp2.getChildren().get(1);
                System.out.println(labeltmp.getText());
                VuePopUpAjouter vpu = new VuePopUpAjouter(modele, modele.getColonneById(Integer.parseInt(labeltmp.getText())));
                vpu.actualiser(modele);
            break;
        }
    }

}
