package classes;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

//Classe VueListe
public class VueListe extends VBox implements Observateur {

    VueListe(){
        super();
        this.setMinWidth(930);
        this.setStyle("-fx-background-color: rgb(255,255,255,0.5)");

    }

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet sous forme de liste
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        this.getChildren().clear();
        Tableau tab = (Tableau) sujet;

        for(Colonne c : tab.liste) {
            TreeItem<Composant> colonne = new TreeItem<>(c);
            colonne.setExpanded(true);
            for (Tache t : c.liste) {
                TreeItem<Composant> tache = new TreeItem<>(t);
                colonne.getChildren().add(tache);
                if(t instanceof TacheMere){
                    ajoutersoustache(tache, (TacheMere) t);
                }
            }
            TreeView<Composant> tree  = new TreeView<>(colonne);
            this.getChildren().add(tree);
        }

    }

    /**
     * Méthode ajoutersoustache qui ajoute toutes les sous-taches d'une tache à son TreeItem
     * @param tree le TreeItem de la tache
     * @param t la tache mère
     */
    public void ajoutersoustache(TreeItem<Composant> tree, TacheMere t){
        tree.setExpanded(true);
        for(Tache st : t.getSousTaches()){
            TreeItem<Composant> sousTache = new TreeItem<>(st);
            tree.getChildren().add(sousTache);
            if(st instanceof TacheMere){
                ajoutersoustache(sousTache, (TacheMere) st);
            }
        }

    }
}
/*Tableau tab = (Tableau) sujet;
        System.out.println("VueListe : ");
        tab.afficher();*/