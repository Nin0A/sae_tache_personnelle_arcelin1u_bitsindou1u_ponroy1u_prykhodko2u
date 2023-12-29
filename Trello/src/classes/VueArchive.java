package classes;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class VueArchive extends HBox implements Observateur{

    /**
     * Méthode actualiser qui permet d'actualiser le sujet sous d'une colonne
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {

        //Mise à jour de la vue
        Tableau tab = (Tableau) sujet;
        this.getChildren().clear();
        this.setSpacing(10);

        //On créer la colonne d'archive et on l'ajoute à la vue
        VBox colonnetmp = new VBox();
        colonnetmp.setSpacing(10);
        colonnetmp.setMinWidth(250);
        colonnetmp.setStyle("-fx-border-color: purple; -fx-border-width: 5px;-fx-border-radius: 50px;-fx-background-color: rgb(255,255,255,0.5) ; -fx-background-radius:50px; ");
        colonnetmp.setPadding(new Insets(20));
        colonnetmp.setAlignment(Pos.TOP_CENTER);
        HBox zoneHauteColonne = new HBox();

        Label titreColonne = new Label(tab.getArchive().getNom()); //a verifier

        titreColonne.setStyle("-fx-font-family: 'Arial';" +
                "-fx-font-size: 20;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #b40047;" +
                "-fx-font-family: Krungthep;");

        //on ajoute le titre de la colonne à la vue
        zoneHauteColonne.getChildren().addAll(titreColonne);


        //On ajoute la colonne à la vue
        VBox vbox = new VBox();

        vbox.getChildren().addAll(titreColonne, zoneHauteColonne);
        zoneHauteColonne.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        colonnetmp.getChildren().addAll(vbox);
        ControleurTache ct = null;
        //On ajoute les taches de la colonne archives à la vue (si il y en a)
        for (Tache t : tab.getArchive().getTaches()) {
            HBox tachetmp = new HBox();
            tachetmp.getChildren().add(new Label(t.getNom()));

            //Button et Controleur
            ct = new ControleurTache(tab, t);
            ajouterBouton(tachetmp, ct);
            colonnetmp.getChildren().addAll(tachetmp);
            //si il y a des taches meres, on archive aussi les sous taches
            if (t instanceof TacheMere) {
                ArrayList<HBox> listeSoustache = ajoutersoustache((TacheMere) t, tab);
                for (HBox hbox : listeSoustache) {
                    colonnetmp.getChildren().addAll(hbox);
                }
            }


        }
    }

    /**
     * Méthode ajouterBouton qui permet d'ajouter un bouton à une tache
     * @param tache à laquelle on veut ajouter un bouton
     * @param c controleur du bouton
     */
    public void ajouterBouton(HBox tache, Controleur c){
        String[] action= {"Désarchiver"};
        Button[] buttons = new Button[1];
        for(int j = 0 ; j<1; j++ ){
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

    /**
     * Méthode ajoutersoustache qui permet d'ajouter les sous taches d'une tache mère
     * @param t tache mère dont on veut ajouter les sous taches
     * @param tab tableau dans lequel se trouve la tache mère
     * @return la liste des sous taches
     */
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
}
