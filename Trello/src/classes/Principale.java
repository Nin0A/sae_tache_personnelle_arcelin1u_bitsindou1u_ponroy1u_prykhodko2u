package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.Insets;

import java.security.Key;
public class Principale extends Application{

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {

        //test
        TacheMere t = new TacheMere("Tache t", 7);
        TacheMere s = new TacheMere("Tache s", 10);
        TacheMere s2 = new TacheMere("Tache s2", 10);
        TacheMere s3 = new TacheMere("Tache s3", 10);
        TacheMere s4 = new TacheMere("Tache s4", 10);
        SousTache st = new SousTache("Soustache st", 10, t);
        t.ajouterSousTache(st);
        t.ajouterSousTache(s);
        s.ajouterSousTache(s2);

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
        col.ajouterTache(s3);
        col2.ajouterTache(s4);

        // Panel principal
        HBox pane= new HBox();
        pane.setPadding(new Insets(10));

        //zone de gauche (Tableau)
        VBox listeTableau = new VBox();
        listeTableau.setMinHeight(100); //dimensions
        listeTableau.setMinWidth(250);
        listeTableau.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        //zone de droite
        VBox main = new VBox();
        main.setMinHeight(100);
        main.setMinWidth(800);
        main.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");


        ComboBox <String> choixDeVues = new ComboBox();
        choixDeVues .getItems().add("Vue Tableau");
        choixDeVues .getItems().add("Vue Liste");
        choixDeVues .getItems().add("Vue Gantt");
        choixDeVues .setValue("Vue Tableau");
        main.getChildren().addAll(choixDeVues);



        //zone vue !!! à modifier selon la vue !!!
        VueListe vue = new VueListe();
        //VueBureau vue =new VueBureau();
        tab.enregistrerObservateur(vue);
        tab.notifierObservateur();

        ScrollPane scrollPane = new ScrollPane(vue);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Barre de défilement horizontale selon les besoins


        main.getChildren().addAll(scrollPane);

        pane.getChildren().addAll(listeTableau,main);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}
