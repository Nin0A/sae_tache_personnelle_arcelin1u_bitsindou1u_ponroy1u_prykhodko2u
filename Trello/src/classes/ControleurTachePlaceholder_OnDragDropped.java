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
//            System.out.println("OK");

            col.liste.add( ((VBox)((VBox)event.getGestureTarget()).getParent()).getChildren().indexOf(placeholder), source);

            root.actualiser(tab);
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
}
