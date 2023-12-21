package classes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class VueBureau extends HBox implements Observateur {

    //POUR L'ITERATION 2 /!\
        /**
        * Méhtode actualiser qui permet d'actualiser le sujet colonnes par colonnes
        * @param sujet sujet à actualiser
        */
        @Override
        public void actualiser(Sujet sujet) {
            Tableau tab = (Tableau) sujet;
            this.getChildren().clear();
            /*
            System.out.println("VueBureau : " + tab.getNom());

            ArrayList<Colonne> colonnes = tab.getColonnes();

            //on parcourt chaque colonne
            for (Colonne colonne : colonnes) {
                System.out.println(colonne.getNom() + " :"+"\t"); //on met un tab entre chaque colonne
            }
            */
            this.setPadding(new Insets(0,0,0,0));
            this.setStyle("-fx-border-color: red; -fx-border-width: 2px;");

            VBox colonnetmp;

            for(int i=0;i<tab.getColonnes().size();i++){
                colonnetmp= new VBox();
                colonnetmp.setStyle("-fx-border-color: purple; -fx-border-width: 2px;");
                colonnetmp.setPadding(new Insets(50));
                HBox zoneHauteColonne = new HBox();

                Label titreColonne = new Label(tab.getColonnes().get(i).getNom());

                titreColonne.setStyle("-fx-font-family: 'Arial';" +
                                        "-fx-font-size: 20;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-text-fill: blue;");

                zoneHauteColonne.getChildren().addAll(titreColonne,new Button("Modifier"),new Button("Supprimer"));

                colonnetmp.getChildren().addAll(zoneHauteColonne);
                for(Tache t : tab.getColonnes().get(i).getTaches()){
                    HBox tachetmp = new HBox();
                    String[] action= {"Modifier","Archiver","Supprimer"};
                    ControleurTache ct = new ControleurTache(tab,t);
                    Button[] buttons = new Button[3];
                    for(int j = 0 ; j<3; j++ ){
                    buttons[j] = new Button(action[j]);
                    buttons[j].setOnAction(ct);
                    }
                    tachetmp.getChildren().addAll(new Label(t.getNom()),buttons[0],buttons[1],buttons[2]);
                    colonnetmp.getChildren().addAll(tachetmp);
                }
                colonnetmp.getChildren().addAll(new Button("Ajouter une tâche"));
                colonnetmp.setMinHeight(810);
                this.getChildren().addAll(colonnetmp);
            }


            VBox ajoutColonne= new VBox();
            ajoutColonne.setStyle("-fx-border-color: green; -fx-border-width: 2px;");
            ajoutColonne.setPadding(new Insets(50));
            ajoutColonne.getChildren().addAll(new Button("Ajouter Colonne"));
            this.getChildren().addAll(ajoutColonne);

        }


    private VBox createPlaceholder(int index) {
        VBox placeholder = new VBox();
        placeholder.setUserData(index);
        return placeholder;
    }
}
