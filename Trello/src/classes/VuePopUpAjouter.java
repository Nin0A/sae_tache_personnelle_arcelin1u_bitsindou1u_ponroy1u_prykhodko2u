package classes;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");
        Button ajouterSousTache = new Button("Ajouter une sous nouvelle sous tâche");

        annulerButton.setOnAction(event -> this.close());

        HBox buttonBox = new HBox(validerButton, annulerButton, ajouterSousTache);
        VBox vboxcontainer = new VBox();
        vbox.getChildren().addAll(buttonBox, vboxcontainer);

        ajouterSousTache.setOnAction(event -> creerFormSousTache(vboxcontainer, 1, 1));

        validerButton.setOnAction(event -> {
            String nom = nomTextField.getText();
            String duree = dureeTextField.getText();
            LocalDate selectedDate = datePicker.getValue();

            //on verifie que les champs de la tache mere et des sous taches sont remplis
            if (validerChampsTacheEtSousTaches(nom, duree, selectedDate, vbox, vboxcontainer)) {

                Tableau t = (Tableau) sujet;
                TacheMere tacheMere = new TacheMere(nom, colonneCourante, Double.parseDouble(duree),
                        selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear());
                System.out.println(t);
                t.ajouterTache(colonneCourante, tacheMere);
                System.out.println(t+" et "+colonneCourante+" et "+tacheMere);

                System.out.println(t.getColonneByName("Colonne").getTaches());

                if (vbox.getChildren().get(vbox.getChildren().size() - 1) instanceof VBox) {
                    ajouterSousTachesRecursive(tacheMere, vboxcontainer);
                }

                this.close();
                t.notifierObservateur();
            }

            //on verifie que les dates et la durée des sous taches sont valides

        });

        Scene scene = new Scene(vbox, 800, 550);
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

    public void creerFormSousTache(VBox vbox, int rangTache, int marge) {
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
            VBox vboxtmp = (VBox) ajouterSousTacheButton.getParent().getParent();
            vboxtmp.setSpacing(5);
            creerFormSousTache(vboxtmp, rangTache + 1, marge + 1);
        });

        Button supprimer = new Button("Supprimer");
        hbox.getChildren().add(supprimer);

        supprimer.setOnAction(event -> {
            HBox parentHBox = (HBox) supprimer.getParent();
            VBox parentVBox = (VBox) parentHBox.getParent();
            ((VBox) parentVBox.getParent()).getChildren().remove(parentVBox);
        });

        hbox.setId("#id" + rangTache);
        hbox.setPadding(new Insets(0, 0, 0, marge * 30));
        vBoxSousTache.getChildren().add(hbox);
        vbox.getChildren().add(vBoxSousTache);
    }

    /**
     * Méthode ajouterSousTachesRecursive qui ajoute des sous-tâches à une tâche mère
     * @param tacheMere la tâche mère à laquelle on veut ajouter des sous-tâches
     * @param vbox la vbox dans laquelle on veut ajouter les sous-tâches
     */
    private void ajouterSousTachesRecursive(TacheMere tacheMere, VBox vbox) {
        if (vbox != null) {
            for (Node vboxtmp : vbox.getChildren()) {
                if (vboxtmp instanceof VBox) {
                    VBox vboxcast = (VBox) vboxtmp;
                    HBox hbox = (HBox) vboxcast.getChildren().get(0);

                    TextField nomTextField = (TextField) hbox.getChildren().get(1);
                    TextField dureeTextField = (TextField) hbox.getChildren().get(3);
                    DatePicker datePicker = (DatePicker) hbox.getChildren().get(5);

                    if (validerChampsSousTache(nomTextField, dureeTextField, datePicker, vboxcast)) {
                        String nom = nomTextField.getText();
                        double duree = Double.parseDouble(dureeTextField.getText());
                        LocalDate selectedDate = datePicker.getValue();
                        int jour = selectedDate.getDayOfMonth();
                        int mois = selectedDate.getMonthValue();
                        int annee = selectedDate.getYear();
                        Colonne colonne = tacheMere.getColonneOrigine();

                        TacheMere sousTache = new TacheMere(nom, colonne, duree, jour, mois, annee);
                        tacheMere.ajouterSousTache(sousTache);

                        ajouterSousTachesRecursive(sousTache, vboxcast);

                        Tableau t = (Tableau) sujet;
                        t.notifierObservateur();
                    }
                }
            }
        }
    }


    /**
     * Méthode validerChamps qui vérifie que les champs du formulaire sont remplies
     * @param nom  nom de la tâche
     * @param duree durée de la tâche
     * @param selectedDate date de début de la tâche
     * @param vbox la vbox dans laquelle on veut ajouter les sous-tâches
     * @return true si les champs sont remplis, false sinon
     */
    private boolean validerChamps(String nom, String duree, LocalDate selectedDate, VBox vbox) {
        if (nom.isEmpty() || duree.isEmpty() || selectedDate == null) {
            Label erreurLabel = new Label("Veuillez remplir tous les champs !");
            erreurLabel.setStyle("-fx-text-fill: red");
            vbox.getChildren().add(erreurLabel);

            TextField nomTextField = (TextField) vbox.getChildren().get(0);
            TextField dureeTextField = (TextField) vbox.getChildren().get(1);
            DatePicker datePicker = (DatePicker) vbox.getChildren().get(2);

            // Efface le message d'erreur lorsque l'utilisateur commence à saisir de nouvelles données
            nomTextField.setOnKeyTyped(e -> vbox.getChildren().remove(erreurLabel));
            dureeTextField.setOnKeyTyped(e -> vbox.getChildren().remove(erreurLabel));
            datePicker.setOnAction(e -> vbox.getChildren().remove(erreurLabel));

            return false;
        }
        return true;
    }

    /**
     * Méthode validerChampsSousTache qui vérifie que les champs du formulaire de chaque sous tache sont remplies
     * @param nomTextField nom de la tâche
     * @param dureeTextField durée de la tâche
     * @param datePicker date de début de la tâche
     * @param vbox la vbox dans laquelle on veut ajouter les sous-tâches
     * @return true si les champs sont remplis, false sinon
     */
    private boolean validerChampsSousTache(TextField nomTextField, TextField dureeTextField, DatePicker datePicker, VBox vbox) {
        String nom = nomTextField.getText();
        String duree = dureeTextField.getText();
        LocalDate selectedDate = datePicker.getValue();

        if (nom.isEmpty() || duree.isEmpty() || selectedDate == null) {
            Label erreurLabel = new Label("Veuillez remplir tous les champs !");
            erreurLabel.setStyle("-fx-text-fill: red");
            vbox.getChildren().add(erreurLabel);

            // Efface le message d'erreur lorsque l'utilisateur commence à saisir de nouvelles données
            nomTextField.setOnKeyTyped(e -> vbox.getChildren().remove(erreurLabel));
            dureeTextField.setOnKeyTyped(e -> vbox.getChildren().remove(erreurLabel));
            datePicker.setOnAction(e -> vbox.getChildren().remove(erreurLabel));

            return false;
        }
        return true;
    }

    private boolean validerChampsSousTaches(VBox vbox) {
        for (Node vboxtmp : vbox.getChildren()) {
            if (vboxtmp instanceof VBox) {
                VBox vboxcast = (VBox) vboxtmp;
                HBox hbox = (HBox) vboxcast.getChildren().get(0);

                TextField nomTextField = (TextField) hbox.getChildren().get(1);
                TextField dureeTextField = (TextField) hbox.getChildren().get(3);
                DatePicker datePicker = (DatePicker) hbox.getChildren().get(5);

                if (!validerChampsSousTache(nomTextField, dureeTextField, datePicker, vboxcast)) {
                    return false; // Arrête la validation si un champ de sous-tâche est invalide
                }
            }
        }
        return true; // Tous les champs de sous-tâches sont valides
    }

    private boolean validerChampsTacheEtSousTaches(String nom, String duree, LocalDate selectedDate, VBox vbox, VBox vboxcontainer) {
        boolean champsTacheValides = validerChamps(nom, duree, selectedDate, vbox);
        boolean champsSousTachesValides = validerChampsSousTaches(vboxcontainer);

        return champsTacheValides && champsSousTachesValides;
    }

    /**
     * Méthode validerTimingSousTaches qui vérifie les dates de début, de fin et la durée des sous-tâches
     * @param vbox la vbox dans laquelle on veut ajouter les sous-tâches
     * @return true si les dates et la durée des sous-tâches sont valides, false sinon
     */
    private boolean validerTimingSousTaches(VBox vbox) {
        for (Node vboxtmp : vbox.getChildren()) {
            if (vboxtmp instanceof VBox) {
                VBox vboxcast = (VBox) vboxtmp;
                HBox hbox = (HBox) vboxcast.getChildren().get(0);

                TextField nomTextField = (TextField) hbox.getChildren().get(1);
                TextField dureeTextField = (TextField) hbox.getChildren().get(3);
                DatePicker datePicker = (DatePicker) hbox.getChildren().get(5);

                String nom = nomTextField.getText();
                String duree = dureeTextField.getText();
                LocalDate selectedDate = datePicker.getValue();

                if (nom.isEmpty() || duree.isEmpty() || selectedDate == null) {
                    return false;
                }

                int jour = selectedDate.getDayOfMonth();
                int mois = selectedDate.getMonthValue();
                int annee = selectedDate.getYear();

                if (!tacheMere.verifDateDebutSousTaches(null)) {
                    Label erreurLabel = new Label("La date de début de la sous-tâche " + nom + " est antérieure à la date de début de la tâche mère !");
                    erreurLabel.setStyle("-fx-text-fill: red");
                    vbox.getChildren().add(erreurLabel);
                    return false;
                }

                if (!tacheMere.verifDateFinSousTaches(null)) {
                    Label erreurLabel = new Label("La date de fin de la sous-tâche " + nom + " est supérieur à la date de fin de la tâche mère !");
                    erreurLabel.setStyle("-fx-text-fill: red");
                    vbox.getChildren().add(erreurLabel);
                    return false;
                }

                if (!tacheMere.verifDureeSousTaches(null)) {
                    Label erreurLabel = new Label("La durée de la sous-tâche " + nom + " est supérieure à la durée de la tâche mère !");
                    erreurLabel.setStyle("-fx-text-fill: red");
                    vbox.getChildren().add(erreurLabel);
                    return false;
                }

                if (!tacheMere.verifChevauche(null  )) {
                    return false;
                }
            }
        }
        return true;
    }

}