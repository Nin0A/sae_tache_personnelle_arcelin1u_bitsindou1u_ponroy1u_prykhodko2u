package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VuePopUpModifierTableau extends Stage implements Observateur {

    private Systeme systeme;
    private Tableau tableau;

    VuePopUpModifierTableau(Sujet sujet, Tableau tableau) {
        this.tableau=tableau;
        this.systeme = (Systeme) sujet;
    }
    /**
     * Méthode actualiser d'ouvrir une pop up pour modifier un tableau
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        nomTextField.setText(tableau.getNom());
        Label nomLabel = new Label("Nom :");

        HBox hbox1 = new HBox();
        hbox1.setSpacing(3);
        hbox1.setAlignment(Pos.CENTER);
        hbox1.getChildren().addAll(nomLabel,nomTextField);
        vbox.getChildren().add(hbox1);



        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");


        validerButton.setOnAction(event -> {

            tableau.setNom(nomTextField.getText());

            // Fermer la fenêtre pop-up après validation
            this.close();
            systeme.notifierObservateur();

        });

        annulerButton.setOnAction(event -> {
            this.close();
        });


        hbox1.getChildren().addAll(validerButton,annulerButton);

        Scene scene = new Scene(vbox, 400, 50);
        this.setScene(scene);
        this.setTitle("Modifier Tableau");
        this.show();
    }
}
