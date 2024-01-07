package classes;

import classes.Tableau;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

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
            case "Ajouter une tache":
                //savoir la colonne ?
                break;
        }
    }

}
