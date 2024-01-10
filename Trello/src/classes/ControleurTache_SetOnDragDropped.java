package classes;

import javafx.scene.control.TreeCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.chrono.ThaiBuddhistChronology;

public class ControleurTache_SetOnDragDropped implements Controleur<DragEvent> {

    private Tableau tab;


    ControleurTache_SetOnDragDropped(Tableau t){
        tab = t;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            if(event.getGestureSource() != event.getGestureTarget()){
                Tache move = null;
                Tache tMere = null;

                if (event.getGestureSource() instanceof TreeCell && ((TreeCell<?>)event.getGestureTarget()).getUserData() instanceof Colonne){
                    move = (Tache) ((TreeCell<?>)event.getGestureSource()).getUserData();
                    Colonne col = (Colonne) ((TreeCell<?>)event.getGestureTarget()).getUserData();
                    tab.supprimerTache(move);
                    col.ajouterTache(move);
                }
                else if (event.getGestureSource() instanceof TreeCell && ((TreeCell<?>)event.getGestureTarget()).getUserData() instanceof Tache){
                     move = (Tache) ((TreeCell<?>)event.getGestureSource()).getUserData();
                     tMere = (Tache) ((TreeCell<?>)event.getGestureTarget()).getUserData();

                }
                else if (((Pane)event.getGestureSource()).getUserData() instanceof Tache) {
                    move = (Tache) ((Pane) event.getGestureSource()).getUserData();
                    tMere = (Tache) ((Pane) event.getGestureTarget()).getUserData();
                }

                if(tMere!= null && ((TacheMere)tMere).verifAjout(move)){
                    tab.supprimerTache(move);
                    ((TacheMere)tMere).ajouterSousTache(move);
                    success = true;
                }
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}