package classes;

import javafx.application.Application;
import javafx.scene.Scene;
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

        // Panel principal
        HBox pane= new HBox();
        pane.setPadding(new Insets(10));

        //zone de gauche (Tableau)
        VBox listeTableau = new VBox();
        listeTableau.setPadding(new Insets(100));
        listeTableau.setStyle("-fx-border-color: black; -fx-border-width: 2px;");

        //zone de droite
        VBox main = new VBox();
        main.setPadding(new Insets(50,30,40,40));
        main.setStyle("-fx-border-color: blue; -fx-border-width: 2px;");

        //zone vue
        HBox vue = new HBox();
        vue.setPadding(new Insets(680,1000,0,0));
        vue.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        main.getChildren().addAll(vue);

        pane.getChildren().addAll(listeTableau,main);
        Scene scene = new Scene(pane, 1300, 800);
        stage.setScene(scene);
        stage.setTitle("Gestionnaire Tâche Personnelle");
        stage.show();
    }
}
