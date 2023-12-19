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
        TacheMere t = new TacheMere("Tache t", 15, 1,1,2023);
        TacheMere tt = new TacheMere("Tache tttt", 15, 1,1,2023);
        TacheMere st = new TacheMere("Soustache s1", 13, 1,1,2023);
        TacheMere s = new TacheMere("ss1", 7, 3,1,2023);
        TacheMere s2 = new TacheMere("ss2", 2, 10,1,2023);
        TacheMere s3 = new TacheMere("ss3", 2, 12,1,2023);
        SousTache ss = new SousTache("sss1", 2, 3,1,2023);
        SousTache ss2 = new SousTache("sss2", 5, 5,1,2023);
        TacheMere t2 = new TacheMere("Tache t2", 10, 27,2,2023);
        TacheMere t3 = new TacheMere("Tache t3", 10, 10,3,2023);

        t.ajouterSousTache(st);
        st.ajouterSousTache(s);
        st.ajouterSousTache(s2);
        st.ajouterSousTache(s3);
        s.ajouterSousTache(ss);
        s.ajouterSousTache(ss2);





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
//        col.ajouterTache(tt);
        col.ajouterTache(t2);
        col2.ajouterTache(t3);

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

        //zone vue !!! à modifier selon la vue !!!


        VueGantt vb =new VueGantt();
        vb.actualiser(tab);

        ScrollPane scrollPane = new ScrollPane(vb);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Barre de défilement horizontale selon les besoins


        main.getChildren().addAll(scrollPane);

        pane.getChildren().addAll(listeTableau,main);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}
