package classes;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
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

    private Sujet sujet;

    VuePopUpAjouter(Sujet sujet, Colonne colonne) {
        this.colonneCourante = colonne;
        this.tacheMere=null;
        this.sujet=sujet;
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






        annulerButton.setOnAction(event -> {
            this.close();
        });



        HBox buttonBox = new HBox(validerButton, annulerButton, ajouterSousTache);
        VBox vboxcontainer = new VBox();
        vbox.getChildren().addAll(buttonBox,vboxcontainer);

        ajouterSousTache.setOnAction(event -> {
            creerFormSousTache(vboxcontainer,1,1);
        });

        validerButton.setOnAction(event -> {
            String nom = nomTextField.getText();
            String duree = dureeTextField.getText();

            Tableau t = (Tableau) sujet;
            LocalDate selectedDate = datePicker.getValue();
            TacheMere tacheMere = new TacheMere(nom, Double.parseDouble(duree), selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear());
            t.ajouterTache(colonneCourante, tacheMere);

            // Créer une nouvelle tâche mère pour chaque appel récursif
            if(vbox.getChildren().get(vbox.getChildren().size()-1)instanceof VBox) {
                ajouterSousTachesRecursive(tacheMere, vboxcontainer);
            }
            System.out.println("coolmavie");
            // Fermer la fenêtre pop-up après validation
            this.close();
            t.notifierObservateur();
        });

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

    public void creerFormSousTache(VBox vbox,int rangTache,int marge) {
        //création
        VBox vBoxSousTache = new VBox();
        vBoxSousTache.setStyle("-fx-border-color: black ");
        HBox hbox = new HBox();

        //champ nom
        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");
        hbox.getChildren().addAll(nomLabel, nomTextField);

        //champ durée
        TextField dureeTextField = new TextField();
        Label dureeLabel = new Label("Durée :");
        hbox.getChildren().addAll(dureeLabel, dureeTextField);

        //champ dateDébut
        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Date :");
        hbox.getChildren().addAll(dateLabel, datePicker);

        //button ajouter sous tache
        Button ajouterSousTacheButton = new Button("Ajouter Sous-Tâche");
        hbox.getChildren().add(ajouterSousTacheButton);

        ajouterSousTacheButton.setOnAction(event -> {
            VBox vboxtmp=(VBox)ajouterSousTacheButton.getParent().getParent();
            vboxtmp.setSpacing(5);
            creerFormSousTache(vboxtmp,rangTache+1,marge+1);  // Appel récursif pour créer des sous-tâches de la sous-tâche
        });


        System.out.println(rangTache);
        hbox.setId("#id"+rangTache);



        hbox.setPadding(new Insets(0, 0, 0, marge*20));
        vBoxSousTache.getChildren().add(hbox);
        vbox.getChildren().add(vBoxSousTache);

    }

    private void ajouterSousTachesRecursive(TacheMere tacheMere, VBox vbox) {

        //for each
        if(vbox!=null) {

            System.out.println("print vbox getChildren : "+vbox.getChildren());
            for (Node vboxtmp : vbox.getChildren()) {

                if(vboxtmp instanceof VBox) {
                    VBox vboxcast = (VBox) vboxtmp;

                    HBox hbox = (HBox) vboxcast.getChildren().get(0);
                    System.out.println(hbox.getChildren());
                    // Récupérer les champs du formulaire depuis lastHBox
                    TextField nomTextField = (TextField) hbox.getChildren().get(1);
                    TextField dureeTextField = (TextField) hbox.getChildren().get(3);
                    DatePicker datePicker = (DatePicker) hbox.getChildren().get(5);

                    String nom = nomTextField.getText();
                    double duree = Double.parseDouble(dureeTextField.getText());
                    LocalDate selectedDate = datePicker.getValue();
                    int jour = selectedDate.getDayOfMonth();
                    int mois = selectedDate.getMonthValue();
                    int annee = selectedDate.getYear();

                    // Créer une nouvelle sous-tâche pour chaque appel récursif
                    TacheMere sousTache = new TacheMere(nom, duree, jour, mois, annee);

                    // Ajouter la sous-tâche à la tâche mère
                    tacheMere.ajouterSousTache(sousTache);

                    // Appel récursif pour ajouter des sous-tâches à la sous-tâche actuelle
                    System.out.println(vbox);
                        ajouterSousTachesRecursive(sousTache, vboxcast);



                    Tableau t = (Tableau) sujet;
                    t.notifierObservateur();
                }
            }
        }






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
