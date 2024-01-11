package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VuePopUpModifier extends Stage implements Observateur {
    private TacheMere tache;
    private Tableau tableau;


    private Colonne colonne;

    VuePopUpModifier(Sujet sujet, TacheMere tache) {

        this.tache = tache;
        this.tableau = (Tableau) sujet;
        this.colonne=tache.getColonneOrigine();
    }
    /**
     * Méthode actualiser d'ouvrir une pop up pour modifier une tache
     * @param sujet sujet à actualiser
     */
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

        //ajouter Antécédents
        Button ajouterAntecedentsButton = new Button("Ajouter Antécédents");
        ajouterAntecedentsButton.setOnAction(event -> {
            ArrayList liste;
            TacheMere tm= this.colonne.getTacheMere(tache, colonne.getTaches());
            if(tm==null){
                liste = new ArrayList();
                for (Colonne c : this.tableau.getColonnes()) {
                        liste.addAll(c.getTaches());
                }
            }else{
                liste = tm.getSousTaches();
            }
            VueChoixAntecedents vueChoixAntecedents = new VueChoixAntecedents(liste, tache);
            vueChoixAntecedents.actualiser(tableau);
        });
        vbox.getChildren().add(ajouterAntecedentsButton);


        // Display existing subtasks with text fields for modification
        VBox vboxcontainer = new VBox();
        displaySubtasks(tache, vboxcontainer,1,1);

        Button validerButton = new Button("Valider");
        Button annulerButton = new Button("Annuler");
        Button ajouterSousTache = new Button("Ajouter une sous nouvelle sous tâche");

        ajouterSousTache.setOnAction(event -> creerFormSousTache(vboxcontainer, 1, 1));
        validerButton.setOnAction(event -> {
            tache.setNom(nomTextField.getText());
            tache.setDuree(Double.parseDouble(dureeTextField.getText()));
            tache.setDate(datePicker.getValue());

            // Update the names of existing subtasks
            majSousTachesRecursive(tache, vboxcontainer);

            // Close the pop-up window after validation
            this.close();
            sujet.notifierObservateur();
            Principale.system.notifierObservateur();
        });

        annulerButton.setOnAction(event -> {
            this.close();
        });

        HBox buttonBox = new HBox(validerButton, annulerButton,ajouterSousTache);
        vbox.getChildren().addAll(buttonBox,vboxcontainer);
        ScrollPane sp = new ScrollPane(vbox);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        Scene scene = new Scene(sp, 900, 500);
        this.setScene(scene);
        this.setTitle("Modifier Tâche");
        this.show();
    }
    /**
     * Méthode displaySubtasks d'afficher les sous taches
     * @param tache tache à afficher
     * @param container container à afficher
     * @param rangTache rang de la tache
     * @param marge marge de la tache
     */
    private void displaySubtasks(Tache tache, VBox container,int rangTache, int marge) {
        //création

        if (tache instanceof TacheMere) {
            TacheMere tacheMere = (TacheMere) tache;
            for (Tache sousTache : tacheMere.getSousTaches()) {
                VBox vBoxSousTachetmp = new VBox();
                HBox sousTacheBox = new HBox();

                sousTacheBox.setId("#id"+rangTache);
                sousTacheBox.setPadding(new Insets(0, 0, 0, marge*30));
                sousTacheBox.setAlignment(Pos.CENTER);
                sousTacheBox.setSpacing(3);

                Label sousTacheLabel = new Label("Nom :");
                TextField sousTacheTextField = new TextField(sousTache.getNom());

                sousTacheBox.getChildren().addAll(VueBureau.createArrow(),sousTacheLabel, sousTacheTextField);
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

                Label id = new Label(String.valueOf(sousTache.getId()));
                id.setVisible(false);
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
                container.getChildren().add(vBoxSousTachetmp);

                // Recursively display sub-subtasks
                displaySubtasks(sousTache, container,rangTache+1,marge+1);
            }
        }
    }
    /**
     * Méthode majSousTachesRecursive de mettre à jour les sous taches
     * @param tacheMere tache mère des sousTaches à mettre à jour
     * @param vbox vbox à mettre à jour
     */
    private void majSousTachesRecursive(TacheMere tacheMere, VBox vbox) {
        if (vbox != null) {
            for (Node vboxtmp : vbox.getChildren()) {
                if (vboxtmp instanceof VBox) {
                    VBox vboxcast = (VBox) vboxtmp;

                    HBox hbox = (HBox) vboxcast.getChildren().get(0);

                    // Récupérer les champs du formulaire depuis lastHBox
                    TextField nomTextField = (TextField) hbox.getChildren().get(2);
                    TextField dureeTextField = (TextField) hbox.getChildren().get(4);
                    DatePicker datePicker = (DatePicker) hbox.getChildren().get(6);

                    Label idLabel = (Label) hbox.getChildren().get(7);


                    // Vérifiez si la sous-tâche existe déjà en comparant l'ID
                    if (Integer.parseInt(idLabel.getText()) > -1) {
                        // La sous-tâche existe, mettez à jour ses propriétés

                        TacheMere sousTache =  (TacheMere) tacheMere.tacheById(Integer.parseInt(idLabel.getText()));

                        sousTache.setNom(nomTextField.getText());
                        sousTache.setDuree(Double.parseDouble(dureeTextField.getText()));
                        sousTache.setDate(datePicker.getValue());

                        // Appel récursif pour traiter les sous-sous-tâches
                        majSousTachesRecursive(sousTache, vboxcast);
                    } else {
                        TacheMere nouvelleSousTache = new TacheMere(nomTextField.getText(),colonne,Double.parseDouble(dureeTextField.getText()),datePicker.getValue().getDayOfMonth(),datePicker.getValue().getMonthValue(),datePicker.getValue().getYear()); // Assurez-vous d'ajuster la création en fonction de votre modèle
                        nouvelleSousTache.setNom(nomTextField.getText());
                        nouvelleSousTache.setDuree(Double.parseDouble(dureeTextField.getText()));
                        nouvelleSousTache.setDate(datePicker.getValue());

                        tacheMere.ajouterSousTache(nouvelleSousTache);

                        // Appel récursif pour traiter les sous-sous-tâches
                        majSousTachesRecursive(nouvelleSousTache, vboxcast);

                    }
                }
            }
        }
        // Notifiez les observateurs après l'ajout de la nouvelle sous-tâche
        tableau.notifierObservateur();
    }



    /** Méthode creerFormSousTache de créer un formulaire pour une sous tache
     * @param vbox vbox à mettre à jour
     * @param rangTache rang de la tache
     * @param marge marge de la tache
     */
    public void creerFormSousTache(VBox vbox,int rangTache,int marge) {
        //création
        VBox vBoxSousTache = new VBox();

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(3);

        //champ nom
        TextField nomTextField = new TextField();
        Label nomLabel = new Label("Nom :");
        hbox.getChildren().addAll(VueBureau.createArrow(),nomLabel, nomTextField);

        //champ durée
        TextField dureeTextField = new TextField();
        Label dureeLabel = new Label("Durée :");
        hbox.getChildren().addAll(dureeLabel, dureeTextField);

        //champ dateDébut
        DatePicker datePicker = new DatePicker();
        Label dateLabel = new Label("Date :");
        hbox.getChildren().addAll(dateLabel, datePicker);

        Label id = new Label("-1");
        id.setVisible(false);
        hbox.getChildren().add(id);

        //button ajouter sous tache
        Button ajouterSousTacheButton = new Button("Ajouter Sous-Tâche");
        hbox.getChildren().add(ajouterSousTacheButton);

        ajouterSousTacheButton.setOnAction(event -> {
            VBox vboxtmp=(VBox)ajouterSousTacheButton.getParent().getParent();
            vboxtmp.setSpacing(5);
            creerFormSousTache(vboxtmp,rangTache+1,marge+1);  // Appel récursif pour créer des sous-tâches de la sous-tâche
        });
        //ajouter antécédents

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

        hbox.setId("#id"+rangTache);



        hbox.setPadding(new Insets(0, 0, 0, marge*30));
        vBoxSousTache.getChildren().add(hbox);
        vbox.getChildren().add(vBoxSousTache);

    }

}
