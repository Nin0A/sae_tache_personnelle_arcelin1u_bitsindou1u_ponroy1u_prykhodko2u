package classes;

import javafx.scene.input.DragEvent;
import javafx.event.EventHandler;

public class ControleurTache_SetOnDragDone implements Controleur<DragEvent> {

    @Override
    public void handle(DragEvent event) {

        event.consume();
    }
}
