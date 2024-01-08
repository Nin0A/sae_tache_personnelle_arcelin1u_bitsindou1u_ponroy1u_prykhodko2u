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

    public void handle(DragEvent event) {
        if (event.getDragboard().hasContent(DataFormat.PLAIN_TEXT)) {
            System.out.println("ondragover_placeholder");
            event.acceptTransferModes(TransferMode.MOVE);
            placeholder.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        }
        event.consume();
    }



}
