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

import java.time.LocalDate;

public class VuePopUpAjouter extends Stage implements Observateur {

    private Colonne colonneCourante;

    private TacheMere tacheMere;

    private int rangTache;


    VuePopUpAjouter(Sujet sujet, Colonne colonne) {
        this.colonneCourante = colonne;
        this.tacheMere=null;
    }

    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setId("POPUP");
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");
        vbox.getChildren().addAll(createHBox(nomLabel, nomTextField));

        TextField dureeTextField = new TextField();
        Label dureeLabel = new Label("Durée :");
        vbox.getChildren().addAll(createHBox(dureeLabel, dureeTextField));

        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Date :");
        vbox.getChildren().addAll(createHBox(dateLabel, datePicker));


        this.tacheMere = new TacheMere("desc",1,1,1,1111);



        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");
        Button ajouterSousTache = new Button("Ajouter une sous nouvelle sous tâche");


        validerButton.setOnAction(event -> {

                String nom = nomTextField.getText();
                String duree = dureeTextField.getText();

                Tableau t = (Tableau) sujet;
                LocalDate selectedDate = datePicker.getValue();
                TacheMere tacheMere = new TacheMere(nom, Double.parseDouble(duree), selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear());
                t.ajouterTache(colonneCourante, tacheMere);

                // Ajouter les sous-tâches récursivement
                ajouterSousTachesRecursive(tacheMere,1);

                // Fermer la fenêtre pop-up après validation
                this.close();
                t.notifierObservateur();

        });

        annulerButton.setOnAction(event -> {
            this.close();
        });

        ajouterSousTache.setOnAction(event -> {
            creerFormSousTache(vbox,1);
        });

        HBox buttonBox = new HBox(validerButton, annulerButton, ajouterSousTache);
        vbox.getChildren().add(buttonBox);

        Scene scene = new Scene(vbox, 700, 350);
        this.setScene(scene);
        this.show();
    }

    private HBox createHBox(Label label, TextField textField) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label, textField);
        hbox.setSpacing(10);
        return hbox;
    }

    private HBox createHBox(Label label, DatePicker datePicker) {
        HBox hbox = new HBox();
        hbox.getChildren().addAll(label, datePicker);
        hbox.setSpacing(10);
        return hbox;
    }

    public void creerFormSousTache(VBox vbox,int marge) {
        VBox vBoxSousTache = new VBox();
        HBox hbox = new HBox();
        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");
        hbox.getChildren().addAll(nomLabel, nomTextField);

        TextField dureeTextField = new TextField();
        Label dureeLabel = new Label("Durée :");
        hbox.getChildren().addAll(dureeLabel, dureeTextField);

        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Date :");
        hbox.getChildren().addAll(dateLabel, datePicker);

        Button ajouterSousTacheButton = new Button("Ajouter Sous-Tâche");
        hbox.getChildren().add(ajouterSousTacheButton);

        ajouterSousTacheButton.setOnAction(event -> {
            VBox vboxtmp=(VBox)ajouterSousTacheButton.getParent().getParent();
            vboxtmp.setSpacing(5);
            creerFormSousTache(vboxtmp,marge+1);  // Appel récursif pour créer des sous-tâches de la sous-tâche
        });


        hbox.setId("#id"+this.rangTache);
        System.out.println(rangTache);
        this.rangTache++;
        System.out.println("marge : " +marge);
        hbox.setPadding(new Insets(0, 0, 0, marge*20));
        vBoxSousTache.getChildren().add(hbox);
        vbox.getChildren().add(vBoxSousTache);



        tacheMere.ajouterSousTache(new TacheMere("desc",1,1,1,1111));
    }

    private void ajouterSousTachesRecursive(TacheMere parent,int indice) {
        int indiceNom = indice * 2;
        int indiceDuree = indiceNom + 1;

        /*
            LocalDate selectDate = listeDeDate.get(indice).getValue();

            System.out.println("Parent : " + parent.getNom() + "\n"
                    + "  --> Soustache --->>>>>>   " + listeDeTextField.get(indiceNom).getText() + " "
                    + Double.parseDouble(listeDeTextField.get(indiceDuree).getText()) + " "
                    + selectDate.getDayOfMonth() + " "
                    + selectDate.getMonthValue() + " "
                    + selectDate.getYear());

            TacheMere sousTache = new TacheMere(
                    listeDeTextField.get(indiceNom).getText(),
                    Double.parseDouble(listeDeTextField.get(indiceDuree).getText()),
                    selectDate.getDayOfMonth(),
                    selectDate.getMonthValue(),
                    selectDate.getYear()
            );



            // Ajouter la sous-tâche au parent
            parent.ajouterSousTache(sousTache);

            // Appel récursif pour la sous-tâche suivante
            ajouterSousTachesRecursive(sousTache, listeDeTextField, listeDeDate, indice + 1, rangTacheMere);
        */
    }

    /*
    public boolean verifierChampsRemplis() {
        boolean res = true;
        for (TextField textfield : listeDeTextField) {
            if (textfield.getText() == null)
                res = false;
        }
        for (DatePicker datePicker : listeDeDate) {
            if (datePicker.getValue() == null)
                res = false;
        }

        System.out.println("VERIFICATION : " + res);
        return res;
    }

     */
}
