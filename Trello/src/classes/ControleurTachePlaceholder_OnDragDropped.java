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
            System.out.println("indexOfPlaceholder = " + indexOfPlaceholder + " indexOfTache = " + indexOfTache);
            if (indexOfPlaceholder+1 != indexOfTache && indexOfPlaceholder-1 != indexOfTache || (source.getColonneOrigine() != col)) {
                int index = (indexOfTache > indexOfPlaceholder) || (source.getColonneOrigine() != col) || (((Pane) event.getGestureSource()).getId()).contains("soustache")   ? (indexOfPlaceholder) / 2 : (indexOfPlaceholder - 2) / 2;
            System.out.println("indexOfPlaceholder = " + indexOfPlaceholder + " indexOfTache = " + indexOfTache + " index = " + index + " 1 = " + (indexOfTache > indexOfPlaceholder) + " 2 = " + (source.getColonneOrigine() != col) + " 3 = " + (((Pane) event.getGestureSource()).getId()).contains("soustache"));
                tab.supprimerTache(source);
                if (index > col.liste.size() || col.liste.size() == 0) {
                System.out.println("case1");
                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    source.setColonneOrigine(col);
                    col.liste.add(source);

                } else {
                System.out.println("case2");
                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    source.setColonneOrigine(col);
                    col.liste.add(index, source);
                }
                success = true;
            }
        }
        root.actualiser(tab);
        event.setDropCompleted(success);
        event.consume();
    }
}
