package classes;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.event.EventHandler;

public class ControleurTachePlaceholder_OnDragDropped implements Controleur<DragEvent> {
    private VueBureau root;
    private VBox placeholder;
    private Tableau tab;

    ControleurTachePlaceholder_OnDragDropped(VueBureau r, VBox pl, Tableau t){
        root = r;
        placeholder = pl;
        tab = t;
    }

    /**
     * Méthode handle
     * @param event action
     */
    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            Tache source = (Tache) (((Pane)(event.getGestureSource())).getUserData());
            Colonne col = ((Colonne) ((Pane)(event.getGestureTarget())).getUserData());



            int indexOfPlaceholder = ((((Pane) ((Pane) event.getGestureTarget()).getParent()).getChildren().indexOf(placeholder)));
            int indexOfTache = ((((Pane) ((Pane) event.getGestureSource()).getParent()).getChildren().indexOf( (Pane) event.getGestureSource() )));
            if (indexOfPlaceholder+1 != indexOfTache && indexOfPlaceholder-1 != indexOfTache || (source.getColonneOrigine() != col)) {
                int index = (indexOfTache > indexOfPlaceholder) || (source.getColonneOrigine() != col) || (((Pane) event.getGestureSource()).getId()).contains("soustache")   ? (indexOfPlaceholder) / 2 : (indexOfPlaceholder - 2) / 2;
                tab.supprimerTache(source);
                if (index > col.liste.size() || col.liste.size() == 0) {
                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    col.liste.add(source);
                    source.setColonneOrigine(col);

                } else {
                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    col.liste.add(index, source);
                    source.setColonneOrigine(col);
                    tab.getColonnes().get(1).afficher();
                }
                success = true;
            }
        }
        root.actualiser(tab);
        event.setDropCompleted(success);
        event.consume();
    }
}
