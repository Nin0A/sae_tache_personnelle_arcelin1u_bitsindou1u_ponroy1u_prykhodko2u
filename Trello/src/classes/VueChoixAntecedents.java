package classes;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class VueChoixAntecedents extends Stage implements Observateur{
    private ArrayList<Tache> taches;
    private Tache tache;
    VueChoixAntecedents(ArrayList<Tache> taches, Tache tache) {
        this.taches = taches;
        this.tache = tache;
    }

    /**
     * Méthode actualiser d'ouvrir une pop up pour choisir les antécédents d'une tache
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tableau = (Tableau) sujet;
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        VBox vboxCheckBox = new VBox();
        //tache cochée qui sont déja antécédents
       for (Tache t: taches){
           if(t!=this.tache){
               HBox hbox = new HBox();
               CheckBox checkBox = new CheckBox(t.getNom());
               if(tache.etreAntecedent(t)){
                   checkBox.setSelected(true);
               }
               hbox.getChildren().add(checkBox);
               Label idLabel = new Label(t.getId()+"");
               idLabel.setVisible(false);
               hbox.getChildren().add(idLabel);
               vboxCheckBox.getChildren().add(hbox);
           }

       }

       for(Node node : vboxCheckBox.getChildren()) {
           HBox hBox = (HBox) node;

       }
        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");
        validerButton.setOnAction(event -> {
            for (Node node : vboxCheckBox.getChildren()) {
                HBox hBox = (HBox) node;
                int id = Integer.parseInt(((Label)hBox.getChildren().get(1)).getText());
                if (((CheckBox) hBox.getChildren().get(0)).isSelected()) {
                    tache.ajouterAntecedent(tableau.getTachebyId(id));

                } else {
                    tache.supprimerAntecedent(tableau.getTachebyId(id));
                }
            }
            // Fermer la fenêtre pop-up après validation
            this.close();
            tableau.notifierObservateur();
        });

        annulerButton.setOnAction(event -> {
            this.close();
        });
        vbox.getChildren().add(vboxCheckBox);
        HBox buttonBox = new HBox(validerButton, annulerButton);
        vbox.getChildren().add(buttonBox);
        Scene scene = new Scene(vbox, 700, 350);
        this.setScene(scene);
        this.show();
    }
}
