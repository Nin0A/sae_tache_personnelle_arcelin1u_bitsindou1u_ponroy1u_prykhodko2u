package classes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VuePopUpModifierColonne extends Stage implements Observateur {

    private Colonne colonne;
    private Tableau tableau;

    VuePopUpModifierColonne(Sujet sujet, Colonne colonne) {
        this.colonne=colonne;
        this.tableau = (Tableau) sujet;
    }

    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        nomTextField.setText(colonne.getNom());
        Label nomLabel = new Label("Nom :");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(nomLabel,nomTextField);
        vbox.getChildren().add(hbox1);





        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");


        validerButton.setOnAction(event -> {

            colonne.setNom(nomTextField.getText());

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
