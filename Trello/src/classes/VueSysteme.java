package classes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueSysteme extends VBox implements Observateur{
    /***
     * Méthode actualiser qui actualise la vue
     */
    @Override
    public void actualiser(Sujet sujet) {
        Systeme sys = (Systeme) sujet;
        System.out.println(sys.getTableaux().size());
        this.setSpacing(3);
        this.setPadding(new Insets(20));
        this.getChildren().clear();

        for(Tableau t : sys.getTableaux()){
            ControleurSysteme cs = new ControleurSysteme(sys, t);

            HBox zoneTab = new HBox();
            Button titre = new Button(t.getNom());
            titre.setStyle("-fx-font-size: 15");
            if(sys.getTableauCourant()==t){
                titre.setStyle("-fx-background-color: rgb(255,243,198);-fx-font-size: 15;-fx-border-width: 2px;-fx-border-color: transparent;");
                titre.setOnMouseEntered(e -> titre.setStyle(
                        "-fx-background-color: rgb(255,243,198);    -fx-font-size: 15;-fx-border-width: 2px;-fx-border-color: black;-fx-border-radius: 2px;"
                ));
                // Style par défaut après le survol
                titre.setOnMouseExited(e -> titre.setStyle(
                        "-fx-background-color: rgb(255,243,198);-fx-font-size: 15;-fx-border-width: 2px;-fx-border-color: transparent;"
                ));
            }else{
                titre.setStyle("-fx-border-width: 0;-fx-background-color: transparent;-fx-border-width: 2px;-fx-border-color: transparent;-fx-font-size: 15;");
                titre.setOnMouseEntered(e -> titre.setStyle(
                        "-fx-background-color: transparent;-fx-border-width: 2px;-fx-border-color: black;-fx-border-radius: 2px;-fx-font-size: 15;"
                ));
                // Style par défaut après le survol
                titre.setOnMouseExited(e -> titre.setStyle(
                        "-fx-background-color: transparent;-fx-border-width: 2px;-fx-border-color: transparent;-fx-font-size: 15;"
                ));
            }

            titre.setOnAction(e -> {
                sys.changerTableauCourrant(t);
            });


            HBox tmpTitre = new HBox();
            tmpTitre.getChildren().add(titre);
            tmpTitre.setAlignment(Pos.CENTER);
            this.getChildren().add(tmpTitre);



            Button modif = new Button("Modifier");
            modif.setOnAction(cs);
            zoneTab.getChildren().add(modif);
            modif.setStyle("-fx-background-color: transparent;");

            // Style pour le survol
            modif.setOnMouseEntered(e -> modif.setStyle(
                    "-fx-background-color: transparent;-fx-underline: true;"
            ));

            // Style par défaut après le survol
            modif.setOnMouseExited(e -> modif.setStyle(
                    "-fx-background-color: transparent;-fx-underline: false"
            ));

            Button suppr = new Button("Supprimer");

            suppr.setStyle("-fx-background-color: transparent;");

            suppr.setOnMouseEntered(e -> suppr.setStyle(
                    "-fx-background-color: transparent;-fx-underline: true"
            ));

            // Style par défaut après le survol
            suppr.setOnMouseExited(e -> suppr.setStyle(
                    "-fx-background-color: transparent;-fx-underline: false"
            ));
            suppr.setOnAction(cs);
            zoneTab.getChildren().add(suppr);
            zoneTab.setAlignment(Pos.BOTTOM_CENTER);
            this.getChildren().add(zoneTab);
        }
        // Créer une HBox pour centrer le bouton "Ajouter Tableau"
        HBox centerBox = new HBox();
        centerBox.setPadding(new Insets(30,0,0,0));
        Button ajouterTab = new Button("Ajouter Tableau");
        ajouterTab.setStyle("-fx-background-color:black;-fx-border-color: black;-fx-text-fill: white ;");

        ajouterTab.setOnMouseEntered(e -> ajouterTab.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-text-fill: black ;-fx-border-width: 2px;-fx-border-color: black;-fx-border-radius:5px"
        ));

        // Style par défaut après le survol
        ajouterTab.setOnMouseExited(e -> ajouterTab.setStyle(
                "-fx-background-color:black;-fx-border-color: black;-fx-text-fill: white ;"
        ));
        ajouterTab.setOnAction(new ControleurSysteme(sys, null));
        centerBox.getChildren().add(ajouterTab);

        // Centrer la HBox dans la VBox
        centerBox.setAlignment(Pos.CENTER);
        this.getChildren().add(centerBox);
    }
}
