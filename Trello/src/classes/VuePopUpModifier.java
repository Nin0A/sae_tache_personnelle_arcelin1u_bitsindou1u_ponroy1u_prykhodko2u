package classes;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

public class VuePopUpModifier extends Stage implements Observateur{
    private Tache tache;
    private Tableau tableau;

    VuePopUpModifier(Sujet sujet, Tache tache) {
        this.tache=tache;
        this.tableau = (Tableau) sujet;
    }
    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        nomTextField.setText(tache.getNom());
        Label nomLabel = new Label("Nom :");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(nomLabel,nomTextField);
        vbox.getChildren().add(hbox1);

        TextField dureeTextField = new TextField();
        dureeTextField.setText(""+tache.getDuree());
        Label dureeLabel = new Label("Durée :");
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(dureeLabel,dureeTextField);
        vbox.getChildren().add(hbox2);

        DatePicker datePicker = new DatePicker();
        // Créer une instance de LocalDate avec vos variables
        LocalDate date = LocalDate.of(tache.getDateDebut().getYear(), tache.getDateDebut().getMonth(), tache.getDateDebut().getDayOfMonth());

        // Définir la date sur le DatePicker
        datePicker.setValue(date);

        Label dateLabel = new Label("Date :");
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(dateLabel,datePicker);
        vbox.getChildren().add(hbox3);



        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");


        validerButton.setOnAction(event -> {

            tache.setNom(nomTextField.getText());
            tache.setDuree(Double.parseDouble(dureeTextField.getText()));
            tache.setDate(datePicker.getValue());

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
