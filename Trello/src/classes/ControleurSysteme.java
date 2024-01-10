package classes;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.Button;

public class ControleurSysteme implements Controleur<ActionEvent>{
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
               VuePopUpModifierTableau vuePopUpModifierTableau = new VuePopUpModifierTableau(modele, tableau);
               vuePopUpModifierTableau.actualiser(modele);
                break;
            case "Supprimer":
                modele.supprimerTab(tableau);
                break;
            case "Ajouter Tableau":
                VuePopUpAjouterTableau vuePopUpAjouterTableau = new VuePopUpAjouterTableau(modele);
                vuePopUpAjouterTableau.actualiser(modele);
                //fenetre pop up
                break;
                default:
                    this.modele.changerTableauCourrant(tableau);
                break;

        }
    }


}
