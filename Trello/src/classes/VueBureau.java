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
     * Méthode setContr qui permet de mettre les controleurs sur les colonnes
     * @param col
     */
    private void setContr(VBox col){

        col.setOnDragDetected(new ControleurColonne_SetOnDragDetected(col, this));

    }

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet colonnes par colonnes
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        Tableau tab = (Tableau) sujet;
        this.getChildren().clear();
        this.setSpacing(10);


        //Création de la vbox du tableau

        VBox colonnetmp;

        //On créer le fond du tableau et on l'ajoute à la vue
        for(int i=0;i<tab.getColonnes().size();i++) {
            colonnetmp = new VBox();
            colonnetmp.setSpacing(10);
            colonnetmp.setMinWidth(250);
            colonnetmp.setStyle("-fx-border-color: #ffffff; -fx-border-width: 5px;-fx-border-radius: 10px;-fx-background-color: rgb(255,255,255,0.5) ; -fx-background-radius:10px; ");

            colonnetmp.setAlignment(Pos.TOP_CENTER);
            colonnetmp.setId(i + "");

            //----------------------------------
            setContr(colonnetmp);
            colonnetmp.setOnDragDone(new ControleurTache_SetOnDragDone(tab, this));

            HBox zoneHauteColonne = new HBox();

            //On créer les titres des colonnes et on les ajoute à la vue
            Label titreColonne = new Label(tab.getColonnes().get(i).getNom());
            Label idColonne = new Label(tab.getColonnes().get(i).getIdColonne() + "");
            idColonne.setVisible(false);

            titreColonne.setStyle("-fx-font-family: 'Arial';" +
                    "-fx-font-size: 30px;" +
                    "-fx-font-weight: bold;" +
                    "-fx-text-fill: #b66682;" +
                    "-fx-font-family: Krungthep;");

            //On créer les boutons de modification et de suppression des colonnes et on les ajoute à la vue
            ControleurColonne cc = new ControleurColonne(tab, tab.getColonnes().get(i));
            Button modif = new Button("Modifier");
            modif.setStyle("-fx-background-color: transparent;");

            // Style pour le survol
            modif.setOnMouseEntered(e -> modif.setStyle(
                    "-fx-background-color: transparent;-fx-underline: true;"
            ));

            // Style par défaut après le survol
            modif.setOnMouseExited(e -> modif.setStyle(
                    "-fx-background-color: transparent;-fx-underline: false"
            ));


            modif.setOnAction(cc);

            Button suppr = new Button("Supprimer");
            suppr.setStyle("-fx-background-color: transparent;");

            suppr.setOnMouseEntered(e -> suppr.setStyle(
                    "-fx-background-color: transparent;-fx-underline: true"
            ));

            // Style par défaut après le survol
            suppr.setOnMouseExited(e -> suppr.setStyle(
                    "-fx-background-color: transparent;-fx-underline: false"
            ));





            suppr.setOnAction(cc);

            zoneHauteColonne.getChildren().addAll(titreColonne,idColonne, modif, suppr);


            VBox vbox = new VBox();

            vbox.getChildren().addAll(titreColonne,idColonne, zoneHauteColonne);
            zoneHauteColonne.setAlignment(Pos.CENTER);

            vbox.setAlignment(Pos.CENTER);
            colonnetmp.getChildren().addAll(vbox);
            ControleurTache ct = null;
            int tacheId = 0;
            //Pour chaque tache on crée une Hbox comportant le nom de la tache et on l'ajoute à la vue
            for (Tache t : tab.getColonnes().get(i).getTaches()) {

                VBox tachetmp = new VBox();

                    tachetmp.setAlignment(Pos.CENTER_LEFT);
                    HBox boutonstachetmp = new HBox();
                    boutonstachetmp.setAlignment(Pos.CENTER_LEFT);
                    //Button et Controleur
                    ct = new ControleurTache(tab, t);
                    ajouterBouton(boutonstachetmp, ct);

                    //placeholder pour taches
                    tachetmp.setId("tache"+ tacheId);
                    tacheId++;
                    tachetmp.setOnDragDropped(new ControleurTache_SetOnDragDropped(this, tab));
                    tachetmp.setOnDragOver(new ControleurTachePlaceholder_SetOnDragOver());
                    tachetmp.setOnDragDetected(new ControleurTache_SetOnDragDetected(tachetmp, this));
                    tachetmp.setOnDragDone(new ControleurTache_SetOnDragDone(tab, this));
                    tachetmp.setUserData(t);
                    VBox pl = createPlaceholderTache(tab, this);
                    pl.setUserData(tab.getColonnes().get(i));


                Label ll = new Label(t.getNom());
                ll.setStyle("-fx-font-size: 20;-fx-font-family: 'Zapf Dingbats'");
                HBox tmp = new HBox(); ///////////////////////////////////////////////////////////////////////supprimer HBOX SI TOUT EST CASSER
                tmp.getChildren().addAll(ll,boutonstachetmp);
                tachetmp.getChildren().addAll(tmp);

                    //Sous taches
                    if (t instanceof TacheMere) {
                        ArrayList<HBox> listeSoustache = ajoutersoustache((TacheMere) t, tab,25);
                        for (HBox hbox : listeSoustache) {
                            VBox p = createPlaceholderTache(tab,this);
                            p.setId("soustachePlaceholder");

                            tachetmp.getChildren().addAll(p ,hbox);
                        }
                    }
                    colonnetmp.getChildren().addAll(pl, tachetmp);
            }
            VBox pl = createPlaceholderTache(tab, this);
            pl.setUserData(tab.getColonnes().get(i));
            colonnetmp.getChildren().addAll(pl);

            ct = new ControleurTache(tab, null);
            Button ajouterTache = new Button("Ajouter une tâche");
            ajouterTache.setOnAction(ct);

            ////***** Buton supprimer //////

            ajouterTache.setStyle("-fx-background-color:black;-fx-border-color: black;-fx-text-fill: white ;-fx-border-radius:5px;-fx-font-size: 15px;");

            ajouterTache.setOnMouseEntered(e -> ajouterTache.setStyle(
                    "-fx-background-color:transparent;" +
                            "-fx-text-fill: black ;-fx-border-width: 2px;-fx-border-color: black;-fx-border-radius:5px;-fx-font-size: 15px;"
            ));

            // Style par défaut après le survol
            ajouterTache.setOnMouseExited(e -> ajouterTache.setStyle(
                    "-fx-background-color:black;-fx-border-color: black;-fx-text-fill: white ;-fx-border-radius:5px;-fx-font-size: 15px;"
            ));
            /////////////////////////////////
            ajouterTache.setAlignment(Pos.BOTTOM_CENTER);



            colonnetmp.getChildren().addAll(ajouterTache);
            colonnetmp.setMinHeight(650);
            colonnetmp.setPadding(new Insets(20));
            this.getChildren().addAll(colonnetmp);
        }


        VBox ajoutColonne= new VBox();
        ajoutColonne.setPadding(new Insets(50));
        Button ajouterColonne = new Button("+");

        ajouterColonne.setStyle(
                "-fx-min-width: 70px; -fx-min-height: 70px;" +
                        "-fx-background-color: #9dcfff; " + // Couleur de fond
                        "-fx-text-fill: #ffffff; " + // Couleur du texte
                        "-fx-border-color: #005686; " + // Couleur de la bordure
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 50px;" + // Bordure arrondie
                        "-fx-background-radius: 50px;-fx-font-weight: bold; -fx-font-size: 40px;" // Coin arrondi pour le fond
        );

        // Style pour le survol
        ajouterColonne.setOnMouseEntered(e -> ajouterColonne.setStyle(
                "-fx-min-width: 70px; -fx-min-height: 70px;" +
                        "-fx-background-color: #fffefe; " + // Nouvelle couleur de fond au survol
                        "-fx-text-fill: #000000; " + // Nouvelle couleur du texte au survol
                        "-fx-border-color: #005686; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 50px;" + // Bordure arrondie
                        "-fx-background-radius: 50px;-fx-font-weight: bold; -fx-font-size: 40px;"
        ));

        // Style par défaut après le survol
        ajouterColonne.setOnMouseExited(e -> ajouterColonne.setStyle(
                "-fx-min-width: 70px; -fx-min-height: 70px;" +
                        "-fx-background-color: #9dcfff; " + // Couleur de fond
                        "-fx-text-fill: #ffffff; " + // Couleur du texte
                        "-fx-border-color: #005686; " + // Couleur de la bordure
                        "-fx-border-width: 2px; " +
                        "-fx-border-radius: 50px;" + // Bordure arrondie
                        "-fx-background-radius: 50px;-fx-font-weight: bold; -fx-font-size: 40px;" // Coin arrondi pour le fond
        ));

        ajoutColonne.setAlignment(Pos.CENTER);
        ajoutColonne.getChildren().addAll(ajouterColonne);
        ajouterColonne.setOnAction(new ControleurColonne(tab,new Colonne(null)));

        this.getChildren().addAll(ajoutColonne);
        //============================
        addPlaceholdersColonnes(this, tab);
    }


    /**
     * Méthode ajoutersoustache qui permet d'ajouter les sous taches d'une tache mère
     * @param t tache mère dont on veut ajouter les sous taches
     * @param tab tableau où se trouve la tache mère
     * @return liste des sous taches
     */

    public ArrayList<HBox> ajoutersoustache(TacheMere t, Tableau tab,int padding) {
        ArrayList<HBox> taches = new ArrayList<>();

        int sousTacheId = 0;
        for (Tache st : t.getSousTaches()) {


            HBox soutache = new HBox();

            soutache.setSpacing(2);


            ControleurTache ct = new ControleurTache(tab, st);
            soutache.setPadding(new Insets(0,0,0,padding));
            Label l = new Label(st.getNom());
            l.setPadding(new Insets(0,0,0,5));
            l.setStyle("-fx-font-size: 18;-fx-font-family: 'Zapf Dingbats';-fx-border-color: black;-fx-border-width: 0 0 0 2px;");
            soutache.getChildren().add(l);
            soutache.setId("soustache"+sousTacheId);
            sousTacheId++;
            soutache.setUserData(st);
            soutache.setOnDragDetected(new ControleurTache_SetOnDragDetected(soutache, this));
            soutache.setOnDragDone(new ControleurTache_SetOnDragDone(tab, this));
            soutache.setOnDragOver(new ControleurTachePlaceholder_SetOnDragOver());

//            soutache.setOnDragDropped();

            ajouterBouton(soutache, ct);
            taches.add(soutache);

            if (st instanceof TacheMere) {
                // Ajouter les sous-tâches récursivement à la liste actuelle

                taches.addAll(ajoutersoustache((TacheMere) st, tab,padding+25));

            }
        }

        return taches;
    }

    private VBox createPlaceholderColonne(Tableau tab, VueBureau vb) {
        VBox placeholder = new VBox();

        placeholder.setPrefWidth(30);
        placeholder.setStyle("-fx-background-color: #7bb6d2;-fx-border-radius: 50px;-fx-border-width: 20px");
        placeholder.setId("placeholderColonne");

        placeholder.setVisible(false);

        placeholder.setOnDragOver(new ControleurPlaceholder_OnDragOver(placeholder));

        placeholder.setOnDragDropped(new ControleurPlaceholder_OnDragDropped(vb, placeholder, tab));

        return placeholder;
    }
    /**
     * Méthode createPlaceholderTache qui permet de créer un placeholder pour une tache
     * @param tab tableau où se trouve la tache
     * @param vb vue du bureau
     * @return le placeholder
     */
    private VBox createPlaceholderTache(Tableau tab, VueBureau vb) {
        VBox placeholder = new VBox();
        placeholder.setPrefWidth(100);
        placeholder.setPrefHeight(20);
        placeholder.setStyle("-fx-background-color: #7bb6d2;-fx-border-radius: 50px;-fx-border-width: 20px");
        placeholder.setId("placeholderTache");
        placeholder.setVisible(false);


        placeholder.setOnDragOver(new ControleurTachePlaceholder_SetOnDragOver());

        placeholder.setOnDragDropped(new ControleurTachePlaceholder_OnDragDropped(vb, placeholder, tab));

        return placeholder;
    }

    /**
     * methode addPlaceholdersColonnes qui permet d'ajouter les placeholders pour les colonnes
     * @param vb
     * @param tab
     */
    private void addPlaceholdersColonnes(VueBureau vb, Tableau tab) {
        int size = getChildren().size();
        for (int i = 0; i < size; i++) {
            VBox placeholder = createPlaceholderColonne(tab, vb);
            getChildren().add(i*2, placeholder);
        }

    }

    /**
     * Méthode ajouterBouton qui permet d'ajouter un bouton à une tache
     * @param tache à laquelle on veut ajouter un bouton
     * @param c controleur du bouton
     */
    public void ajouterBouton(HBox tache, Controleur c){
        String[] action= {"Modifier","Archiver","Supprimer"};
        Button[] buttons = new Button[3];
        for(int j = 0 ; j<3; j++ ){
            buttons[j] = new Button(action[j]);
            buttons[j].setOnAction(c);

            // Style par défaut
            buttons[j].setStyle(
                    "-fx-font-size: 10px; " +
                            "-fx-padding: 5px; " +
                            "-fx-background-color: #bde7cc; " + // Couleur de fond
                            "-fx-text-fill: white; " + // Couleur du texte
                            "-fx-border-color: #ffffff; " + // Couleur de la bordure
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
                            "-fx-text-fill: #95c2a8; " + // Nouvelle couleur du texte au survol
                            "-fx-border-color: #95c2a8; "+
                            "-fx-border-width: 2px; "+
                            "-fx-border-radius: 50px;" + // Bordure arrondie
                            "-fx-background-radius: 50px;"
            ));

            // Style par défaut après le survol
            int finalJ1 = j;
            buttons[j].setOnMouseExited(e ->  buttons[finalJ1].setStyle(
                    "-fx-font-size: 10px; " +
                            "-fx-padding: 5px; " +
                            "-fx-background-color: #bde7cc; " + // Retour à la couleur de fond transparente
                            "-fx-text-fill: white; " + // Retour à la couleur du texte blanche
                            "-fx-border-color: #ffffff; " + // Retour à la couleur de bordure initiale
                            "-fx-border-width: 2px; " +
                            "-fx-border-radius: 50px;" + // Bordure arrondie
                            "-fx-background-radius: 50px;" // Coin arrondi pour le fond
            ));
        }
        tache.getChildren().addAll(buttons[0],buttons[1],buttons[2]);
    }
}
