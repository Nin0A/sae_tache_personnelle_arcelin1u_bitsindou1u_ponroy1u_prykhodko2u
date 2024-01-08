package classes;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.Insets;

public class Principale extends Application {

        public static void main(String[] args) {
                launch(args);
        }

        public void start(Stage stage) {

                //test
                TacheMere t = new TacheMere("Tache t", 15, 1 , 1 , 2023);
                TacheMere s1 = new TacheMere("Tache s1", 5, 10, 1 ,2023);
                TacheMere s2 = new TacheMere("Tache s2", 1, 1, 1, 2023);
                TacheMere s3 = new TacheMere("Tache s3", 3, 5, 1, 2023);
                TacheMere s4 = new TacheMere("Tache s4", 3, 5, 1, 2023);
                TacheMere s5 = new TacheMere("Tache s5", 3, 5, 1, 2023);

                t.ajouterSousTache(s1);
                s1.ajouterSousTache(s2);
                s1.ajouterSousTache(s3);
                s2.ajouterSousTache(s4);
                s2.ajouterSousTache(s5);






        // Panel principal
        HBox pane = new HBox();
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-background-color: #f0f0f0;"); // Set background color

        // Zone de gauche (Tableau)
        VBox listeTableau = new VBox();
        listeTableau.setMinHeight(100);
        listeTableau.setMinWidth(250);
        listeTableau.setStyle("-fx-border-color: white; -fx-border-width: 5px;-fx-border-radius: 20px;");

        // Zone de droite
        VBox main = new VBox();
        main.setMinHeight(100);
        main.setMinWidth(1030);
        main.setStyle("-fx-border-color: white; -fx-border-width: 5px;-fx-border-radius: 20px;");
        main.setSpacing(20);
        main.setAlignment(Pos.TOP_RIGHT);

        ComboBox<String> choixDeVues = new ComboBox<>();
        choixDeVues.getItems().addAll("Vue Bureau", "Vue Liste", "Vue Gantt");
        choixDeVues.setValue("Vue Bureau");


        // Style avancé avec CSS
        choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #ffe1fd; " + // Couleur de fond
                "-fx-text-fill: #ff0000; " + // Couleur du texte
                "-fx-border-color: #555555; " + // Couleur de la bordure
                "-fx-border-width: 2px; "+
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
        );

        // Style pour le survol
        choixDeVues.setOnMouseEntered(e -> choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                "-fx-text-fill: white; " + // Nouvelle couleur du texte au survol
                "-fx-border-color: #555555; "+
                "-fx-border-width: 2px; "+
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 50px;"+
                "-fx-text-fill: white;"// Coin arrondi pour le fond// Nouvelle couleur de bordure au survol
        ));

        // Style par défaut après le survol
        choixDeVues.setOnMouseExited(e -> choixDeVues.setStyle(
                "-fx-font-size: 14px; " +
                "-fx-padding: 5px; " +
                "-fx-background-color: #ffe1fd; " + // Retour à la couleur de fond transparente
                "-fx-text-fill: #b07171; " + // Retour à la couleur du texte blanche
                "-fx-border-color: #555555; " + // Retour à la couleur de bordure initiale
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 50px;" + // Bordure arrondie
                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
        ));



////
                Tableau tab = new Tableau("Tableau");
                Colonne col = new Colonne("Colonne");
                Colonne col2 = new Colonne("Colonne2");
                Colonne col3 = new Colonne("Colonne3");
                tab.ajouterColonne(col);
                tab.ajouterColonne(col2);
                tab.ajouterColonne(col3);
                col.ajouterTache(t);


                Label nomTableauCourant = new Label(tab.getNom());
                nomTableauCourant.setStyle("-fx-font-family: Krungthep;-fx-font-size: 20;");

                HBox containerTop = new HBox(nomTableauCourant,choixDeVues);
                containerTop.setAlignment(Pos.CENTER_RIGHT);
                containerTop.setSpacing(40);
                main.getChildren().addAll(containerTop);
                main.setPadding(new Insets(20));
                //zone vue !!! à modifier selon la vue !!!

                Vue vue = new Vue(tab);
                vue.changerVue(choixDeVues.getValue());
                tab.notifierObservateur();
                ScrollPane scrollPanetmp = new ScrollPane((Node) vue.getCourant());
                scrollPanetmp.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPanetmp.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                scrollPanetmp.setPrefHeight(760);
                scrollPanetmp.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");
                main.getChildren().add(scrollPanetmp);


                choixDeVues.setOnAction(e -> {
                        ComboBox<String> cb = (ComboBox<String>) e.getSource();

                        // Supprimez l'ancien contenu
                        main.getChildren().removeIf(node -> node instanceof ScrollPane);

                        // Mettez à jour le contenu avec le nouveau choix
                        vue.changerVue(cb.getValue());
                        tab.notifierObservateur();

                        // Créez un nouveau ScrollPane avec le contenu actuel de vue.getCourant()
                        ScrollPane scrollPane = new ScrollPane((Node) vue.getCourant());
                        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
                        scrollPane.setPrefHeight(760);
                        scrollPane.setStyle("-fx-background-color: transparent; -fx-border-width: 0;");

                        // Ajoutez le nouveau contenu à main
                        //main.getChildren().add((Node) vue.getCourant());
                        // Ajoutez le ScrollPane à main
                        main.getChildren().add(scrollPane);

                });

                pane.setStyle(" -fx-background: linear-gradient(to bottom, #ffb6c4, #ba8ef7);");


        pane.getChildren().addAll(listeTableau,main);
        pane.setSpacing(5);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}

