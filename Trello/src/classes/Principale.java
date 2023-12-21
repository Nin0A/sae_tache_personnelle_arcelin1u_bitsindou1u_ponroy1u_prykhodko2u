package classes;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
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
                TacheMere s = new TacheMere("Tache s", 5, 10, 1 ,2023);
                TacheMere s2 = new TacheMere("Tache s2", 3, 5, 1, 2023);
                TacheMere s3 = new TacheMere("Tache s3", 10, 4, 2, 2023);
                TacheMere s4 = new TacheMere("Tache s4", 10, 10, 3, 2023);
                TacheMere t2 = new TacheMere("Tache t2", 10, 2, 1, 2023);
                SousTache st = new SousTache("Soustache st", 10, 1 , 1, 2023);
                t.ajouterSousTache(st);
                t.ajouterSousTache(s);
                s.ajouterSousTache(s2);

                s4.ajouterAntecedent(s3);
                s4.ajouterAntecedent(t);
                t2.ajouterAntecedent(t);


        // Panel principal
        HBox pane = new HBox();
        pane.setPadding(new Insets(10));
        pane.setStyle("-fx-background-color: #f0f0f0;"); // Set background color

        // Zone de gauche (Tableau)
        VBox listeTableau = new VBox();
        listeTableau.setMinHeight(100);
        listeTableau.setMinWidth(250);
        listeTableau.setStyle("-fx-border-color: white; -fx-border-width: 5px;-fx-border-radius: 50px;");

        // Zone de droite
        VBox main = new VBox();
        main.setMinHeight(100);
        main.setMinWidth(1000);
        main.setStyle("-fx-border-color: white; -fx-border-width: 5px;-fx-border-radius: 50px;");

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


        main.getChildren().addAll(choixDeVues);
        main.setPadding(new Insets(20));
////
                Tableau tab = new Tableau("Tableau");
                Colonne col = new Colonne("Colonne");
                Colonne col2 = new Colonne("Colonne2");
                Colonne col3 = new Colonne("Colonne3");
                Colonne col4 = new Colonne("Colonne4");
                Colonne col5 = new Colonne("Colonne5");
                tab.ajouterColonne(col);
                tab.ajouterColonne(col2);
                tab.ajouterColonne(col3);
                tab.ajouterColonne(col4);
                tab.ajouterColonne(col5);
                col.ajouterTache(t);
                col.ajouterTache(t2);
                col.ajouterTache(s3);
                col2.ajouterTache(s4);



////


                //zone vue !!! à modifier selon la vue !!!

                Vue vue = new Vue(tab);


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

                        // Ajoutez le nouveau contenu à main
                        //main.getChildren().add((Node) vue.getCourant());
                        // Ajoutez le ScrollPane à main
                        main.getChildren().add(scrollPane);

                });


        pane.setStyle(" -fx-background: linear-gradient(to bottom, #ffb6c4, #ba8ef7);");
        pane.getChildren().addAll(listeTableau,main);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}

