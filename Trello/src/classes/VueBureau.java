package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;


public class VueBureau extends HBox implements Observateur {


        /**
        * Méhtode actualiser qui permet d'actualiser le sujet colonnes par colonnes
        * @param sujet sujet à actualiser
        */
        @Override
        public void actualiser(Sujet sujet) {
            Tableau tab = (Tableau) sujet;
            this.getChildren().clear();
            this.setSpacing(10);

            /*
            System.out.println("VueBureau : " + tab.getNom());

            ArrayList<Colonne> colonnes = tab.getColonnes();

            //on parcourt chaque colonne
            for (Colonne colonne : colonnes) {
                System.out.println(colonne.getNom() + " :"+"\t"); //on met un tab entre chaque colonne
            }
            */

            VBox colonnetmp;

            for(int i=0;i<tab.getColonnes().size();i++){
                colonnetmp= new VBox();
                colonnetmp.setSpacing(10);
                colonnetmp.setMinWidth(250);
                colonnetmp.setStyle("-fx-border-color: purple; -fx-border-width: 5px;-fx-border-radius: 50px;-fx-background-color: rgb(255,255,255,0.5) ; -fx-background-radius:50px; ");
                colonnetmp.setPadding(new Insets(20));
                colonnetmp.setAlignment(Pos.TOP_CENTER);
                HBox zoneHauteColonne = new HBox();

                Label titreColonne = new Label(tab.getColonnes().get(i).getNom());

                titreColonne.setStyle("-fx-font-family: 'Arial';" +
                                        "-fx-font-size: 20;" +
                                        "-fx-font-weight: bold;" +
                                        "-fx-text-fill: blue;");
                ControleurColonne cc = new ControleurColonne(tab,tab.getColonnes().get(i));
                Button modif = new Button("Modifier");
                modif.setOnAction(cc);

                Button suppr = new Button("Supprimer");
                suppr.setOnAction(cc);

                zoneHauteColonne.getChildren().addAll(titreColonne,modif,suppr);


                VBox vbox = new VBox();

                vbox.getChildren().addAll(titreColonne,zoneHauteColonne);
                zoneHauteColonne.setAlignment(Pos.CENTER);
                vbox.setPadding(new Insets(20));
                vbox.setAlignment(Pos.CENTER);
                colonnetmp.getChildren().addAll(vbox);
                for(Tache t : tab.getColonnes().get(i).getTaches()){
                    HBox tachetmp = new HBox();
                    tachetmp.getChildren().add(new Label(t.getNom()));

                    //Button et Controleur
                    ControleurTache ct = new ControleurTache(tab,t);
                    ajouterBouton(tachetmp,ct);
                    colonnetmp.getChildren().addAll(tachetmp);
                    //Sous taches
                    if(t instanceof TacheMere) {
                        ArrayList<HBox> listeSoustache = ajoutersoustache((TacheMere) t,tab);
                        for (HBox hbox : listeSoustache) {
                            colonnetmp.getChildren().addAll(hbox);
                        }
                    }

                }

                Button ajouterTache = new Button("Ajouter une tâche");

                ////***** Buton supprimer //////

                ajouterTache.setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #ffe1fd; " + // Couleur de fond
                                "-fx-text-fill: #000000; " + // Couleur du texte
                                "-fx-border-color: #cea1c9; " + // Couleur de la bordure
                                "-fx-border-width: 2px; "+
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
                );

                // Style pour le survol
                ajouterTache.setOnMouseEntered(e -> ajouterTache.setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                                "-fx-text-fill: #000000; " + // Nouvelle couleur du texte au survol
                                "-fx-border-color: #ffc3f8; "+
                                "-fx-border-width: 2px; "+
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;"
                ));

                // Style par défaut après le survol
                ajouterTache.setOnMouseExited(e -> ajouterTache.setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #ffe1fd; " + // Retour à la couleur de fond transparente
                                "-fx-text-fill: #000000; " + // Retour à la couleur du texte blanche
                                "-fx-border-color: #cea1c9; " + // Retour à la couleur de bordure initiale
                                "-fx-border-width: 2px; " +
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
                ));

                /////////////////////////////////
                ajouterTache.setAlignment(Pos.CENTER);
                colonnetmp.getChildren().addAll(ajouterTache);
                colonnetmp.setMinHeight(710);
                this.getChildren().addAll(colonnetmp);
            }


            VBox ajoutColonne= new VBox();
            ajoutColonne.setStyle("-fx-border-color: green; -fx-border-width: 5px;-fx-border-radius: 50px");
            ajoutColonne.setPadding(new Insets(50));
            ajoutColonne.getChildren().addAll(new Button("Ajouter Colonne"));
            this.getChildren().addAll(ajoutColonne);

        }
        public ArrayList<HBox> ajoutersoustache(TacheMere t, Tableau tab){
            ArrayList<HBox> taches = new ArrayList<HBox>();
            for(Tache st : t.getSousTaches()){
                HBox soutache = new HBox();
                ControleurTache ct = new ControleurTache(tab,st);
                soutache.getChildren().add(new Label(st.getNom()));
                ajouterBouton(soutache,ct);
                taches.add(soutache);
                if(st instanceof TacheMere){
                    ajoutersoustache( (TacheMere) st, tab);
                }
            }
            return taches;

        }
        public void ajouterBouton(HBox tache, Controleur c){
            String[] action= {"Modifier","Archiver","Supprimer"};
            Button[] buttons = new Button[3];
            for(int j = 0 ; j<3; j++ ){
                buttons[j] = new Button(action[j]);
                buttons[j].setOnAction(c);
                buttons[j].setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #ffe1fd; " + // Couleur de fond
                                "-fx-text-fill: #000000; " + // Couleur du texte
                                "-fx-border-color: #cea1c9; " + // Couleur de la bordure
                                "-fx-border-width: 2px; "+
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
                );

                // Style pour le survol
                int finalJ = j;
                buttons[j].setOnMouseEntered(e ->  buttons[finalJ].setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                                "-fx-text-fill: #000000; " + // Nouvelle couleur du texte au survol
                                "-fx-border-color: #ffc3f8; "+
                                "-fx-border-width: 2px; "+
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;"
                ));

                // Style par défaut après le survol
                int finalJ1 = j;
                buttons[j].setOnMouseExited(e ->  buttons[finalJ1].setStyle(
                        "-fx-font-size: 10px; " +
                                "-fx-padding: 5px; " +
                                "-fx-background-color: #ffe1fd; " + // Retour à la couleur de fond transparente
                                "-fx-text-fill: #000000; " + // Retour à la couleur du texte blanche
                                "-fx-border-color: #cea1c9; " + // Retour à la couleur de bordure initiale
                                "-fx-border-width: 2px; " +
                                "-fx-border-radius: 50px;" + // Bordure arrondie
                                "-fx-background-radius: 50px;" // Coin arrondi pour le fond
                ));
            }
            tache.getChildren().addAll(buttons[0],buttons[1],buttons[2]);
        }
}
