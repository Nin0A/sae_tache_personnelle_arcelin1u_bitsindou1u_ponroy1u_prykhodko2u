package classes;

import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.time.chrono.ThaiBuddhistChronology;

public class ControleurTache_SetOnDragDropped implements Controleur<DragEvent> {

    private Observateur root;
    private Tableau tab;


    ControleurTache_SetOnDragDropped(Observateur r, Tableau t){
        tab = t;
        root = r;
    }

    @Override
    public void handle(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasString()) {
            if (((Pane)event.getGestureSource()).getUserData() instanceof Tache && event.getGestureSource() != event.getGestureTarget()){
                Tache move = (Tache) ((Pane)event.getGestureSource()).getUserData();
                Tache tMere = (Tache) ((Pane)event.getGestureTarget()).getUserData();


                System.out.println("AJOUTTTT");

                if(((TacheMere)tMere).verifAjout(move)){
                    System.out.println("AJOUTOKKKKKKKK");
                    tab.supprimerTache(move);
                    ((TacheMere)tMere).ajouterSousTache(move);
                    success = true;
                    root.actualiser(tab);
                }

                StringBuilder str = new StringBuilder();

                for(Tache t :((TacheMere) tMere).getSousTaches()){
                    str.append(t);
                }


                System.out.println(str);
            }
        }
        event.setDropCompleted(success);
        event.consume();
    }
}