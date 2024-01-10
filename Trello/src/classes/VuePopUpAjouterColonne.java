package classes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VuePopUpAjouterColonne extends Stage implements Observateur {
    private Tableau tableau;

    VuePopUpAjouterColonne(Sujet sujet) {

        this.tableau = (Tableau) sujet;
    }
    /**
     * Méthode actualiser d'ouvrir une pop up pour ajouter une colonne
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(nomLabel,nomTextField);
        vbox.getChildren().add(hbox1);





        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");


        validerButton.setOnAction(event -> {

            Colonne colonne = new Colonne(nomTextField.getText());
            tableau.ajouterColonne(colonne);

            // Fermer la fenêtre pop-up après validation
            this.close();
            tableau.notifierObservateur();

        });

        annulerButton.setOnAction(event -> {
            this.close();
        });


        HBox buttonBox = new HBox(validerButton, annulerButton);
        vbox.getChildren().add(buttonBox);

        Scene scene = new Scene(vbox, 700, 350);
        this.setScene(scene);
        this.show();
    }
}
