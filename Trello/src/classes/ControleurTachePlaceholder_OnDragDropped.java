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


    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            Tache source = (Tache) (((Pane)(event.getGestureSource())).getUserData());
            Colonne col = ((Colonne) ((Pane)(event.getGestureTarget())).getUserData());

            tab.supprimerTache(source);

            int indexOfPlaceholder = ((((Pane) ((Pane) event.getGestureTarget()).getParent()).getChildren().indexOf(placeholder)));
            int indexOfTache = ((((Pane) ((Pane) event.getGestureSource()).getParent()).getChildren().indexOf( (Pane) event.getGestureSource() )));
            if (indexOfPlaceholder+1 != indexOfTache && indexOfPlaceholder-1 != indexOfTache) {
                int index = (indexOfTache > indexOfPlaceholder) || (source.getColonneOrigine() != col) ? (indexOfPlaceholder) / 2 : (indexOfPlaceholder - 2) / 2;
//            System.out.println("indexOfPlaceholder = " + indexOfPlaceholder + " indexOfTache = " + indexOfTache + " index = " + index + " 1 = " + (indexOfTache > indexOfPlaceholder) + " 2 = " + (source.getColonneOrigine() != col) );
                if (index > col.liste.size() || col.liste.size() == 0) {
//                System.out.println("case1");

                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    source.setColonneOrigine(col);
                    col.liste.add(source);

                } else {
//                    System.out.println("index =" + index);
//                System.out.println("case2");
                    if (source instanceof SousTache)
                        source = new TacheMere(source);
                    source.setColonneOrigine(col);
                    col.liste.add(index, source);
//                    System.out.println(col.liste.get(index));
                }
                root.actualiser(tab);
                success = true;
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
