package classes;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
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
        Tableau tab = new Tableau("Tableau");
        TacheMere t = new TacheMere("Tache t", 7);
        TacheMere s = new TacheMere("Tache s", 10);
        TacheMere s2 = new TacheMere("Tache s2", 10);
        TacheMere s3 = new TacheMere("Tache s3", 10);
        TacheMere s4 = new TacheMere("Tache s4", 10);
        SousTache st = new SousTache("Soustache st", 10);
        t.ajouterSousTache(st);
        t.ajouterSousTache(s);
        s.ajouterSousTache(s2);

        Colonne col = new Colonne("Colonne");
        Colonne col2 = new Colonne("Colonne2");
        tab.ajouterColonne(col);
        tab.ajouterColonne(col2);
        col.ajouterTache(t);
        col.ajouterTache(s3);
        col2.ajouterTache(s4);

        // Panel principal
        HBox pane= new HBox();
        pane.setPadding(new Insets(10));

        //zone de gauche (Tableau)
        VBox listeTableau = new VBox();
        listeTableau.setPadding(new Insets(100));
        listeTableau.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        //zone de droite
        VBox main = new VBox();
        main.setPadding(new Insets(0,30,40,40));
        main.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");


        ComboBox <String> choixDeVues = new ComboBox();
        choixDeVues .getItems().add("Vue Tableau");
        choixDeVues .getItems().add("Vue Liste");
        choixDeVues .getItems().add("Vue Gantt");
        choixDeVues .setValue("Vue Tableau");

        main.getChildren().addAll(choixDeVues);


        //zone vue
        //HBox vue = new HBox();
        VueListe vue = new VueListe();
        vue.actualiser(tab);
        main.getChildren().add(vue);

        //tab.enregistrerObservateur(vue);
        //tab.notifierObservateur();

        pane.getChildren().addAll(listeTableau,main);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}
