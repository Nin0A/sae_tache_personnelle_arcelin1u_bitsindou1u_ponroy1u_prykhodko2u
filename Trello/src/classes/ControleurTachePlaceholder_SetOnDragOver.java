package classes;

import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ControleurTachePlaceholder_SetOnDragOver implements Controleur<DragEvent> {

    private VBox placeholder;

    ControleurTachePlaceholder_SetOnDragOver(VBox pl){
        placeholder = pl;
    }


    @Override
    public void handle(DragEvent event) {
        if (event.getGestureSource() != event.getTarget() &&
                event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }
}
