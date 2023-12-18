package classes;

import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.HBox;

//Classe VueListe
public class VueListe extends HBox implements Observateur {
    VueListe(){
        super();
    }

    /**
     * Méhtode actualiser qui permet d'actualiser le sujet sous forme de liste
     * @param sujet sujet à actualiser
     */
    @Override
    public void actualiser(Sujet sujet) {
        TreeItem<Composant> colonne1 = new TreeItem<Composant>(new Colonne("Colonne 1"));
        TreeItem<Composant> tache0 = new TreeItem<Composant>(new TacheMere("Tache racine", 0 ));
        TreeItem<Composant> tache2 = new TreeItem<Composant>(new TacheMere("Tache 2", 0 ));
        colonne1.getChildren().add(tache0);
        colonne1.setExpanded(true);
        tache0.getChildren().add(tache2);
        tache0.setExpanded(true);

        /*Tableau tab = (Tableau) sujet;
        System.out.println("VueListe : ");
        tab.afficher();*/
        TreeView<Composant> tree  = new TreeView<>(colonne1);
        this.getChildren().add(tree);

    }
}
