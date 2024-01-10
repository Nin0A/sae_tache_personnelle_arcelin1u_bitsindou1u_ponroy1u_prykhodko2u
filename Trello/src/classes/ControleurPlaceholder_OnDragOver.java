package classes;

import javafx.event.EventHandler;
import javafx.scene.input.DataFormat;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;

public class ControleurPlaceholder_OnDragOver implements Controleur<DragEvent>{

    private VBox placeholder;

    ControleurPlaceholder_OnDragOver(VBox pl){
        placeholder = pl;
    }
    /**
     * Méthode handle qui gère les actions sur les boutons
     * @param event action sur un bouton
     */
    public void handle(DragEvent event) {
        if (event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
            event.acceptTransferModes(TransferMode.MOVE);
        }
        event.consume();
    }



}
