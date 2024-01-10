package classes;

import javafx.scene.input.DragEvent;
import javafx.event.EventHandler;

public class ControleurTache_SetOnDragDone implements Controleur<DragEvent> {

    private Sujet s;
    private Observateur v;

    ControleurTache_SetOnDragDone(Sujet sujet, Observateur vue){
        v = vue;
        s =sujet;
    }

    @Override
    public void handle(DragEvent event) {
        v.actualiser(s);
        event.consume();
    }
}
