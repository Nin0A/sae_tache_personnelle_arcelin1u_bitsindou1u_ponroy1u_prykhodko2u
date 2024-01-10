package classes;

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

public class VuePopUpModifier extends Stage implements Observateur {
    private Tache tache;
    private Tableau tableau;

    VuePopUpModifier(Sujet sujet, Tache tache) {
        this.tache = tache;
        this.tableau = (Tableau) sujet;
    }

    @Override
    public void actualiser(Sujet sujet) {
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));

        // Create text fields for task details
        TextField nomTextField = new TextField();
        nomTextField.setText(tache.getNom());
        Label nomLabel = new Label("Nom :");

        HBox hbox1 = new HBox();
        hbox1.getChildren().addAll(nomLabel, nomTextField);
        vbox.getChildren().add(hbox1);

        TextField dureeTextField = new TextField();
        dureeTextField.setText("" + tache.getDuree());
        Label dureeLabel = new Label("Durée :");
        HBox hbox2 = new HBox();
        hbox2.getChildren().addAll(dureeLabel, dureeTextField);
        vbox.getChildren().add(hbox2);

        DatePicker datePicker = new DatePicker();
        // Create an instance of LocalDate with your variables
        LocalDate date = LocalDate.of(tache.getDateDebut().getYear(), tache.getDateDebut().getMonth(), tache.getDateDebut().getDayOfMonth());

        // Set the date on the DatePicker
        datePicker.setValue(date);

        Label dateLabel = new Label("Date :");
        HBox hbox3 = new HBox();
        hbox3.getChildren().addAll(dateLabel, datePicker);
        vbox.getChildren().add(hbox3);

        // Display existing subtasks with text fields for modification
        VBox vboxcontainer = new VBox();
        vboxcontainer.setStyle("-fx-border-color: black");
        displaySubtasks(tache, vboxcontainer,1,1);

        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");

        validerButton.setOnAction(event -> {
            tache.setNom(nomTextField.getText());
            tache.setDuree(Double.parseDouble(dureeTextField.getText()));
            tache.setDate(datePicker.getValue());

            // Update the names of existing subtasks
            majSousTachesRecursive((TacheMere) tache, vbox);

            // Close the pop-up window after validation
            this.close();
            sujet.notifierObservateur();
        });

        annulerButton.setOnAction(event -> {
            this.close();
        });

        HBox buttonBox = new HBox(validerButton, annulerButton);
        vbox.getChildren().addAll(buttonBox,vboxcontainer);

        Scene scene = new Scene(vbox, 700, 350);
        this.setScene(scene);
        this.show();
    }

    private void displaySubtasks(Tache tache, VBox container,int rangTache, int marge) {
        //création

        if (tache instanceof TacheMere) {
            TacheMere tacheMere = (TacheMere) tache;
            for (Tache sousTache : tacheMere.getSousTaches()) {
                VBox vBoxSousTachetmp = new VBox();
                HBox sousTacheBox = new HBox();

                System.out.println(rangTache);
                sousTacheBox.setId("#id"+rangTache);
                sousTacheBox.setPadding(new Insets(0, 0, 0, marge*30));

                Label sousTacheLabel = new Label("Sous-tâche :");
                TextField sousTacheTextField = new TextField(sousTache.getNom());

                sousTacheBox.getChildren().addAll(sousTacheLabel, sousTacheTextField);
                //

                //champ durée
                TextField dureeTextField = new TextField();
                Label dureeLabel = new Label("Durée :");
                dureeTextField.setText("" + sousTache.getDuree());
                sousTacheBox.getChildren().addAll(dureeLabel, dureeTextField);

                //champ dateDébut
                DatePicker datePicker = new DatePicker();
                Label dateLabel = new Label("Date :");
                datePicker.setValue(sousTache.getDateDebut());
                sousTacheBox.getChildren().addAll(dateLabel, datePicker);


                Label id = new Label(""+sousTache.getId());
                sousTacheBox.getChildren().add(id);

                //button ajouter sous tache
                Button ajouterSousTacheButton = new Button("Ajouter Sous-Tâche");
                sousTacheBox.getChildren().add(ajouterSousTacheButton);

                ajouterSousTacheButton.setOnAction(event -> {
                    VBox vboxtmp=(VBox)ajouterSousTacheButton.getParent().getParent();
                    vboxtmp.setSpacing(5);
                    creerFormSousTache(vboxtmp,2,3);
                });


                Button supprimer = new Button("Supprimer");
                sousTacheBox.getChildren().add(supprimer);

                supprimer.setOnAction(event -> {


                    // Obtenez le parent du bouton (HBox)
                    HBox parentHBox = (HBox) supprimer.getParent();

                    // Obtenez le parent du HBox (VBox)
                    VBox parentVBox = (VBox) parentHBox.getParent();

                    // Supprimez le VBox du parent du VBox
                    ((VBox) parentVBox.getParent()).getChildren().remove(parentVBox);

                });

                vBoxSousTachetmp.getChildren().add(sousTacheBox);
                vBoxSousTachetmp.setStyle("-fx-border-color: black");
                container.getChildren().add(vBoxSousTachetmp);

                // Recursively display sub-subtasks
                displaySubtasks(sousTache, container,rangTache+1,marge+1);
            }
        }
    }

    private void majSousTachesRecursive(TacheMere tacheMere, VBox vbox) {
        if (vbox != null) {
            for (Node vboxtmp : vbox.getChildren()) {
                if (vboxtmp instanceof VBox) {
                    VBox vboxcast = (VBox) vboxtmp;

                    HBox hbox = (HBox) vboxcast.getChildren().get(0);

                    // Récupérer les champs du formulaire depuis lastHBox
                    TextField nomTextField = (TextField) hbox.getChildren().get(1);
                    TextField dureeTextField = (TextField) hbox.getChildren().get(3);
                    DatePicker datePicker = (DatePicker) hbox.getChildren().get(5);
                    Label idLabel = (Label) hbox.getChildren().get(6);

                    // Obtenez l'indice de la sous-tâche dans la liste des sous-tâches de la tâche mère
                    int index = vbox.getChildren().indexOf(vboxtmp);

                    // Vérifiez si la sous-tâche existe déjà en comparant l'ID
                    if (Integer.parseInt(idLabel.getText()) > 0) {
                        // La sous-tâche existe, mettez à jour ses propriétés
                        TacheMere sousTache = (TacheMere) tacheMere.getSousTaches().get(index);
                        sousTache.setNom(nomTextField.getText());
                        sousTache.setDuree(Double.parseDouble(dureeTextField.getText()));
                        sousTache.setDate(datePicker.getValue());

                        // Appel récursif pour traiter les sous-sous-tâches
                        majSousTachesRecursive(sousTache, vboxcast);
                    } else {
                        // La sous-tâche n'existe pas ou n'a pas d'ID, créez une nouvelle sous-tâche
                        TacheMere nouvelleSousTache = new TacheMere(nomTextField.getText(), tacheMere.getColonneOrigine(), Double.parseDouble(dureeTextField.getText()),datePicker.getValue().getDayOfMonth(),datePicker.getValue().getMonthValue(),datePicker.getValue().getYear() ); // Assurez-vous d'ajuster la création en fonction de votre modèle
                        nouvelleSousTache.setNom(nomTextField.getText());
                        nouvelleSousTache.setDuree(Double.parseDouble(dureeTextField.getText()));
                        nouvelleSousTache.setDate(datePicker.getValue());

                        // Ajoutez la nouvelle sous-tâche à la liste des sous-tâches de la tâche mère
                        tacheMere.ajouterSousTache(nouvelleSousTache);

                        // Appel récursif pour traiter les sous-sous-tâches
                        majSousTachesRecursive(nouvelleSousTache, vboxcast);

                        // Notifiez les observateurs après l'ajout de la nouvelle sous-tâche
                        tableau.notifierObservateur();
                    }
                }
            }
        }
    }




    public void creerFormSousTache(VBox vbox,int rangTache,int marge) {
        //création
        VBox vBoxSousTache = new VBox();
        //vBoxSousTache.setStyle("-fx-border-color: black ");
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

        Label id = new Label("0");
        hbox.getChildren().add(id);

        //button ajouter sous tache
        Button ajouterSousTacheButton = new Button("Ajouter Sous-Tâche");
        hbox.getChildren().add(ajouterSousTacheButton);

        ajouterSousTacheButton.setOnAction(event -> {
            VBox vboxtmp=(VBox)ajouterSousTacheButton.getParent().getParent();
            vboxtmp.setSpacing(5);
            creerFormSousTache(vboxtmp,rangTache+1,marge+1);  // Appel récursif pour créer des sous-tâches de la sous-tâche
        });

        Button supprimer = new Button("Supprimer");
        hbox.getChildren().add(supprimer);

        supprimer.setOnAction(event -> {


            // Obtenez le parent du bouton (HBox)
            HBox parentHBox = (HBox) supprimer.getParent();

            // Obtenez le parent du HBox (VBox)
            VBox parentVBox = (VBox) parentHBox.getParent();

            // Supprimez le VBox du parent du VBox
            ((VBox) parentVBox.getParent()).getChildren().remove(parentVBox);

        });


        System.out.println(rangTache);
        hbox.setId("#id"+rangTache);



        hbox.setPadding(new Insets(0, 0, 0, marge*30));
        vBoxSousTache.getChildren().add(hbox);
        vbox.getChildren().add(vBoxSousTache);

    }

}
