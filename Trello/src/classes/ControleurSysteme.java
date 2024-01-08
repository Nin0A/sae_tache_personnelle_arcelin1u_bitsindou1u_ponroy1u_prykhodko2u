package classes;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class ControleurSysteme implements Controleur{
    private Systeme modele;
    private Tableau tableau;
    ControleurSysteme(Systeme modele, Tableau tableau){
        this.modele = modele;
        this.tableau = tableau;
    }
    @Override
    public void handle(ActionEvent actionEvent) {
        Button boutton = (Button) actionEvent.getSource();
        switch (boutton.getText()){
            case "Modifier":
                this.tableau.setNom("Nouveau nom");//a modifier
                this.modele.notifierObservateur();
                //modifier le nom
                break;
            case "Supprimer":
                modele.supprimerTab(tableau);
                break;
            case "Ajouter Tableau":
                this.tableau= new Tableau("Nouveau tableau");//a modifier
                modele.ajouterTab(this.tableau);
                //fenetre pop up
                break;
                default:
                    this.modele.changerTableauCourrant(tableau);
                break;

        }
    }
}
