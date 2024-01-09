package classes;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class VueSysteme extends VBox implements Observateur{

    @Override
    public void actualiser(Sujet sujet) {
        Systeme sys = (Systeme) sujet;
        System.out.println(sys.getTableaux().size());
        this.setSpacing(10);
        this.setPadding(new Insets(20));
        this.getChildren().clear();

        for(Tableau t : sys.getTableaux()){
            ControleurSysteme cs = new ControleurSysteme(sys, t);

            HBox zoneTab = new HBox();
            Button titre = new Button(t.getNom());
            titre.setStyle("-fx-font-size: 15");
            if(sys.getTableauCourant()==t){
                titre.setStyle("-fx-background-color: rgb(249,227,255);-fx-font-size: 15;");
            }

            titre.setOnAction(e -> {
                sys.changerTableauCourrant(t);
                sys.notifierObservateur();
            });
            this.getChildren().add(titre);


            Button modif = new Button("Modifier");
            modif.setOnAction(cs);
            zoneTab.getChildren().add(modif);

            // Style pour le survol
            modif.setOnMouseEntered(e -> modif.setStyle(
                    "-fx-underline: true"
            ));

            // Style par défaut après le survol
            modif.setOnMouseExited(e -> modif.setStyle(
                    "-fx-underline: false"
            ));

            Button suppr = new Button("Supprimer");

            suppr.setOnMouseEntered(e -> suppr.setStyle(
                    "-fx-underline: true"
            ));

            // Style par défaut après le survol
            suppr.setOnMouseExited(e -> suppr.setStyle(
                    "-fx-underline: false"
            ));
            suppr.setOnAction(cs);
            zoneTab.getChildren().add(suppr);
            this.getChildren().add(zoneTab);
        }
        // Créer une HBox pour centrer le bouton "Ajouter Tableau"
        HBox centerBox = new HBox();
        Button ajouterTab = new Button("Ajouter Tableau");
        ajouterTab.setStyle("-fx-border-color: black");

        ajouterTab.setOnMouseEntered(e -> ajouterTab.setStyle(
                "-fx-background-color:black;" +
                        "-fx-text-fill: white ;"
        ));

        // Style par défaut après le survol
        ajouterTab.setOnMouseExited(e -> ajouterTab.setStyle(
                "-fx-background-color:transparent;" +
                        "-fx-border-color: black"
        ));
        ajouterTab.setOnAction(new ControleurSysteme(sys, null));
        centerBox.getChildren().add(ajouterTab);

        // Centrer la HBox dans la VBox
        centerBox.setAlignment(Pos.CENTER);
        this.getChildren().add(centerBox);
    }
}
