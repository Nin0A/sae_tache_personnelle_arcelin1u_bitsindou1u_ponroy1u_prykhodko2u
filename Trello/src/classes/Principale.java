package classes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.effect.GaussianBlur;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Principale extends Application {

        public static void main(String[] args) {
                launch(args);
        }
        /**
         * Méthode start qui lance l'application
         * @param stage stage de l'application
         */

        public final static Systeme system = new Systeme();
        public void start(Stage stage) {
                //initialisation


                VueSysteme vueSysteme = new VueSysteme();
                system.enregistrerObservateur(vueSysteme);
                Tableau tab2 = new Tableau("Tableau");
                system.ajouterTab(tab2);

        // Panel principal
        HBox pane = new HBox();
        pane.setPadding(new Insets(10));

        // Zone de gauche (Tableau)

        vueSysteme.setMinHeight(100);
        vueSysteme.setMinWidth(250);
        vueSysteme.setStyle("-fx-background-color: #a698da;-fx-background-radius: 20px;-fx-border-color: white; -fx-border-width: 5px;-fx-border-radius: 10px;");

        // Zone de droite
        VBox main = new VBox();
        main.setMinHeight(100);
        main.setMinWidth(1030);
        main.setStyle("-fx-background-color: #c3b0ff;-fx-background-radius: 20px;-fx-border-color: #ffffff; -fx-border-width: 5px;-fx-border-radius: 10px;");
        main.setSpacing(20);
        main.setAlignment(Pos.TOP_RIGHT);


        ComboBox<String> choixDeVues = new ComboBox<>();
        choixDeVues.getItems().addAll("Vue Bureau", "Vue Liste", "Vue Gantt","Vue Archive");
        // Valeur par défaut
        choixDeVues.setValue("Vue Bureau");


        // Style avancé avec CSS
        choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #ffead9; " +
                "-fx-border-color: #ffffff; " + // Couleur de la bordure
                "-fx-border-width: 3px; "+
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 40px;" // Coin arrondi pour le fond
        );

        // Style pour le survol
        choixDeVues.setOnMouseEntered(e -> choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #ffffff; " + // Nouvelle couleur de fond au survol
                "-fx-border-color: #ffead9; "+
                "-fx-border-width: 3px; "+
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 50px;"+
                "-fx-text-fill: white;"// Coin arrondi pour le fond// Nouvelle couleur de bordure au survol
        ));

        // Style par défaut après le survol
        choixDeVues.setOnMouseExited(e -> choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #ffead9; " + // Retour à la couleur de fond transparente
                "-fx-border-color: #ffffff; " + // Retour à la couleur de bordure initiale
                "-fx-border-width: 3px; " +
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
        ));



                VueNomTableau nomTableauCourant = new VueNomTableau(system);
                system.enregistrerObservateur(nomTableauCourant);


                HBox containerTop = new HBox(nomTableauCourant,choixDeVues);
                containerTop.setAlignment(Pos.CENTER_RIGHT);
                containerTop.setSpacing(40);
                main.getChildren().addAll(containerTop);
                main.setPadding(new Insets(20));

                choixDeVues.setValue("Home");


                //Vue
                Vue vue = new Vue(system);
                system.enregistrerObservateur(vue);

                choixDeVues.setOnAction(e -> {
                        system.notifierObservateur();
                        ComboBox<String> cb = (ComboBox<String>) e.getSource();
                        // Supprimez l'ancien contenu
                        main.getChildren().removeIf(node -> node instanceof ScrollPane);

                        // Mettez à jour le contenu avec le nouveau choix
                        vue.changerVue(cb.getValue());
                        Principale.system.notifierObservateur();

                        vue.setStyle("-fx-background-color: transparent");


                        // Créez un nouveau ScrollPane avec le contenu actuel de vue
                        ScrollPane scrollPane = new ScrollPane(vue);


                        scrollPane.setStyle("-fx-background-color:#c3b0ff ;-fx-background:transparent;-fx-border-radius:5000px;-fx-border-width: 20px");

                        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                        scrollPane.setPrefHeight(760);
                        main.getChildren().add(scrollPane);
                });


        pane.getChildren().addAll(vueSysteme,main);
        pane.setSpacing(5);
        pane.setStyle("-fx-background-color: #ffcdf8");
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire de tâches personnelles");
        stage.show();
        }

}

