package classes;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
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
            Tache source = (Tache) (((VBox)(event.getGestureSource())).getUserData());
            Colonne col = ((Colonne) ((VBox)(event.getGestureTarget())).getUserData());

            tab.supprimerTache(source);

            int indexOfPlaceholder = ((((VBox) ((VBox) event.getGestureTarget()).getParent()).getChildren().indexOf(placeholder)));
            int indexOfTache = ((((VBox) ((VBox) event.getGestureSource()).getParent()).getChildren().indexOf( (VBox) event.getGestureSource() )));
            int index =  (indexOfTache > indexOfPlaceholder) || (source.getColonneOrigine() != col)  ? (indexOfPlaceholder)/2 : (indexOfPlaceholder-2)/2 ;

            if (index > col.liste.size() || col.liste.size()==0){
                System.out.println("case1");

                col.liste.add(source);
            }
            else {
                System.out.println("case2");
                col.liste.add(index , source);
            }
            root.actualiser(tab);
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
