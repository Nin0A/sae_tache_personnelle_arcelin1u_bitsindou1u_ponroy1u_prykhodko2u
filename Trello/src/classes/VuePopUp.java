package classes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class VuePopUp extends Stage implements Observateur {

    VuePopUp(Sujet sujet, Colonne colonne) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");
        gridPane.add(nomLabel, 0, 0);
        gridPane.add(nomTextField, 1, 0);

        TextField dureeTextField = new TextField();
        Label dureeLabel = new Label("Durée :");
        gridPane.add(dureeLabel, 0, 1);
        gridPane.add(dureeTextField, 1, 1);

        DatePicker datePicker = new DatePicker();

        Label dateLabel = new Label("Date :");
        gridPane.add(dateLabel, 0, 2);
        gridPane.add(datePicker, 1, 2);

        Button validerButton = new Button("Valider");
        validerButton.setOnAction(event -> {
            String nom = nomTextField.getText();
            String duree = dureeTextField.getText();

            Tableau t = (Tableau) sujet;
            LocalDate selectedDate = datePicker.getValue();
            if(selectedDate!=null)
                t.ajouterTache(colonne,new TacheMere(nom,Double.parseDouble(duree),selectedDate.getDayOfMonth(),selectedDate.getMonthValue(),selectedDate.getDayOfYear()));
            // Vous pouvez fermer la fenêtre pop-up après validation
            this.close();
        });

        HBox buttonBox = new HBox(validerButton);
        gridPane.add(buttonBox, 1, 3);

        Scene scene = new Scene(gridPane, 400, 200);
        this.setScene(scene);
        this.show();
    }

    /**
     * Méthode actualiser qui permet d'actualiser le sujet sous forme de liste
     *
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        // Implementez ici la mise à jour du formulaire si nécessaire
    }
}
